package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.TopDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.route
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.FeaturesList

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
        featuresDirectionsGraph(navController)
    }
}
