package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTransition
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDirectionsDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureLocationsDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.node
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.DirectionsFeaturePager
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsLookTargetPre
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsPickStartPre
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsPickTargetPre
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsStartPre

fun NavGraphBuilder.featuresLocationsGraph(
    navController: NavController
) {
    val onBack: () -> Unit = { navController.navigateUp() }
    val goTo: (FeatureLocationsDestination) -> Unit = { phase ->
        navController.navigate(phase)
    }
    val goToDirections: (
        String, Double, Double, String, Double, Double
    ) -> Unit = { startN, startLat, startLon, endN, endLat, endLon ->
        navController.navigate(
            FeatureDirectionsDestination.Home(
                startLocationName = startN,
                startLat = startLat,
                startLon = startLon,
                endLocationName = endN,
                endLat = endLat,
                endLon = endLon
            )
        )
    }

    graph<FeatureDestination.LocationsGraph>(
        startDestination = FeatureLocationsDestination.PickTarget
    ) {
        node<FeatureLocationsDestination.Info> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FeatureDestination.LocationsGraph)
            }
            DirectionsFeaturePager(
                model = viewModel(parentEntry),
                onFullScreen = { goTo(FeatureLocationsDestination.Card) },
                onBack = onBack
            )
        }

        node<FeatureLocationsDestination.Card> {
            LocationsStartPre(
                goToPickTarget = { goTo(FeatureLocationsDestination.PickTarget) },
            )
        }

        node<FeatureLocationsDestination.PickTarget> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FeatureDestination.LocationsGraph)
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

        node<FeatureLocationsDestination.Collection> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FeatureDestination.LocationsGraph)
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


        node<FeatureLocationsDestination.LookTarget>(
            enterTransition = AppTransition.fadeEnter,
            popEnterTransition = AppTransition.fadeEnter,
            exitTransition = AppTransition.fadeExit,
            popExitTransition = AppTransition.fadeExit,
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FeatureDestination.LocationsGraph)
            }
            LocationsLookTargetPre(
                model = viewModel(parentEntry),
                onBack = onBack,
                goToPickStart = { goTo(FeatureLocationsDestination.PickStart) },
            )
        }

        node<FeatureLocationsDestination.PickStart> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FeatureDestination.LocationsGraph)
            }
            LocationsPickStartPre(
                model = viewModel(parentEntry),
                goToDirections = goToDirections,
                onBack = onBack,
            )
        }
    }
}
