package com.agombiproducts.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.agombiproducts.notes.database.Dummy
import com.agombiproducts.notes.models.Note
import com.agombiproducts.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Notes(notes = Dummy.testNotes.notes)
                }
            }
        }
    }

    @Composable
    fun NoteView(note: Note) {
        Surface() {
            Column() {
                Text(text = note.task)
            }

        }
    }

    @Composable
    fun Notes(notes: List<Note>) {
        LazyColumn {
            items(notes) { note ->
                NoteView(note = note)
            }
        }
    }

}