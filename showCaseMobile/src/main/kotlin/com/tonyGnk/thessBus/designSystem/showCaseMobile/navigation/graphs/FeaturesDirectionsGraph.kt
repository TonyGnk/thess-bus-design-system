package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.toRoute
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTransition
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.DirectionsOverview
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDirectionsDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.node

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
        node<FeatureDirectionsDestination.Home>(
            // enterTransition = AppTransition.scaleEnter,
            //  popExitTransition = AppTransition.fadeExit,
            //  exitTransition = AppTransition.fadeExit
        ) {
            val args = it.toRoute<FeatureDirectionsDestination.Home>()
            DirectionsOverview(
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
