package com.nan.todo

import android.os.Bundle
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nan.todo.data.DataCenter
import com.nan.todo.data.Todo
import com.nan.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    // remember的作用是防止Composable函数多次调用导致改值重新初始化
    var inputting by remember {
        mutableStateOf(false)
    }
    val animatedFabScale by animateFloatAsState(targetValue = if (inputting) 0F else 1F)
    val animatedInputScale by animateFloatAsState(targetValue = if (inputting) 1F else 0F)
    var inputText by remember {
        mutableStateOf("")
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // 弹出输入框
                    inputting = true
                },
                Modifier
                    .scale(animatedFabScale)
                    .alpha(animatedFabScale)
            ) {
                Icon(Icons.Filled.Add, "创建")
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text("首页")
                }, navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Home, "首页")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Settings, "设置")
                    }
                }
            )
        }) {
        Box(Modifier.fillMaxSize()) {
            Todos(DataCenter.todos)
            Row(Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .scale(animatedInputScale)
                .alpha(animatedInputScale)) {
                TextField(
                    value = inputText,
                    onValueChange = {
                        inputText = it
                    },
                    Modifier.weight(1.0F),
                    placeholder = {
                        Text(text = "接下来要做点什么？")
                    }
                )
                IconButton(onClick = {
                    // 关闭输入框
                    if (!TextUtils.isEmpty(inputText)) {
                        DataCenter.todos.add(Todo().apply {
                            name = inputText
                        })
                        inputting = false
                    }
                }) {
                    Icon(Icons.Filled.Send, "创建")
                }
            }
        }
    }
}

@Composable
fun Todos(todos: List<Todo>) {
    Surface(Modifier.padding(16.dp), elevation = 8.dp, shape = RoundedCornerShape(8.dp)) {
        LazyColumn(Modifier.fillMaxWidth()) {
            items(todos) { todo ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Compose 整体上就被设计为了数据和表现始终⼀致的数据模型，单向数据流，这样会减少开发成本和BUG
                    Checkbox(checked = todo.completed, onCheckedChange = { checked ->
                        todo.completed = checked
                    })
                    Text(todo.name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Preview(showBackground = true)
@Composable
fun TodosPreview() {
    val todos = listOf(
        Todo().apply {
            name = "吃饭"
        },
        Todo().apply {
            name = "洗澡"
        },
        Todo().apply {
            name = "睡觉"
        },
    )
    Todos(todos)
}

