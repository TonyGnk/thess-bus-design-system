package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.map.MapStyleManager
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDirectionsDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureLocationsDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.node
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsFeatureModel
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.DirectionsMapPre
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsOverviewPre
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsPickTargetPre
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.LocationsStartPre
import org.maplibre.android.maps.Style

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
        startDestination = FeatureLocationsDestination.NAV
    ) {
        node<FeatureLocationsDestination.Card> {
            LocationsStartPre(
                goToPickTarget = { goTo(FeatureLocationsDestination.PickTarget) },
            )
        }


        node<FeatureLocationsDestination.NAV> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FeatureDestination.LocationsGraph)
            }
            MapNavHost(
                model = viewModel(parentEntry),
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
    }
}


@Composable
fun MapNavHost(
    goBack: () -> Unit = {},
    model: LocationsFeatureModel
) {
    val navController = rememberNavController()
    val context = navController.context

    val style = when (val result = MapStyleManager(context).setupStyle()) {
        is MapStyleManager.StyleSetupResult.Error -> {
            throw result.exception
        }

        is MapStyleManager.StyleSetupResult.Success -> result.styleFile
    }
    val styleBuilder = Style.Builder().fromUri(
        Uri.fromFile(style).toString()
    )

    DirectionsMapPre(
        model = model,
        onBack = {},
        styleBuilder = styleBuilder,
        goToPickStart = { },
    )

    NavHost(
        navController = navController,
        startDestination = FeatureLocationsDestination.Overview
    ) {
        val goTo: (FeatureLocationsDestination) -> Unit = { phase ->
            navController.navigate(phase)
        }

        node<FeatureLocationsDestination.Overview> {
            LocationsOverviewPre(
                model = model,
                goToPickTarget = { goTo(FeatureLocationsDestination.PickTarget) },
            )
        }

        node<FeatureLocationsDestination.PickTarget> {
            LocationsPickTargetPre(
                model = model,
                onBack = navController::navigateUp,
                goToLookTarget = { goTo(FeatureLocationsDestination.LookTarget) },
                goToPickStart = { goTo(FeatureLocationsDestination.PickStart) },
                goToCategories = {
                    //goTo(FeatureLocationsDestination.PickCategory)
                },
            )
        }
    }
}
