package com.tonyGnk.thessBus.designSystem.showCaseMobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.MyNavHost
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.TopDestination

internal class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { false }

//        var keepSplashScreenOn = true
//        splashScreen.setKeepOnScreenCondition { keepSplashScreenOn }
//        lifecycleScope.launch {
//            delay(1)
//            keepSplashScreenOn = false
//        }

        enableEdgeToEdge()
        setContent {
            val darkTheme = isSystemInDarkTheme()
            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.TRANSPARENT,
                        android.graphics.Color.TRANSPARENT,
                    ) { darkTheme },
//                    navigationBarStyle = SystemBarStyle.auto(
//                        lightScrim,
//                        darkScrim,
//                    ) { darkTheme },
                )
                onDispose {}
            }



            ThessBusTheme {
                val navController = rememberNavController()
//                Box(
//                    Modifier
//                        .fillMaxSize()
//                        .background(Color.Cyan)
//                )
                MyNavHost(navController)
            }
//            Column(Modifier.fillMaxSize()) {
//                row(modifier = Modifier.weight(1f), dark = false, amoled = false)
//                row(modifier = Modifier.weight(1f), dark = true, amoled = false)
//                row(modifier = Modifier.weight(1f), dark = true, amoled = true)
//            }
        }
    }
}

private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

@Composable
private fun row(
    modifier: Modifier = Modifier,
    dark: Boolean,
    amoled: Boolean
) {
    ThessBusTheme(modifier = modifier, darkTheme = dark, useTotalBlack = amoled) {
        Row(modifier = Modifier) {
//            Box(
//                contentAlignment = androidx.compose.ui.Alignment.Center,
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxHeight()
//                    .background(color = AppColor.primary)
//            ) {
//                val colorString: String = with(AppColor.primary) {
//                    "${(red * 255).toInt()} ${(green * 255).toInt()} ${(blue * 255).toInt()}"
//                }
//                Text(
//                    "pri - $colorString",
//                    style = AppTypo.bodySmall.copy(color = AppColor.onPrimary)
//                )
//            }
//            Box(
//                contentAlignment = androidx.compose.ui.Alignment.Center,
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxHeight()
//                    .background(color = AppColor.surface)
//            ) {
//                val colorString: String = "#" + AppColor.surface.toArgb()
//                    .toUInt()
//                    .toString(16)
//                    .padStart(8, '0')
//                    .substring(2)
//                Text(
//                    "surf - $colorString",
//                    style = AppTypo.bodySmall.copy(color = AppColor.onSurface)
//                )
//            }
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color = AppColor.surfaceLowest)
            ) {
                val colorString: String = with(AppColor.surfaceLowest) {
                    "${(red * 255).toInt()} ${(green * 255).toInt()} ${(blue * 255).toInt()}"
                }
                Text(
                    "lest - $colorString",
                    style = AppTypo.bodySmall.copy(color = AppColor.onSurface)
                )
            }
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color = AppColor.surfaceLow)
            ) {
                val colorString: String = with(AppColor.surfaceLow) {
                    "${(red * 255).toInt()} ${(green * 255).toInt()} ${(blue * 255).toInt()}"
                }
                Text(
                    "low - $colorString",
                    style = AppTypo.bodySmall.copy(color = AppColor.onSurface)
                )
            }
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color = AppColor.surface)
            ) {
                val colorString: String = with(AppColor.surface) {
                    "${(red * 255).toInt()} ${(green * 255).toInt()} ${(blue * 255).toInt()}"
                }
                Text(
                    "cont - $colorString",
                    style = AppTypo.bodySmall.copy(color = AppColor.onSurface)
                )
            }
//            Box(
//                contentAlignment = androidx.compose.ui.Alignment.Center,
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxHeight()
//                    .background(color = AppColor.surfaceContainerHigh)
//            ) {
//                val colorString: String = with(AppColor.surfaceContainerHigh) {
//                    "${(red * 255).toInt()} ${(green * 255).toInt()} ${(blue * 255).toInt()}"
//                }
//                Text(
//                    "high - $colorString",
//                    style = AppTypo.bodySmall.copy(color = AppColor.onSurface)
//                )
//            }
//            Box(
//                contentAlignment = androidx.compose.ui.Alignment.Center,
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxHeight()
//                    .background(color = AppColor.surfaceContainerHighest)
//            ) {
//                val colorString: String = with(AppColor.surfaceContainerHighest) {
//                    "${(red * 255).toInt()} ${(green * 255).toInt()} ${(blue * 255).toInt()}"
//                }
//                Text(
//                    "hest - $colorString",
//                    style = AppTypo.bodySmall.copy(color = AppColor.onSurface)
//                )
//            }
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color = AppColor.background)
            ) {
                val colorString: String = with(AppColor.background) {
                    "${(red * 255).toInt()} ${(green * 255).toInt()} ${(blue * 255).toInt()}"
                }
                Text(
                    "back - $colorString",
                    style = AppTypo.bodySmall.copy(color = AppColor.onSurface)
                )
            }
        }
    }
}
