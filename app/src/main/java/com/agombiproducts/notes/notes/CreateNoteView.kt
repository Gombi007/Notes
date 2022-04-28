package com.agombiproducts.notes.notes


import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController

@Composable
fun CreateNoteView(navController: NavHostController) {
    NoteTaskInput()

}

@Composable
fun NoteTaskInput() {

    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = {
            Text(text = "Label")
        },
        textStyle = TextStyle(color = MaterialTheme.colors.primary)

    )
}