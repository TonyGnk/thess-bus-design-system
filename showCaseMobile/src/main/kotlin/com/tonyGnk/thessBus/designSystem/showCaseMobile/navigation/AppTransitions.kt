package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry

/*
{         (fadeIn(animationSpec = tween(220, delayMillis = 90)) +
           scaleIn(initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90)))
                      .togetherWith(fadeOut(animationSpec = tween(90)))     },
 */
data object AppTransition {
    val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
//            fadeIn(
//                animationSpec = tween(350)
//            ) + slideInVertically(
//                initialOffsetY = { it / 30 },
//                animationSpec = tween(350)
//            )
            fadeIn(
                animationSpec = tween(350)
            ) + slideInVertically(
                initialOffsetY = { it / 34 },
                animationSpec = tween(350)
            )
//            fadeIn(animationSpec = tween(220, delayMillis = 90)) +
//                    scaleIn(initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90))
        }

    val enterPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            fadeIn(
                animationSpec = tween(350)
            ) + slideInVertically(
                initialOffsetY = { it / 34 },
                animationSpec = tween(350)
            )

//            fadeIn(animationSpec = tween(220, delayMillis = 90)) +
//                    scaleIn(initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90))
        }

    val exitPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
        {
            fadeOut(animationSpec = tween(80))
//            fadeOut(animationSpec = tween(350)) + slideOutVertically(
//                targetOffsetY = { it / 30 },
//                animationSpec = tween(350)
//            )
        }
    val exit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
        {
            fadeOut(animationSpec = tween(80))
            //fadeOut(animationSpec = tween(350))
        }
}
