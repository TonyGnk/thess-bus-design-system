package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalAnimatedContentScope
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LandingDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LandingPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LandingPageRoute
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.navigation.NavigationPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.navigation.NavigationPageRoute

@Composable
fun MyNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = LandingPageRoute) {

        val navigateToDestination: (LandingDestination) -> Unit = { destination ->
            when (destination) {
                LandingDestination.Layouts -> {}// navController.navigate("layouts")
                LandingDestination.Actions -> {}// navController.navigate("actions")
                LandingDestination.Navigation -> navController.navigate(NavigationPageRoute)
            }
        }

        route<LandingPageRoute> {
            LandingPage(
                navigateToDestination = navigateToDestination
            )
        }

        route<NavigationPageRoute> {
            NavigationPage()
        }
    }
}


inline fun <reified R : Any> NavGraphBuilder.route(
    noinline content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit),
) {
    composable<R>(
        enterTransition = AppTransition.enter,
        exitTransition = AppTransition.exitOnlyFade,
        popEnterTransition = AppTransition.enter,
        popExitTransition = AppTransition.exitPop
    ) {
        CompositionLocalProvider(LocalAnimatedContentScope provides this) {
            content(it)
        }
    }
}
