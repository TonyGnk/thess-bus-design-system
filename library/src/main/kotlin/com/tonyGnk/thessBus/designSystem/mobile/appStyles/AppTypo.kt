package com.tonyGnk.thessBus.designSystem.mobile.appStyles

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTypo {
    val displayLarge: TextStyle
        @Composable
        get() = MaterialTheme.typography.displayLarge.copy(color = AppColor.onSurface)

    val displayMedium: TextStyle
        @Composable
        get() = MaterialTheme.typography.displayMedium.copy(color = AppColor.onSurface)

    val displaySmall: TextStyle
        @Composable
        get() = MaterialTheme.typography.displaySmall.copy(color = AppColor.onSurface)

    val headlineLarge: TextStyle
        @Composable
        get() = MaterialTheme.typography.headlineLarge.copy(color = AppColor.onSurface)

    val headlineMedium: TextStyle
        @Composable
        get() = MaterialTheme.typography.headlineMedium.copy(color = AppColor.onSurface)

    val headlineSmall: TextStyle
        @Composable
        get() = MaterialTheme.typography.headlineSmall.copy(color = AppColor.onSurface)

    val titleLarge: TextStyle
        @Composable
        get() = MaterialTheme.typography.titleLarge.copy(color = AppColor.onSurface)

    val titleMedium: TextStyle
        @Composable
        get() = MaterialTheme.typography.titleMedium.copy(color = AppColor.onSurface)

    val titleSmall: TextStyle
        @Composable
        get() = MaterialTheme.typography.titleSmall.copy(color = AppColor.onSurface)

    val bodyLarge: TextStyle
        @Composable
        get() = MaterialTheme.typography.bodyLarge.copy(color = AppColor.onSurface)

    val bodyMedium: TextStyle
        @Composable
        get() = MaterialTheme.typography.bodyMedium.copy(color = AppColor.onSurface)

    val bodySmall: TextStyle
        @Composable
        get() = MaterialTheme.typography.bodySmall.copy(color = AppColor.onSurface)

    val labelLarge: TextStyle
        @Composable
        get() = MaterialTheme.typography.labelLarge.copy(color = AppColor.onSurface)

    val labelMedium: TextStyle
        @Composable
        get() = MaterialTheme.typography.labelMedium.copy(color = AppColor.onSurface)

    val labelSmall: TextStyle
        @Composable
        get() = MaterialTheme.typography.labelSmall.copy(color = AppColor.onSurface)

    val appBar: TextStyle
        @Composable
        get() = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            color = AppColor.onSurface
        )

    val topBar: TextStyle
        @Composable
        get() = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.W600,
            fontSize = 20.sp,
            color = AppColor.onSurface
        )
}
