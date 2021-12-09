package com.nan.todo.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Todo {
    var name by mutableStateOf("")
    var completed by mutableStateOf(false)
}
