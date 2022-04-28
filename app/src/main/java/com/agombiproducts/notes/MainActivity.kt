package com.agombiproducts.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agombiproducts.notes.notes.CreateNoteView
import com.agombiproducts.notes.notes.RenderShowNotes
import com.agombiproducts.notes.routes.NavRoute
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

                }
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = NavRoute.Home.route) {

            composable(NavRoute.Home.route) {
                RenderShowNotes(navController = navController)
            }

            composable(NavRoute.Create.route) {
               CreateNoteView().CreateNoteView(navController = navController)
            }
        }
    }

}