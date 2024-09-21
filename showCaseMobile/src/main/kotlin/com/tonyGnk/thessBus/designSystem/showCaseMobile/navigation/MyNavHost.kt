package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTransition
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalAnimatedContentScope
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalSharedTransitionScope
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs.componentGraph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs.directionsFeatureGraph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LandingDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LandingPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LayoutDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.FeaturesList


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MyNavHost(navController: NavHostController) {
    SharedTransitionLayout {
        CompositionLocalProvider(value = LocalSharedTransitionScope provides this) {
            NavHost(
                navController = navController,
                startDestination = TopDestination.DirectionsFeatureGraph
            ) {
                val navigateToTopDestination: (LandingDestination) -> Unit = { destination ->
                    when (destination) {
                        LandingDestination.Features -> navController.navigate(TopDestination.FeatureList)
                        LandingDestination.Components -> navController.navigate(TopDestination.ComponentsGraph)
                    }
                }
                val navigateToLayoutTopDestination: (LayoutDestination) -> Unit = { destination ->
                    when (destination) {
                        LayoutDestination.NavCard -> navController.navigate(TopDestination.DirectionsFeatureGraph)
                    }
                }
                val onBack: () -> Unit = { navController.navigateUp() }

                route<TopDestination.Landing> {
                    LandingPage(
                        navigateToDestination = navigateToTopDestination
                    )
                }

                route<TopDestination.FeatureList> {
                    FeaturesList(
                        onBack = onBack,
                        onLayoutDestinations = navigateToLayoutTopDestination
                    )
                }

                directionsFeatureGraph(navController)
                componentGraph(navController)
            }
        }
    }
}


inline fun <reified R : Any> NavGraphBuilder.route(
    noinline content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit),
) {
    composable<R>(
        enterTransition = {
            AppTransition.enter()
        },
        exitTransition = { AppTransition.exit() },
        popEnterTransition = { AppTransition.enterPop() },
        popExitTransition = { AppTransition.exitPop() }
    ) {
        CompositionLocalProvider(LocalAnimatedContentScope provides this) {
            content(it)
        }
    }
}

inline fun <reified T : Any> NavGraphBuilder.graph(
    startDestination: Any,
    noinline builder: NavGraphBuilder.() -> Unit
) {
    navigation<T>(
        startDestination = startDestination,
        enterTransition = { AppTransition.enter() },
        exitTransition = { AppTransition.exit() },
        popEnterTransition = { AppTransition.enterPop() },
        popExitTransition = { AppTransition.exitPop() },
        builder = builder
    )
}
