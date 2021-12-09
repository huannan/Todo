package com.nan.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                Todos(DataCenter.todos)
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

