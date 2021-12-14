package com.nan.todo.data

import androidx.compose.runtime.mutableStateListOf

object DataCenter {
    val todos = mutableStateListOf(
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
}