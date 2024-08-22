package com.tonyGnk.thessBus.designSystem.mobile.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.tonyGnk.thessBus.designSystem.mobile.theme.themeBrand.GreenAppTheme


@Composable
fun ClpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
   // brand: ThemeBrand = ThemeBrand.BLUE,
    useTotalBlack: Boolean = false,
    content: @Composable () -> Unit,
) {
//    val appTheme = when (brand) {
//        ThemeBrand.DEFAULT -> com.tonygnk.thessbus.designsystem.mobile.theme.themeBrand.getDynamicTheme(LocalContext.current)
//        ThemeBrand.GREEN -> com.tonygnk.thessbus.designsystem.mobile.theme.themeBrand.GreenAppTheme
//        ThemeBrand.BLUE -> com.tonygnk.thessbus.designsystem.mobile.theme.themeBrand.BlueAppTheme
//    }
    val appTheme = GreenAppTheme

    var colorScheme = when (darkTheme) {
        true -> appTheme.darkColorScheme
        false -> appTheme.lightColorScheme
    }

    if (darkTheme) {
        colorScheme = colorScheme.copy(
            surfaceContainerLowest = colorScheme.surfaceContainer
        )

        colorScheme = colorScheme.copy(
            surfaceContainer = when (useTotalBlack) {
                true -> Color.Black
                false -> colorScheme.background
            }
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTheme.typography,
        content = content,
    )
}
