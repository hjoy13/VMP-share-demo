package com.example.vmpsharedemo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Demo is light only: VMP's screens are light, so no dark scheme.
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
fun VMPShareDemoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}