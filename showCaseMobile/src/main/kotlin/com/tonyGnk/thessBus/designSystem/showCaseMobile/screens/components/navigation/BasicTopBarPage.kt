package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R


@Composable
fun BasicTopBarPage(
    onBack: () -> Unit = {}
) {
    Scaffold {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            BasicTopBar(
                label = stringResource(R.string.landing_destinations_navigation),
                backIcon = TopBarBackIcon(
                    onBack = onBack
                ),
            )
        }
    }
}

@AppPreview.Brightness
@Composable
private fun Preview() = ThessBusTheme {
    NavigationBarPage()
}
