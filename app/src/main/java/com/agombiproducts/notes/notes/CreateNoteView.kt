package com.agombiproducts.notes.notes


import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import com.agombiproducts.notes.R

@Composable
fun CreateNoteView(navController: NavHostController) {
    Row() {
        TopMenuArea(title = stringResource(id = R.string.title_create_note))
        NoteTaskInput()
    }

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