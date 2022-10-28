package com.example.gymresultsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = prussianBlue,
    primaryVariant = blueGray,
    background = backgroundColor,
    secondary = oxfordBlue,
    onSurface = Color.White,
    onPrimary = lightGray,
    onSecondary = Color.Black,
    onBackground = darkGray

)

@Composable
fun GymResultsAppTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}