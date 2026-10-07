package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.model.ThemeMode

val PortfolioDarkColorScheme = darkColorScheme(
    primary = PortfolioPureWhite,
    onPrimary = PortfolioNearBlack,
    primaryContainer = PortfolioDarkCardElevated,
    onPrimaryContainer = PortfolioTextPrimary,
    secondary = PortfolioTextSecondary,
    onSecondary = PortfolioDarkBackground,
    background = PortfolioDarkBackground,
    onBackground = PortfolioTextPrimary,
    surface = PortfolioDarkSurface,
    onSurface = PortfolioTextPrimary,
    surfaceVariant = PortfolioDarkSurfaceVariant,
    onSurfaceVariant = PortfolioTextSecondary,
    outline = PortfolioDarkBorder,
    outlineVariant = PortfolioDarkBorderSubtle
)

val PortfolioLightColorScheme = lightColorScheme(
    primary = PortfolioNearBlack,
    onPrimary = PortfolioPureWhite,
    primaryContainer = PortfolioLightSurfaceVariant,
    onPrimaryContainer = PortfolioLightTextPrimary,
    secondary = PortfolioLightTextSecondary,
    onSecondary = PortfolioLightBackground,
    background = PortfolioLightBackground,
    onBackground = PortfolioLightTextPrimary,
    surface = PortfolioLightSurface,
    onSurface = PortfolioLightTextPrimary,
    surfaceVariant = PortfolioLightSurfaceVariant,
    onSurfaceVariant = PortfolioLightTextSecondary,
    outline = PortfolioLightBorder,
    outlineVariant = Color(0xFFE0E0E0)
)

@Composable
fun DeveloperPortfolioTheme(
    themeMode: ThemeMode = ThemeMode.DARK,
    content: @Composable () -> Unit
) {
    val isDark = when (themeMode) {
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val colorScheme = if (isDark) PortfolioDarkColorScheme else PortfolioLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PortfolioTypography,
        content = content
    )
}
