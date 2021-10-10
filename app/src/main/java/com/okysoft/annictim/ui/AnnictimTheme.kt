package com.okysoft.annictim

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.okysoft.annictim.ui.annictimTypography

private val primaryColor = Color(0xFF212121)
private val primaryAccent = Color(0xFFFF4081)

private val LightThemeColors = lightColors(
    primary = Color(0xff6200ee),
    primaryVariant = Color(0xFF3700b3),
    secondary = Color(0xFF03dac6),
    secondaryVariant = Color(0xff018786),
    background = Color(0xffffffff),
    surface = Color(0xffffffff),
    error = Color(0xffb00020),
    onPrimary = Color(0xffffffff),
    onSecondary = Color(0xffffffff),
    onBackground = Color(0xff000000),
    onSurface = Color(0xff000000),
    onError = Color(0xffffffff),
)

private val DarkThemeColors = darkColors(
    primary = primaryColor,
    primaryVariant = primaryAccent
)

@Composable
fun AnnictimTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = annictimTypography,
        content = content
    )
}