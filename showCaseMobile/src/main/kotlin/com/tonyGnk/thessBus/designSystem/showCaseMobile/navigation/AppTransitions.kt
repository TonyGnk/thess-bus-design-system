package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry

data object AppTransition {
    val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            fadeIn(
                animationSpec = tween(350)
            ) + slideInVertically(
                initialOffsetY = { it / 30 },
                animationSpec = tween(350)
            )
        }

    val exitPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
        {
            fadeOut(animationSpec = tween(350)) + slideOutVertically(
                targetOffsetY = { it / 30 },
                animationSpec = tween(350)
            )
        }
    val enterNone: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            EnterTransition.None
        }
    val exitNone: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
        {
            ExitTransition.None
        }
    val exitOnlyFade: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
        {
            fadeOut(animationSpec = tween(350))
        }
    val enterOnlyFade: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            fadeIn(animationSpec = tween(350))
        }
}
