package com.agombiproducts.notes.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.agombiproducts.notes.R
import com.agombiproducts.notes.database.Dummy
import com.agombiproducts.notes.models.Note
import com.agombiproducts.notes.routes.NavRoute
import com.agombiproducts.notes.ui.theme.NonUrgent
import com.agombiproducts.notes.ui.theme.Urgent


@Composable
fun RenderShowNotes(navController: NavHostController) {
    Column() {
        TopMenuArea(stringResource(id = R.string.app_name))
        Notes(notes = Dummy.Data.notes)
    }
    BoxWithConstraints(
        Modifier
            .offset(
                330.dp, 640.dp
            )
            .padding(
                0.dp, 15.dp
            )
    ) {
        CreateNoteButton(navController)
    }
}


@Composable
fun CreateNoteButton(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(NavRoute.Create.route)
        }, backgroundColor = MaterialTheme.colors.secondaryVariant
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_add_24),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)

        )
    }
}

@Composable
fun NoteView(note: Note) {
    val borderColor = if (note.urgent) Urgent else NonUrgent
    var isExpanded by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .padding(15.dp, 5.dp)
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(17.dp)),
        color = MaterialTheme.colors.onBackground,
        shape = RoundedCornerShape(17.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .clickable { isExpanded = !isExpanded },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.task,
                Modifier.padding(15.dp, 10.dp),
                color = MaterialTheme.colors.primary,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1
            )
            Text(text = note.modified.toString(), color = MaterialTheme.colors.primary)
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

@Composable
fun TopMenuArea(title: String) {
    androidx.compose.material.Surface(
        color = MaterialTheme.colors.onBackground,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row() {
                Text(
                    text = title,
                    color = MaterialTheme.colors.primary,
                    fontSize = 22.sp
                )
            }
        }
    }
}

