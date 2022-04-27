package com.agombiproducts.notes.models

data class Note(
    val id: String,
    var task: String,
    var urgent: Boolean,
    var done: Boolean,
    var created: String,
    var modified: String?
)