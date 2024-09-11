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
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.LayoutDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.LayoutPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.layoutScreens.navCardScreen.DirectionsFeatureList
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.layoutScreens.navCardScreen.DirectionsPreviewFeatureScreen
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.navigation.NavigationPage
import kotlinx.serialization.Serializable

@Serializable
private sealed interface Destination {
    @Serializable
    data object Landing : Destination

    @Serializable
    data object NavigationItems : Destination

    @Serializable
    data object LayoutItems : Destination

    @Serializable
    data object DirectionsFeatureList : Destination

    @Serializable
    data object DirectionsPreviewFeature : Destination
}


@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.DirectionsPreviewFeature
    ) {
        val navigateToDestination: (LandingDestination) -> Unit = { destination ->
            when (destination) {
                LandingDestination.Layouts -> navController.navigate(Destination.LayoutItems)
                LandingDestination.Actions -> {}
                LandingDestination.Navigation -> navController.navigate(Destination.NavigationItems)
            }
        }
        val navigateToLayoutDestination: (LayoutDestination) -> Unit = { destination ->
            when (destination) {
                LayoutDestination.NavCard -> navController.navigate(Destination.DirectionsFeatureList)
            }
        }
        val onBack: () -> Unit = { navController.navigateUp() }

        route<Destination.Landing> {
            LandingPage(
                navigateToDestination = navigateToDestination
            )
        }

        route<Destination.NavigationItems> {
            NavigationPage(
                onBack = onBack
            )
        }

        route<Destination.LayoutItems> {
            LayoutPage(
                onBack = onBack,
                onLayoutDestinations = navigateToLayoutDestination
            )
        }

        route<Destination.DirectionsFeatureList> {
            DirectionsFeatureList(
                onNavCardPreview = { navController.navigate(Destination.DirectionsPreviewFeature) },
                onBack = onBack
            )
        }

        route<Destination.DirectionsPreviewFeature> {
            DirectionsPreviewFeatureScreen(
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
        exitTransition = AppTransition.exit,
        popEnterTransition = AppTransition.enterPop,
        popExitTransition = AppTransition.exitPop
    ) {
        CompositionLocalProvider(LocalAnimatedContentScope provides this) {
            content(it)
        }
    }
}
