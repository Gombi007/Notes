package com.agombiproducts.notes.notes


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.agombiproducts.notes.R
import com.agombiproducts.notes.database.FormattedDate
import com.agombiproducts.notes.models.Note
import com.agombiproducts.notes.routes.NavRoute
import com.agombiproducts.notes.ui.theme.Green_Save

@Composable
fun CreateNoteView(navController: NavHostController) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxHeight()) {
        Column() {
            TopMenuArea(title = stringResource(id = R.string.title_create_note))
            NoteTaskInput(navController = navController)

        }
    }

}


@Composable
fun NoteTaskInput(navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    var urgent by remember { mutableStateOf(false) }
    var done by remember { mutableStateOf(false) }
    var note = Note(null, text, urgent, done, null, null)


    androidx.compose.material.Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 15.dp),
        color = MaterialTheme.colors.background
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.write),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(100.dp)

            )
            OutlinedTextField(
                value = text,
                modifier = Modifier.padding(20.dp),
                onValueChange = { text = it },
                label = {
                    Text(text = stringResource(id = R.string.txt_task_hint))
                },
                textStyle = TextStyle(color = MaterialTheme.colors.primary)

            )

            Spacer(modifier = Modifier.height(40.dp))

            Row() {
                Surface(
                    color = MaterialTheme.colors.onBackground,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.clickable { urgent = !urgent },
                ) {
                    Row(modifier = Modifier.padding(5.dp, 10.dp)) {
                        Text(
                            text = stringResource(id = R.string.switch_urgent),
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(10.dp, 0.dp)
                        )
                        Switch(checked = urgent, onCheckedChange = { urgent = it })
                    }

                }

                Spacer(modifier = Modifier.width(50.dp))

                Surface(
                    color = MaterialTheme.colors.onBackground,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.clickable { done = !done },
                ) {
                    Row(modifier = Modifier.padding(5.dp, 10.dp)) {
                        Text(
                            text = stringResource(id = R.string.switch_done),
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(10.dp, 0.dp)
                        )
                        Switch(checked = done, onCheckedChange = { done = it })
                    }
                }
            }
            BoxWithConstraints(
                Modifier
                    .offset(10.dp, 40.dp)
            ) {
                SaveNoteData(navController = navController, note = note)
            }
        }
    }

}

@Composable
fun SaveNoteData(navController: NavHostController, note: Note) {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = {
            collectAllNoteData(context = context, note)
            navController.navigate(NavRoute.Home.route)
        }, backgroundColor = Green_Save
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_check_24),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)

        )
    }
}


private fun collectAllNoteData(context: Context, note: Note) {
    val currentDate = FormattedDate.getCurrentDate()
    val id = "AAAAAA"
    note.id = id
    note.created = currentDate
    note.modified = currentDate
    Toast.makeText(context, note.toString(), Toast.LENGTH_SHORT).show()
}