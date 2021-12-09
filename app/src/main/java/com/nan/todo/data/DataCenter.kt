package com.nan.todo.data

object DataCenter {
    val todos = mutableListOf(
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