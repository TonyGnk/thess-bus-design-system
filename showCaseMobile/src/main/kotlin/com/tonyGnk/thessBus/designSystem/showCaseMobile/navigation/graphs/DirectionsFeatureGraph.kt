package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.DirectionDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.TopDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.route
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.DirectionsFeatureList
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.DirectionsPreviewFeatureScreen

fun NavGraphBuilder.directionsFeatureGraph(
    navController: NavController
) {
    val onBack: () -> Unit = { navController.navigateUp() }

    graph<DirectionDestination.DirectionsFeature>(
        startDestination = TopDestination.DirectionsFeatureList
    ) {
        route<TopDestination.DirectionsFeatureList> {
            DirectionsFeatureList(
                onNavCardPreview = { navController.navigate(DirectionDestination.DirectionsPreviewFeature) },
                onBack = onBack
            )
        }

        route<DirectionDestination.DirectionsPreviewFeature> {
            DirectionsPreviewFeatureScreen(
                onBack = onBack
            )
        }
    }
}
