package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.HorizontalDivider
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LayoutDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared.LandingUnknown
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared.SharedListContainer


@Composable
fun FeaturesList(
    onBack: () -> Unit = {},
    onLayoutDestinations: (LayoutDestination) -> Unit = {}
) {
    Scaffold {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                BasicTopBar(
                    modifier = Modifier.padding(it),
                    labelRes = R.string.landing_destinations_features,
                    backIcon = TopBarBackIcon(
                        onBack = onBack
                    )
                )
            }

            item {
                SharedListContainer {
                    LayoutDestination.entries.forEachIndexed { index, layoutDestination ->
                        LandingUnknown(
                            text = stringResource(id = layoutDestination.labelRes),
                            iconRes = 0,
                            onClick = { onLayoutDestinations(layoutDestination) }
                        )
                        if (index != LayoutDestination.entries.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}


@AppPreview.Brightness
@Composable
fun LandingPagePreview() = ClpTheme {
    FeaturesList()
}