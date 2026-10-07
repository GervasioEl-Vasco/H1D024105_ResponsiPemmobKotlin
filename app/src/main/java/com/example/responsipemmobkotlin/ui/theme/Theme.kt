package com.example.responsipemmobkotlin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = NavyBlue,
    onPrimary = OnNavy,
    primaryContainer = CreamWhite,
    onPrimaryContainer = NavyDark,
    secondary = TealAccent,
    onSecondary = OnNavy,
    secondaryContainer = Color(0xFFB2DFDB),
    onSecondaryContainer = TealDark,
    tertiary = WarmOrange,
    onTertiary = OnNavy,
    background = CreamWhite,
    onBackground = OnSurfaceLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = Color(0xFFE0E7EF),
    onSurfaceVariant = SoftGray,
    error = ErrorRed,
    onError = OnNavy
)

private val DarkColorScheme = darkColorScheme(
    primary = NavyLight,
    onPrimary = NavyDark,
    primaryContainer = NavyDark,
    onPrimaryContainer = NavyLight,
    secondary = TealLight,
    onSecondary = TealDark,
    secondaryContainer = TealDark,
    onSecondaryContainer = TealLight,
    tertiary = WarmOrangeLight,
    onTertiary = Color(0xFF4A1800),
    background = SurfaceDark,
    onBackground = OnSurfaceDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = Color(0xFF1E2A36),
    onSurfaceVariant = Color(0xFFB0BEC5),
    error = Color(0xFFCF6679),
    onError = Color(0xFF690020)
)

@Composable
fun ResponsiPemmobKotlinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}