package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.DirectionDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.TopDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.route
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.DirectionsFeaturePager
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.DirectionsFeaturePreview

fun NavGraphBuilder.directionsFeatureGraph(
    navController: NavController
) {
    val onBack: () -> Unit = { navController.navigateUp() }

    graph<TopDestination.DirectionsFeatureGraph>(
        startDestination = DirectionDestination.Preview
    ) {
        route<DirectionDestination.Pager> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(TopDestination.DirectionsFeatureGraph)
            }
            DirectionsFeaturePager(
                model = viewModel(parentEntry),
                onNavCardPreview = {
                    navController.navigate(
                        DirectionDestination.Preview
                    )
                },
                onBack = onBack
            )
        }

        route<DirectionDestination.Preview> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(TopDestination.DirectionsFeatureGraph)
            }

            DirectionsFeaturePreview(
                model = viewModel(parentEntry),
            )
        }
    }
}
