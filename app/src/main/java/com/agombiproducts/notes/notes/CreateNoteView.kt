package com.agombiproducts.notes.notes

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.agombiproducts.notes.ui.theme.Gold

@Composable
fun CreateNoteView(navController: NavHostController) {
    Text(text = "Hello create", color = Gold)

}