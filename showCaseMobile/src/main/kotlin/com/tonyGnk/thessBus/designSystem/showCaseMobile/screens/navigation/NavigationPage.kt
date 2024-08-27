package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.bar.NavigationBar
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import kotlinx.serialization.Serializable

@Serializable
data object NavigationPageRoute

@Composable
fun NavigationPage() {
    Scaffold {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavigationBar(enabledItems = Triple(true, true, true))
        }
    }
}

@AppPreview.Brightness
@Composable
fun LandingPagePreview() = ClpTheme {
    NavigationPage()
}
