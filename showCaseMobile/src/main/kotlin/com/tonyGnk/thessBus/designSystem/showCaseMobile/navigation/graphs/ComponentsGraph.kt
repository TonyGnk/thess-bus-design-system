package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.ComponentDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.TopDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.node
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components.ComponentsList
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components.navigation.NavigationBarPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components.actions.ComponentsActionsButtonPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components.navigation.BasicTopBarPage
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.Components

fun NavGraphBuilder.componentGraph(
    navController: NavController
) {
    val onBack: () -> Unit = { navController.navigateUp() }

    val onComponentPick: (Components) -> Unit = { destination ->
        when (destination) {
            Components.NavigationBar -> navController.navigate(ComponentDestination.NavigationBar)
            Components.Buttons -> navController.navigate(ComponentDestination.Buttons)
            Components.FloatingActionButton -> navController.navigate(ComponentDestination.FloatingActionButton)
            Components.BasicTopBar -> navController.navigate(ComponentDestination.BasicTopBar)
        }
    }

    graph<TopDestination.ComponentsGraph>(
        startDestination = ComponentDestination.Select
    ) {
        node<ComponentDestination.Select> {
            ComponentsList(
                onComponentPick = onComponentPick,
                onBack = onBack
            )
        }

        node<ComponentDestination.NavigationBar> {
            NavigationBarPage(onBack)
        }

        node<ComponentDestination.BasicTopBar> {
            BasicTopBarPage(onBack)
        }

        node<ComponentDestination.Buttons> {
            ComponentsActionsButtonPage(onBack)
        }

        node<ComponentDestination.FloatingActionButton> {
//            NavigationBarPage(
//                onBack = onBack
//            )
        }
    }
}
