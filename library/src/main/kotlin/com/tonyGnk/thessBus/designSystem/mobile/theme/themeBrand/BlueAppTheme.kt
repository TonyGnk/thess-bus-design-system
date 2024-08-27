package com.tonyGnk.thessBus.designSystem.mobile.theme.themeBrand

import androidx.annotation.VisibleForTesting
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.theme.appTypography

private val primaryLight = Color(0xFF2C638B)
private val onPrimaryLight = Color(0xFFFFFFFF)
private val primaryContainerLight = Color(0xFFCCE5FF)
private val onPrimaryContainerLight = Color(0xFF001D31)
private val secondaryLight = Color(0xFF51606F)
private val onSecondaryLight = Color(0xFFFFFFFF)
private val secondaryContainerLight = Color(0xFFD4E4F6)
private val onSecondaryContainerLight = Color(0xFF0D1D2A)
private val tertiaryLight = Color(0xFF05677E)
private val onTertiaryLight = Color(0xFFFFFFFF)
private val tertiaryContainerLight = Color(0xFFB5EBFF)
private val onTertiaryContainerLight = Color(0xFF001F28)
private val errorLight = Color(0xFFBA1A1A)
private val onErrorLight = Color(0xFFFFFFFF)
private val errorContainerLight = Color(0xFFFFDAD6)
private val onErrorContainerLight = Color(0xFF410002)
private val backgroundLight = Color(0xFFF7F9FF)
private val onBackgroundLight = Color(0xFF181C20)
private val surfaceLight = Color(0xFFF7F9FF)
private val onSurfaceLight = Color(0xFF181C20)
private val surfaceVariantLight = Color(0xFFDEE3EB)
private val onSurfaceVariantLight = Color(0xFF42474E)
private val outlineLight = Color(0xFF72787E)
private val outlineVariantLight = Color(0xFFC2C7CE)
private val scrimLight = Color(0xFF000000)
private val inverseSurfaceLight = Color(0xFF2D3135)
private val inverseOnSurfaceLight = Color(0xFFEEF1F6)
private val inversePrimaryLight = Color(0xFF99CCFA)
private val surfaceDimLight = Color(0xFFD7DADF)
private val surfaceBrightLight = Color(0xFFF7F9FF)
private val surfaceContainerLowestLight = Color(0xFFFFFFFF)
private val surfaceContainerLowLight = Color(0xFFF1F4F9)
private val surfaceContainerLight = Color(0xFFEBEEF3)
private val surfaceContainerHighLight = Color(0xFFE6E8EE)
private val surfaceContainerHighestLight = Color(0xFFE0E2E8)

private val primaryDark = Color(0xFF99CCFA)
private val onPrimaryDark = Color(0xFF003351)
private val primaryContainerDark = Color(0xFF084B72)
private val onPrimaryContainerDark = Color(0xFFCCE5FF)
private val secondaryDark = Color(0xFFB8C8DA)
private val onSecondaryDark = Color(0xFF23323F)
private val secondaryContainerDark = Color(0xFF394857)
private val onSecondaryContainerDark = Color(0xFFD4E4F6)
private val tertiaryDark = Color(0xFF87D1EB)
private val onTertiaryDark = Color(0xFF003543)
private val tertiaryContainerDark = Color(0xFF004E60)
private val onTertiaryContainerDark = Color(0xFFB5EBFF)
private val errorDark = Color(0xFFFFB4AB)
private val onErrorDark = Color(0xFF690005)
private val errorContainerDark = Color(0xFF93000A)
private val onErrorContainerDark = Color(0xFFFFDAD6)
private val backgroundDark = Color(0xFF101418)
private val onBackgroundDark = Color(0xFFE0E2E8)
private val surfaceDark = Color(0xFF101418)
private val onSurfaceDark = Color(0xFFE0E2E8)
private val surfaceVariantDark = Color(0xFF42474E)
private val onSurfaceVariantDark = Color(0xFFC2C7CE)
private val outlineDark = Color(0xFF8C9198)
private val outlineVariantDark = Color(0xFF42474E)
private val scrimDark = Color(0xFF000000)
private val inverseSurfaceDark = Color(0xFFE0E2E8)
private val inverseOnSurfaceDark = Color(0xFF2D3135)
private val inversePrimaryDark = Color(0xFF2C638B)
private val surfaceDimDark = Color(0xFF101418)
private val surfaceBrightDark = Color(0xFF36393E)
private val surfaceContainerLowestDark = Color(0xFF0B0F12)
private val surfaceContainerLowDark = Color(0xFF181C20)
private val surfaceContainerDark = Color(0xFF1C2024)
private val surfaceContainerHighDark = Color(0xFF272A2E)
private val surfaceContainerHighestDark = Color(0xFF313539)

@VisibleForTesting
private val LightAndroidColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

@VisibleForTesting
private val DarkAndroidColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val font = FontFamily(
    Font(R.font.inter_four_variable),
)


internal data object BlueAppTheme : AppTheme(
    lightColorScheme = LightAndroidColorScheme,
    darkColorScheme = DarkAndroidColorScheme,
    typography = appTypography(font)
)
