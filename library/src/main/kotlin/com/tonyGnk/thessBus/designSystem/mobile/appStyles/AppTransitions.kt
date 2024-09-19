package com.tonyGnk.thessBus.designSystem.mobile.appStyles

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

data object AppTransition {
    val enter: () -> EnterTransition = {
        fadeIn(animationSpec = tween(250, easing = FastOutSlowInEasing, delayMillis = 50)) +
                slideInHorizontally(
                    initialOffsetX = { it / 6 },
                    animationSpec = tween(250, easing = FastOutSlowInEasing, delayMillis = 0)
                )
    }

    val enterPop: () -> EnterTransition? = {
        fadeIn(animationSpec = tween(250, easing = FastOutSlowInEasing, delayMillis = 50)) +
                slideInHorizontally(
                    initialOffsetX = { -it / 6 },
                    animationSpec = tween(250, easing = FastOutSlowInEasing, delayMillis = 0)
                )
    }

    val exit: () -> ExitTransition = {
        fadeOut(animationSpec = tween(250, easing = FastOutSlowInEasing)) +
                slideOutHorizontally(
                    targetOffsetX = { -it / 6 },
                    animationSpec = tween(250, easing = FastOutSlowInEasing)
                )
    }

    val exitPop: () -> ExitTransition = {
        fadeOut(animationSpec = tween(250, easing = FastOutSlowInEasing, delayMillis = 0)) +
                slideOutHorizontally(
                    targetOffsetX = { it / 6 },
                    animationSpec = tween(250, easing = FastOutSlowInEasing, delayMillis = 0)
                )
    }
}


val iosEasing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f)
