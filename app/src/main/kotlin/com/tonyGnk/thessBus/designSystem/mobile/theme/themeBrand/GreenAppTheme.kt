package com.tonyGnk.thessBus.designSystem.mobile.theme.themeBrand

import androidx.annotation.VisibleForTesting
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.theme.appTypography

private val primaryLight = Color(0xFF236A4C)
private val onPrimaryLight = Color(0xFFFFFFFF)
private val primaryContainerLight = Color(0xFFAAF2CB)
private val onPrimaryContainerLight = Color(0xFF002113)
private val secondaryLight = Color(0xFF4D6356)
private val onSecondaryLight = Color(0xFFFFFFFF)
private val secondaryContainerLight = Color(0xFFD0E8D8)
private val onSecondaryContainerLight = Color(0xFF0A1F15)
private val tertiaryLight = Color(0xFF3C6472)
private val onTertiaryLight = Color(0xFFFFFFFF)
private val tertiaryContainerLight = Color(0xFFC0E9FA)
private val onTertiaryContainerLight = Color(0xFF001F28)
private val errorLight = Color(0xFFBA1A1A)
private val onErrorLight = Color(0xFFFFFFFF)
private val errorContainerLight = Color(0xFFFFDAD6)
private val onErrorContainerLight = Color(0xFF410002)
private val backgroundLight = Color(0xFFF5FBF4)
private val onBackgroundLight = Color(0xFF171D19)
private val surfaceLight = Color(0xFFF5FBF4)
private val onSurfaceLight = Color(0xFF171D19)
private val surfaceVariantLight = Color(0xFFDCE5DD)
private val onSurfaceVariantLight = Color(0xFF404943)
private val outlineLight = Color(0xFF707973)
private val outlineVariantLight = Color(0xFFC0C9C1)
private val scrimLight = Color(0xFF000000)
private val inverseSurfaceLight = Color(0xFF2C322E)
private val inverseOnSurfaceLight = Color(0xFFEDF2EC)
private val inversePrimaryLight = Color(0xFF8FD5B0)
private val surfaceDimLight = Color(0xFFD6DBD5)
private val surfaceBrightLight = Color(0xFFF5FBF4)
private val surfaceContainerLowestLight = Color(0xFFFFFFFF)
private val surfaceContainerLowLight = Color(0xFFF0F5EF)
private val surfaceContainerLight = Color(0xFFEAEFE9)
private val surfaceContainerHighLight = Color(0xFFE4EAE3)
private val surfaceContainerHighestLight = Color(0xFFDEE4DE)

private val primaryDark = Color(0xFF8FD5B0)
private val onPrimaryDark = Color(0xFF003824)
private val primaryContainerDark = Color(0xFF005235)
private val onPrimaryContainerDark = Color(0xFFAAF2CB)
private val secondaryDark = Color(0xFFB4CCBC)
private val onSecondaryDark = Color(0xFF20352A)
private val secondaryContainerDark = Color(0xFF364B3F)
private val onSecondaryContainerDark = Color(0xFFD0E8D8)
private val tertiaryDark = Color(0xFFA4CDDE)
private val onTertiaryDark = Color(0xFF063543)
private val tertiaryContainerDark = Color(0xFF234C5A)
private val onTertiaryContainerDark = Color(0xFFC0E9FA)
private val errorDark = Color(0xFFFFB4AB)
private val onErrorDark = Color(0xFF690005)
private val errorContainerDark = Color(0xFF93000A)
private val onErrorContainerDark = Color(0xFFFFDAD6)
private val backgroundDark = Color(0xFF0F1511)
private val onBackgroundDark = Color(0xFFDEE4DE)
private val surfaceDark = Color(0xFF0F1511)
private val onSurfaceDark = Color(0xFFDEE4DE)
private val surfaceVariantDark = Color(0xFF404943)
private val onSurfaceVariantDark = Color(0xFFC0C9C1)
private val outlineDark = Color(0xFF8A938C)
private val outlineVariantDark = Color(0xFF404943)
private val scrimDark = Color(0xFF000000)
private val inverseSurfaceDark = Color(0xFFDEE4DE)
private val inverseOnSurfaceDark = Color(0xFF2C322E)
private val inversePrimaryDark = Color(0xFF236A4C)
private val surfaceDimDark = Color(0xFF0F1511)
private val surfaceBrightDark = Color(0xFF353B36)
private val surfaceContainerLowestDark = Color(0xFF0A0F0C)
private val surfaceContainerLowDark = Color(0xFF171D19)
private val surfaceContainerDark = Color(0xFF1B211D)
private val surfaceContainerHighDark = Color(0xFF262B27)
private val surfaceContainerHighestDark = Color(0xFF303632)

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
    Font(R.font.google_sans_regular),
)


internal data object GreenAppTheme : AppTheme(
    lightColorScheme = LightAndroidColorScheme,
    darkColorScheme = DarkAndroidColorScheme,
    typography = appTypography(font)
)
