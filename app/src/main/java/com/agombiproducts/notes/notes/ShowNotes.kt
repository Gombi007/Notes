package com.agombiproducts.notes.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.agombiproducts.notes.R
import com.agombiproducts.notes.database.DatabaseToFile
import com.agombiproducts.notes.models.Note
import com.agombiproducts.notes.routes.NavRoute
import com.agombiproducts.notes.ui.theme.Black
import com.agombiproducts.notes.ui.theme.Gold_500
import com.agombiproducts.notes.ui.theme.NonUrgent
import com.agombiproducts.notes.ui.theme.Urgent

class ShowNotes {

    lateinit var classLevelNavController: NavHostController;

    @Composable
    fun RenderShowNotes(navController: NavHostController) {
        classLevelNavController = navController;
        val context = LocalContext.current
        val readAllNotesFromFile = DatabaseToFile().reader(context)

        Column() {
            TopMenu().TopMenuArea(stringResource(id = R.string.app_name))
            Notes(notes = readAllNotesFromFile)
        }
        CreateNoteButton()
    }


    @Composable
    fun CreateNoteButton() {
        FloatingActionButton(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            modifier = Modifier.offset(315.dp, 640.dp),
            onClick = {
                classLevelNavController.navigate(NavRoute.Create.route)
            },
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                colorFilter = ColorFilter.tint(Black) ,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
    }

    @Composable
    fun NoteView(note: Note) {
        val textColor = if (note.urgent) Urgent else NonUrgent
        var isExpanded by remember { mutableStateOf(false) }

        Surface(
            modifier = Modifier
                .padding(15.dp, 10.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colors.onBackground,
            shape = RoundedCornerShape(17.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(0.dp, 10.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = { isExpanded = !isExpanded },
                            onLongPress = { classLevelNavController.navigate(NavRoute.Modify.route) }
                        )
                    }

            ) {
                Text(
                    text = note.task,
                    Modifier.padding(15.dp, 3.dp),
                    color = textColor,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
                Text(text = note.modified.toString(), color = textColor)
            }
        }
    }

    @Composable
    fun Notes(notes: List<Note>) {
        LazyColumn(Modifier.padding(0.dp, 15.dp)) {
            items(notes) { note ->
                NoteView(note = note)
            }
        }
    }

}

