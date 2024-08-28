package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.navigationBar.NavigationBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R
import kotlinx.serialization.Serializable

@Serializable
data object NavigationPageRoute

@Composable
fun NavigationPage(
    onBack: () -> Unit = {}
) {
    Scaffold {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            BasicTopBar(
                labelRes = R.string.landing_destinations_navigation,
                backIcon = TopBarBackIcon(
                    onBack = onBack
                ),
            )
            NavigationBar(enabledItems = Triple(true, true, true))
        }
    }
}

@AppPreview.Brightness
@Composable
fun LandingPagePreview() = ClpTheme {
    NavigationPage()
}
