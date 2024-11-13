package com.tonyGnk.thessBus.designSystem.mobile.theme

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.tonyGnk.thessBus.designSystem.mobile.theme.themeBrand.BlueAppTheme
import com.tonyGnk.thessBus.designSystem.mobile.theme.themeBrand.getDynamicTheme


@Composable
fun ThessBusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // brand: ThemeBrand = ThemeBrand.BLUE,
    useTotalBlack: Boolean = false,
    includeBackgroundPane: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
//    val appTheme = when (brand) {
//        ThemeBrand.DEFAULT -> com.tonygnk.thessbus.designsystem.mobile.theme.themeBrand.getDynamicTheme(LocalContext.current)
//        ThemeBrand.GREEN -> com.tonygnk.thessbus.designsystem.mobile.theme.themeBrand.GreenAppTheme
//        ThemeBrand.BLUE -> com.tonygnk.thessbus.designsystem.mobile.theme.themeBrand.BlueAppTheme
//    }
    //if android sdk >=31
    val appTheme =
        //    getDynamicTheme(LocalContext.current)
        BlueAppTheme

    var colorScheme = when (darkTheme) {
        true -> appTheme.darkColorScheme
        false -> appTheme.lightColorScheme
    }

    when (darkTheme) {
        true -> {
            colorScheme = colorScheme.copy(
                //Ignore the real surfaceContainerHighest
                surfaceContainerLowest =  //colorScheme.surfaceContainer,
//                averageColor(
//                    colorScheme.surfaceContainerHigh, //High
//                    colorScheme.surfaceContainer, //Container
//                ),
                averageColor(
                    colorScheme.surfaceContainer,
                    colorScheme.surfaceContainerLow, //Container
                ),

                surfaceContainerLow = //colorScheme.surfaceContainerLow, //*
//                averageColor(
//                    colorScheme.surfaceContainer, //Container
//                    colorScheme.surface, // Surface
//                ),
                averageColor(
                    colorScheme.surfaceContainerLow,
                    colorScheme.surface, //Container
                ),
                surfaceContainer = colorScheme.surface, //*
//                        averageColor(
//                    colorScheme.surface,
//                    colorScheme.surfaceContainerLow //Surface and SurfaceContainerLow
//                ),

                background = colorScheme.surfaceContainerLowest
//                averageColor(
//                    colorScheme.surfaceContainerLow,
//                    colorScheme.surfaceContainerLowest//low and lowest
//                ),
            )
        }

        false -> {
            colorScheme = colorScheme.copy(
                //Ignore the real surfaceContainerHighest
                surfaceContainerLowest = averageColor(
                    colorScheme.surfaceContainerLowest, colorScheme.surface
                ),
                surfaceContainerLow = averageColor(
                    colorScheme.surface, colorScheme.surfaceContainerLow
                ),
                surfaceContainer = averageColor(
                    colorScheme.surfaceContainerLow,
                    colorScheme.surfaceContainer
                ),

                background = averageColor(
                    colorScheme.surfaceContainer,
                    colorScheme.surfaceContainerHigh
                )
            )
        }
    }

    if (darkTheme && useTotalBlack) {
        colorScheme = colorScheme.copy(
            surfaceContainerLowest = colorScheme.surfaceContainerLow,
            surfaceContainerLow = colorScheme.surfaceContainer,
            surfaceContainer = colorScheme.background,
            background = Color.Black,
        )
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            //  window.navigationBarColor = colorScheme.background.toArgb()

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
        Box(
            modifier = if (includeBackgroundPane) modifier.background(colorScheme.background) else modifier
        ) {
            content()
        }
    }
}

fun averageColor(color1: Color, color2: Color): Color {
    // Extract ARGB components from the two colors
    val alpha1 = color1.alpha
    val red1 = color1.red
    val green1 = color1.green
    val blue1 = color1.blue

    val alpha2 = color2.alpha
    val red2 = color2.red
    val green2 = color2.green
    val blue2 = color2.blue

    // Compute the average of each component
    val avgAlpha = (alpha1 + alpha2) / 2
    val avgRed = (red1 + red2) / 2
    val avgGreen = (green1 + green2) / 2
    val avgBlue = (blue1 + blue2) / 2

    // Combine the averaged components back into a single color
    return Color(avgRed, avgGreen, avgBlue, avgAlpha)
}
