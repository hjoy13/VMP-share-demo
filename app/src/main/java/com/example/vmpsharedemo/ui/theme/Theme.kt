package com.example.vmpsharedemo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = VmpPurple,
    secondary = VmpPurpleDark
)

private val LightColorScheme = lightColorScheme(
    primary = VmpPurple,
    secondary = VmpPurpleDark,
    background = VmpBackground,
    surface = VmpSurface,
    onPrimary = VmpSurface,
    onBackground = VmpTextPrimary,
    onSurface = VmpTextPrimary
)

@Composable
fun VMPShareDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}