package com.agombiproducts.notes.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.agombiproducts.notes.R
import com.agombiproducts.notes.database.DatabaseToFile
import com.agombiproducts.notes.routes.NavRoute
import com.agombiproducts.notes.ui.theme.Black
import com.agombiproducts.notes.ui.theme.Red_Delete

class ModifyNoteView {

    @Composable
    fun RenderModifyView(navController: NavHostController, noteId: String?) {
        val context = LocalContext.current
        val noteById = DatabaseToFile().getNoteById(context = context, id = noteId)
        var modifiedNoteTask by remember { mutableStateOf(noteById.task) }

        Surface(
            Modifier
                .fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TopMenu().TopMenuArea(stringResource(id = R.string.title_modify_note))
                ModifyText(
                    task = modifiedNoteTask,
                    onTaskChange = { value -> modifiedNoteTask = value })
                DeleteNoteByID(navController = navController, noteId)
            }
        }
    }

    @Composable
    fun ModifyText(task: String, onTaskChange: (String) -> Unit) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.write),
                contentDescription = "Modify text",
                modifier = Modifier.size(100.dp)
            )
            OutlinedTextField(
                value = task,
                onValueChange = onTaskChange,
                modifier = Modifier.padding(20.dp),
                colors = textFieldColors(
                    textColor = MaterialTheme.colors.primaryVariant
                ),
                label = {
                    Text(
                        text = "Modify the task",
                        color = MaterialTheme.colors.primary
                    )
                })
        }
    }

    @Composable
    fun DeleteNoteByID(navController: NavHostController, noteId: String?) {
        val context = LocalContext.current
        FloatingActionButton(backgroundColor = Red_Delete, onClick = {
            navController.navigate(NavRoute.Home.route)
            DatabaseToFile().deleteNoteById(context = context, id = noteId)
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_delete_forever_24),
                colorFilter = ColorFilter.tint(Black),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
    }
}