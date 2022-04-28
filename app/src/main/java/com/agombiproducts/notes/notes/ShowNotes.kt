package com.agombiproducts.notes.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.agombiproducts.notes.R
import com.agombiproducts.notes.database.Dummy
import com.agombiproducts.notes.models.Note
import com.agombiproducts.notes.routes.NavRoute
import com.agombiproducts.notes.ui.theme.*


@Composable
fun RenderShowNotes(navController: NavHostController) {
    Notes(notes = Dummy.testNotes.notes)
    BoxWithConstraints(
        Modifier
            .offset(
                310.dp, 560.dp
            )
            .padding(
                0.dp, 15.dp
            )
    ) {
        CreateNote(navController)
    }
}


@Composable
fun CreateNote(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(NavRoute.Create.route)
        }, backgroundColor = Gold
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
    Surface(
        modifier = Modifier
            .padding(15.dp, 5.dp)
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(17.dp)),
        color = NoteBG,
        shape = RoundedCornerShape(17.dp)
    ) {
        Column(
            modifier = Modifier.padding(5.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = note.task, color = White)
            Text(text = note.modified.toString(), color = White)
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
