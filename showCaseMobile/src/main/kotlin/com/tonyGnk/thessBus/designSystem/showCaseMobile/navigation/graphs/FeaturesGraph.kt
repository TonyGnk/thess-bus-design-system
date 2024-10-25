package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTransition
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureLocationsDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.TopDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.route
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.FeaturesList
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.DirectionsFeaturePager
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsLookTargetPre
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsPickStartPre
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsPickTargetPre
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsStartPre

fun NavGraphBuilder.featuresGraph(
    navController: NavController
) {
    val onBack: () -> Unit = { navController.navigateUp() }
    val navigateTo: (FeatureDestination) -> Unit = { destination ->
        navController.navigate(destination)
    }

    graph<TopDestination.FeaturesGraph>(
        startDestination = FeatureDestination.LocationsGraph
    ) {
        route<FeatureDestination.List> {
            FeaturesList(
                onBack = onBack,
                goTo = navigateTo
            )
        }

        featuresLocationsGraph(navController)
    }
}

fun NavGraphBuilder.featuresLocationsGraph(
    navController: NavController
) {
    val onBack: () -> Unit = { navController.navigateUp() }
    val goTo: (FeatureLocationsDestination) -> Unit = { phase ->
        navController.navigate(phase)
    }

    graph<FeatureDestination.LocationsGraph>(
        startDestination = FeatureLocationsDestination.PickTarget
    ) {
        route<FeatureLocationsDestination.Info> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(TopDestination.FeaturesGraph)
            }
            DirectionsFeaturePager(
                model = viewModel(parentEntry),
                onFullScreen = { goTo(FeatureLocationsDestination.Card) },
                onBack = onBack
            )
        }

        route<FeatureLocationsDestination.Card> {
            LocationsStartPre(
                goToPickTarget = { goTo(FeatureLocationsDestination.PickTarget) },
            )
        }

        route<FeatureLocationsDestination.PickTarget> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(TopDestination.FeaturesGraph)
            }
            LocationsPickTargetPre(
                model = viewModel(parentEntry),
                onBack = onBack,
                goToLookTarget = { goTo(FeatureLocationsDestination.LookTarget) },
                goToPickStart = { goTo(FeatureLocationsDestination.PickStart) },
                goToCategories = {
                    //goTo(FeatureLocationsDestination.PickCategory)
                },
            )
        }

        route<FeatureLocationsDestination.Collection> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(TopDestination.FeaturesGraph)
            }
            LocationsPickTargetPre(
                model = viewModel(parentEntry),
                onBack = onBack,
                goToLookTarget = { goTo(FeatureLocationsDestination.LookTarget) },
                goToPickStart = { goTo(FeatureLocationsDestination.PickStart) },
                goToCategories = {
                    //goTo(FeatureLocationsDestination.PickCategory)
                },
            )
        }


        route<FeatureLocationsDestination.LookTarget>(
            enterTransition = AppTransition.fadeEnter,
            popEnterTransition = AppTransition.fadeEnter,
            exitTransition = AppTransition.fadeExit,
            popExitTransition = AppTransition.fadeExit,
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(TopDestination.FeaturesGraph)
            }
            LocationsLookTargetPre(
                model = viewModel(parentEntry),
                onBack = onBack,
                goToPickStart = { goTo(FeatureLocationsDestination.PickStart) },
            )
        }

        route<FeatureLocationsDestination.PickStart> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(TopDestination.FeaturesGraph)
            }
            LocationsPickStartPre(
                model = viewModel(parentEntry),
                onBack = onBack,
            )
        }
    }
}
