package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.ComponentsDestination
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
            Components.NavigationBar -> navController.navigate(ComponentsDestination.NavigationBar)
        }
    }

    graph<TopDestination.ComponentsGraph>(
        startDestination = ComponentsDestination.Select
    ) {
        route<ComponentsDestination.Select> {
            ComponentsList(
                onComponentPick = onComponentPick,
                onBack = onBack
            )
        }

        route<ComponentsDestination.NavigationBar> {
            NavigationBarPage(
                onBack = onBack
            )
        }
    }
}
