package com.tonyGnk.thessBus.designSystem.mobile.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.tonyGnk.thessBus.designSystem.mobile.theme.themeBrand.BlueAppTheme


@Composable
fun ClpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // brand: ThemeBrand = ThemeBrand.BLUE,
    useTotalBlack: Boolean = false,
    //fillMaxSize: Boolean = false,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
//    val appTheme = when (brand) {
//        ThemeBrand.DEFAULT -> com.tonygnk.thessbus.designsystem.mobile.theme.themeBrand.getDynamicTheme(LocalContext.current)
//        ThemeBrand.GREEN -> com.tonygnk.thessbus.designsystem.mobile.theme.themeBrand.GreenAppTheme
//        ThemeBrand.BLUE -> com.tonygnk.thessbus.designsystem.mobile.theme.themeBrand.BlueAppTheme
//    }
    //if android sdk >=31
    val appTheme = BlueAppTheme //getDynamicTheme(LocalContext.current)

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

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.navigationBarColor = colorScheme.surfaceContainer.toArgb()

            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTheme.typography,
    ) {
        ClsBackground(content = content, modifier = modifier)
    }
}
