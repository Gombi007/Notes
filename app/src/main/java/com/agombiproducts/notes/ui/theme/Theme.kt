package com.agombiproducts.notes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Blue_200,
    primaryVariant = Blue_500,
    secondary = Gold_200,
    onSurface = Blue_200,
    secondaryVariant = Gold_500,
    onBackground = Gray_deep,
)

private val LightColorPalette = lightColors(
    primary = Blue_200,
    primaryVariant = Blue_500,
    secondary = Gold_200,
    onSurface = Blue_200,
    secondaryVariant = Gold_500,
    onBackground = Gray_deep,
    background = Black,

    /* Other default colors to override
    background = Color.White,

    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,

    //testing light colors
        primary = Blue_200,
    primaryVariant = Blue_500,
    onPrimary = White,
    secondary = Gold_200,
    secondaryVariant = Gold_500,
    background = Light_Background,
    onBackground = Light_OnBackground
    */
)

@Composable
fun NotesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}