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
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.LayoutDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.LayoutPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.LayoutPageRoute
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.layoutScreens.navCardScreen.NavCardPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.layoutScreens.navCardScreen.NavCardPageRoute
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.layoutScreens.navCardScreen.NavCardPreviewPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.layoutScreens.navCardScreen.NavCardPreviewPageRoute
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.navigation.NavigationPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.navigation.NavigationPageRoute

@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavCardPreviewPageRoute) {
        val navigateToDestination: (LandingDestination) -> Unit = { destination ->
            when (destination) {
                LandingDestination.Layouts -> navController.navigate(LayoutPageRoute)
                LandingDestination.Actions -> {}// navController.navigate("actions")
                LandingDestination.Navigation -> navController.navigate(NavigationPageRoute)
            }
        }
        val navigateToLayoutDestination: (LayoutDestination) -> Unit = { destination ->
            when (destination) {
                LayoutDestination.NavCard -> navController.navigate(NavCardPageRoute)
            }
        }
        val onBack: () -> Unit = { navController.navigateUp() }

        route<LandingPageRoute> {
            LandingPage(
                navigateToDestination = navigateToDestination
            )
        }

        route<NavigationPageRoute> {
            NavigationPage(
                onBack = onBack
            )
        }

        route<LayoutPageRoute> {
            LayoutPage(
                onBack = onBack,
                onLayoutDestinations = navigateToLayoutDestination
            )
        }

        route<NavCardPageRoute> {
            NavCardPage(
                onNavCardPreview = { navController.navigate(NavCardPreviewPageRoute) },
                onBack = onBack
            )
        }

        route<NavCardPreviewPageRoute> {
            NavCardPreviewPage(
                onBack = onBack
            )
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
