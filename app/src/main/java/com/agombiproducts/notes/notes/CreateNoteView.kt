package com.agombiproducts.notes.notes


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
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
import com.agombiproducts.notes.database.DatabaseToFile
import com.agombiproducts.notes.database.FormattedDate
import com.agombiproducts.notes.database.IdGenerator
import com.agombiproducts.notes.models.Note
import com.agombiproducts.notes.routes.NavRoute
import com.agombiproducts.notes.ui.theme.Green_Save
import com.agombiproducts.notes.ui.theme.Urgent

class CreateNoteView {

    @Composable
    fun RenderCreateNoteView(navController: NavHostController) {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxHeight()) {
            Column() {
                TopMenu().TopMenuArea(title = stringResource(id = R.string.title_create_note))
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
        var isCorrectLengthText = text.length >= 3 || text.isEmpty()

        Surface(
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
                    colors = textFieldColors(
                        focusedIndicatorColor = if (isCorrectLengthText) MaterialTheme.colors.primary else Urgent,
                    ),
                    label = {
                        Text(
                            color = if (isCorrectLengthText) MaterialTheme.colors.primary else Urgent,
                            text =
                            if (isCorrectLengthText)
                                stringResource(id = R.string.txt_task_hint)
                            else
                                stringResource(id = R.string.error_input_text_length)
                        )
                    },
                    textStyle = TextStyle(MaterialTheme.colors.primary)
                )

                Spacer(modifier = Modifier.height(40.dp))

                Row() {
                    Surface(
                        color = MaterialTheme.colors.onBackground,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = { urgent = !urgent })

                    ) {
                        Row(modifier = Modifier.padding(5.dp, 10.dp)) {
                            Text(
                                text = stringResource(id = R.string.switch_urgent),
                                color = MaterialTheme.colors.primary,
                                modifier = Modifier
                                    .padding(10.dp, 0.dp)
                                    .width(50.dp)
                            )
                            Switch(
                                checked = urgent,
                                onCheckedChange = { urgent = it }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(50.dp))

                    Surface(
                        color = MaterialTheme.colors.onBackground,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = { done = !done })

                    ) {
                        Row(modifier = Modifier.padding(5.dp, 10.dp)) {
                            Text(
                                text = stringResource(id = R.string.switch_done),
                                color = MaterialTheme.colors.primary,
                                modifier = Modifier
                                    .padding(10.dp, 0.dp)
                                    .width(50.dp)
                            )
                            Switch(
                                checked = done,
                                onCheckedChange = { done = it }
                            )
                        }
                    }
                }

                BoxWithConstraints(
                    Modifier
                        .offset(10.dp, 40.dp)
                ) {
                    SaveNoteData(
                        navController = navController,
                        note = note
                    )
                }
            }
        }

    }

    @Composable
    fun SaveNoteData(navController: NavHostController, note: Note) {
        val context = LocalContext.current
        FloatingActionButton(
            onClick = {
                if (note.task.length >= 3) {
                    collectAllNoteData(context = context, note)
                    navController.navigate(NavRoute.Home.route)
                } else {
                    Toast.makeText(context, R.string.error_input_text_empty, Toast.LENGTH_SHORT)
                        .show()
                }
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
        note.created = currentDate
        note.modified = currentDate

        var id = IdGenerator().getNewId()
        val allExistingIdInTheDatabase = DatabaseToFile().readAllId(context)
        if (allExistingIdInTheDatabase.isNotEmpty()) {
            while (allExistingIdInTheDatabase.contains(id)) {
                id = IdGenerator().getNewId()
            }
        }
        note.id = id
        DatabaseToFile().writer(context, note)
    }


}


