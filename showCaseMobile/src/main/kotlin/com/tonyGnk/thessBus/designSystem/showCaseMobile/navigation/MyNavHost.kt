package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.bar.NavigationBar
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LandingPage

@Composable
fun MyNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") {
            LandingPage(
                navigateToNavBar = { navController.navigate("navBar") }
            )
        }
        composable("navBar") {

            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                NavigationBar(
                    Triple(true, true, true)
                )
            }
        }
    }
}
