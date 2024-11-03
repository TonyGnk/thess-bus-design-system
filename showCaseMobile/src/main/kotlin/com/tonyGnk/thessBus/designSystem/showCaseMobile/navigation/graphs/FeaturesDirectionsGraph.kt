package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.toRoute
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDirectionsDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.route

fun NavGraphBuilder.featuresDirectionsGraph(
    navController: NavController
) {
    val onBack: () -> Unit = { navController.navigateUp() }
    val goTo: (FeatureDirectionsDestination) -> Unit = { phase ->
        navController.navigate(phase)
    }

    graph<FeatureDestination.DirectionsGraph>(
        startDestination = FeatureDirectionsDestination.Home(
            "A", 40.64, 22.94, "B", 3.0, 3.3
        )
    ) {
        route<FeatureDirectionsDestination.Home> {
            val args =
                it.toRoute<com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDirectionsDestination.Home>()
            com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.DirectionsOverview(
                startLocationName = args.startLocationName,
                startLat = args.startLat,
                startLon = args.startLon,
                endLocationName = args.endLocationName,
                endLat = args.endLat,
                endLon = args.endLon
            )

        }
    }
}
