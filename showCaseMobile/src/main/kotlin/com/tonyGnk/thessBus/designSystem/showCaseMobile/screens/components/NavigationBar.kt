package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.navigationBar.NavigationBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R

@Composable
fun NavigationBarPage(
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
                label = stringResource(R.string.landing_destinations_navigation),
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
fun LandingPagePreview() = ThessBusTheme {
    NavigationBarPage()
}
