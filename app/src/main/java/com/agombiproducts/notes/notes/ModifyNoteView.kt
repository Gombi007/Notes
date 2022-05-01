package com.agombiproducts.notes.notes

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.agombiproducts.notes.R

class ModifyNoteView {

    @Composable
    fun RenderModifyView(navController: NavHostController) {
        Surface(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            TopMenu().TopMenuArea(stringResource(id = R.string.title_modify_note))


        }
    }
}