package com.agombiproducts.notes.notes


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
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
        var task by remember { mutableStateOf("") }
        var urgent by remember { mutableStateOf(false) }
        var done by remember { mutableStateOf(false) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            TopMenu().TopMenuArea(title = stringResource(id = R.string.title_create_note))
            InputFieldWithPicture(task = task, onTaskChange = { value -> task = value })
            Spacer(modifier = Modifier.height(30.dp))
            Row() {
                SwitchSelector(
                    label = stringResource(id = R.string.switch_urgent),
                    checked = urgent,
                    onCheckedChange = { value -> urgent = value })
                Spacer(modifier = Modifier.width(50.dp))
                SwitchSelector(
                    label = stringResource(id = R.string.switch_done),
                    checked = done,
                    onCheckedChange = { value -> done = value })
            }
            Spacer(modifier = Modifier.height(30.dp))
            SaveNoteData(navController, task, urgent, done)
        }
    }

    @Composable
    fun InputFieldWithPicture(task: String, onTaskChange: (String) -> Unit) {
        val isCorrectLengthText = task.length >= 3 || task.isEmpty()

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.write),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(100.dp)
            )
            OutlinedTextField(
                value = task,
                modifier = Modifier.padding(20.dp),
                onValueChange = onTaskChange,
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
        }
    }


    @Composable
    fun SwitchSelector(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
        Card(
            backgroundColor = MaterialTheme.colors.onBackground,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .height(40.dp)
                .width(120.dp)
                .toggleable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    value = checked,
                    onValueChange = onCheckedChange
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = label,
                    color = MaterialTheme.colors.primary
                )
                Switch(
                    checked = checked,
                    onCheckedChange = onCheckedChange
                )
            }
        }
    }

    @Composable
    fun SaveNoteData(
        navController: NavHostController,
        task: String,
        urgent: Boolean,
        done: Boolean
    ) {
        val context = LocalContext.current
        FloatingActionButton(
            onClick = {
                if (task.length >= 3) {
                    collectAllNoteData(context, task, urgent, done)
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

    private fun collectAllNoteData(context: Context, task: String, urgent: Boolean, done: Boolean) {
        val currentDate = FormattedDate.getCurrentDate()
        var id = IdGenerator().getNewId()
        val allExistingIdInTheDatabase = DatabaseToFile().readAllId(context)

        if (allExistingIdInTheDatabase.isNotEmpty()) {
            while (allExistingIdInTheDatabase.contains(id)) {
                id = IdGenerator().getNewId()
            }
        }

        val newNote = Note(id, task, urgent, done, currentDate, currentDate)
        DatabaseToFile().writer(context, newNote)
    }
}







