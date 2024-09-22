package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.ComponentDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.TopDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.route
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components.ComponentsList
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components.NavigationBarPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.Components

fun NavGraphBuilder.componentGraph(
    navController: NavController
) {
    val onBack: () -> Unit = { navController.navigateUp() }

    val onComponentPick: (Components) -> Unit = { destination ->
        when (destination) {
            Components.NavigationBar -> navController.navigate(ComponentDestination.NavigationBar)
        }
    }

    graph<TopDestination.ComponentsGraph>(
        startDestination = ComponentDestination.Select
    ) {
        route<ComponentDestination.Select> {
            ComponentsList(
                onComponentPick = onComponentPick,
                onBack = onBack
            )
        }

        route<ComponentDestination.NavigationBar> {
            NavigationBarPage(
                onBack = onBack
            )
        }
    }
}
