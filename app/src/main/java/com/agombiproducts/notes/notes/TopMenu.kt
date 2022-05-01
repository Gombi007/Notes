package com.agombiproducts.notes.notes

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TopMenu {

    @Composable
    fun TopMenuArea(title: String) {
        androidx.compose.material.Surface(
            color = MaterialTheme.colors.onBackground,
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
}