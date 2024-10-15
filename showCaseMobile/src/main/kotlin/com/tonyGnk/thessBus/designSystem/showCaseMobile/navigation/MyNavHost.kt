package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTransition
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.menu.BlurThing
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.menu.applyContextMenu
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.LocationsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalAnimatedContentScope
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalSharedTransitionScope
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs.componentGraph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs.featuresGraph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.ColorsGridPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.IconsGridPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LandingDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LandingPage


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MyNavHost(navController: NavHostController) {
    SharedTransitionLayout {
        CompositionLocalProvider(value = LocalSharedTransitionScope provides this) {
            NavHost(
                navController = navController,
                startDestination = TopDestination.FeaturesGraph
            ) {
                val navigateToTopDestination: (LandingDestination) -> Unit = { destination ->
                    when (destination) {
                        LandingDestination.Features -> navController.navigate(TopDestination.FeaturesGraph)
                        LandingDestination.Components -> navController.navigate(TopDestination.ComponentsGraph)
                        LandingDestination.Icons -> navController.navigate(TopDestination.Icons)
                        LandingDestination.Colors -> navController.navigate(TopDestination.Colors)
                    }
                }

                route<TopDestination.Blur> {
                    BlurThing()
                }

                route<TopDestination.Landing> {
                    LandingPage(
                        navigateToDestination = navigateToTopDestination
                    )
                }

                route<TopDestination.Icons> {
                    IconsGridPage(
                        onBack = { navController.navigateUp() }
                    )
                }

                route<TopDestination.Colors> {
                    ColorsGridPage(
                        onBack = { navController.navigateUp() }
                    )
                }

                featuresGraph(navController)
                componentGraph(navController)
            }
        }
    }
}


inline fun <reified R : Any> NavGraphBuilder.route(
    noinline content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit),
) {
    composable<R>(
        enterTransition = { AppTransition.enter() },
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
