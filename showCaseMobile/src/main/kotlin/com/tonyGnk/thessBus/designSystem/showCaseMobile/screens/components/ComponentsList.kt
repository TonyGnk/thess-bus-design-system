package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.HorizontalDivider
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.Components
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared.LandingUnknown
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared.SharedListContainer

@Composable
fun ComponentsList(
    onBack: () -> Unit = {},
    onComponentPick: (Components) -> Unit = {}
) {
    Scaffold {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
            contentPadding = it,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                BasicTopBar(
                    labelRes = R.string.landing_destinations_components,
                    backIcon = TopBarBackIcon(
                        onBack = onBack
                    )
                )
            }

            item {
                SharedListContainer {
                    Components.entries.forEachIndexed { index, destination ->
                        LandingUnknown(
                            text = stringResource(id = destination.labelRes),
                            iconRes = 0,
                            onClick = { onComponentPick(destination) }
                        )
                        if (index != Components.entries.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}
