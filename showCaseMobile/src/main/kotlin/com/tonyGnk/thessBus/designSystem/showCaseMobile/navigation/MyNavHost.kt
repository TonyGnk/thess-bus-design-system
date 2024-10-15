package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.menu.BlurThing
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
    noinline enterTransition: () -> EnterTransition = AppTransition.nativeEnter,
    noinline exitTransition: () -> ExitTransition = AppTransition.nativeExit,
    noinline popEnterTransition: () -> EnterTransition? = AppTransition.nativeEnterPop,
    noinline popExitTransition: () -> ExitTransition = AppTransition.nativeExitPop,
    noinline content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable<R>(
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
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
        enterTransition = { AppTransition.nativeEnter() },
        exitTransition = { AppTransition.nativeExit() },
        popEnterTransition = { AppTransition.nativeEnterPop() },
        popExitTransition = { AppTransition.nativeExitPop() },
        builder = builder
    )
}
