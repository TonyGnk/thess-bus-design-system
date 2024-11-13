package com.tonyGnk.thessBus.designSystem.mobile.appStyles

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse


object AppColor {
    val primary: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary

    val onPrimary: Color
        @Composable
        get() = MaterialTheme.colorScheme.onPrimary

    val primaryContainer: Color
        @Composable
        get() = MaterialTheme.colorScheme.primaryContainer

    val onPrimaryContainer: Color
        @Composable
        get() = MaterialTheme.colorScheme.onPrimaryContainer

    val secondary: Color
        @Composable
        get() = MaterialTheme.colorScheme.secondary

    val onSecondary: Color
        @Composable
        get() = MaterialTheme.colorScheme.onSecondary

    val secondaryContainer: Color
        @Composable
        get() = MaterialTheme.colorScheme.secondaryContainer

    val onSecondaryContainer: Color
        @Composable
        get() = MaterialTheme.colorScheme.onSecondaryContainer

    val tertiary: Color
        @Composable
        get() = MaterialTheme.colorScheme.tertiary

    val onTertiary: Color
        @Composable
        get() = MaterialTheme.colorScheme.onTertiary

    val tertiaryContainer: Color
        @Composable
        get() = MaterialTheme.colorScheme.tertiaryContainer

    val onTertiaryContainer: Color
        @Composable
        get() = MaterialTheme.colorScheme.onTertiaryContainer

    val error: Color
        @Composable
        get() = MaterialTheme.colorScheme.error

    val onError: Color
        @Composable
        get() = MaterialTheme.colorScheme.onError

    val errorContainer: Color
        @Composable
        get() = MaterialTheme.colorScheme.errorContainer

    val onErrorContainer: Color
        @Composable
        get() = MaterialTheme.colorScheme.onErrorContainer

    val onSurface: Color
        @Composable
        get() = MaterialTheme.colorScheme.onSurface

    val surfaceLowest: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceContainerLowest

    val surfaceLow: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceContainerLow

    val surface: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceContainer

    val background: Color
        @Composable
        get() = MaterialTheme.colorScheme.background

    val outline: Color
        @Composable
        get() = MaterialTheme.colorScheme.outline

    val scrim: Color
        @Composable
        get() = MaterialTheme.colorScheme.scrim

    val inversePrimary: Color
        @Composable
        get() = MaterialTheme.colorScheme.inversePrimary

    val inverseSurface: Color
        @Composable
        get() = MaterialTheme.colorScheme.inverseSurface

    val inverseOnSurface: Color
        @Composable
        get() = MaterialTheme.colorScheme.inverseOnSurface

    val transparent: Color
        @Composable
        get() = Color.Transparent

    //____STATIC COLORS_______________________________
    val orange: Color
        @Composable
        get() = if (!isSystemInDarkTheme()) {
            Color(0xFFFF9800) // Light theme orange
        } else {
            Color(0xFFFFB74D) // Dark theme orange (slightly lighter for better visibility)
        }

    val blue: Color
        @Composable
        get() = if (!isSystemInDarkTheme()) {
            Color(0xFF2196F3) // Light theme blue
        } else {
            Color(0xFF64B5F6) // Dark theme blue (slightly lighter for better visibility)
        }

    val green: Color
        @Composable
        get() = if (!isSystemInDarkTheme()) {
            Color(0xFF4CAF50) // Light theme green
        } else {
            Color(0xFF81C784) // Dark theme green (slightly lighter for better visibility)
        }

    val red: Color
        @Composable
        get() = if (!isSystemInDarkTheme()) {
            Color(0xFFE57373) // Light theme red
        } else {
            Color(0xFFE57373) // Dark theme red
        }

}


@Composable
fun contentColorFor(backgroundColor: Color) =
    MaterialTheme.colorScheme.contentColorFor(backgroundColor).takeOrElse {
        LocalContentColor.current
    }

enum class ColorOptions {
    Primary, Secondary, Green, Blue, Orange, Red
}


@Composable
fun ColorOptions.color() = when (this) {
    ColorOptions.Primary -> AppColor.primary
    ColorOptions.Secondary -> AppColor.secondary
    ColorOptions.Green -> AppColor.green
    ColorOptions.Blue -> AppColor.blue
    ColorOptions.Orange -> AppColor.orange
    ColorOptions.Red -> AppColor.red
}
