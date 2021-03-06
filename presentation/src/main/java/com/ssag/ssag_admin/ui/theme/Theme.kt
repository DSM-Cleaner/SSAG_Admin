package com.ssag.ssag_admin.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue700,
    surface = Color.White,
    secondary = Purple200,
    secondaryVariant = Purple700
)

@Composable
fun SSAG_AdminTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}