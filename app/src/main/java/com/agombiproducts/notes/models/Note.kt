package com.agombiproducts.notes.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Note(
    var id: String?,
    var task: String,
    var urgent: Boolean,
    var done: Boolean,
    var created: String?,
    var modified: String?
)

/*
class Note {
    var id: String = ""
    var task: MutableState<String> = mutableStateOf("")
    var urgent: Boolean = false
    var done: Boolean = false
    var created: String = ""
    var modified: String = ""

}
*/

