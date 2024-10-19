package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared.LandingUnknown
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared.SharedListContainer


private const val MARGIN = 20

@Composable
fun LandingPage(
    navigateToDestination: (LandingDestination) -> Unit = {},
) {
    Scaffold {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(MARGIN.dp),
            contentPadding = it,
            modifier = Modifier.fillMaxSize()
        ) {
            item { Spacer(modifier = Modifier) }
            item { Header() }
            item {
                SharedListContainer {
                    LandingDestination.entries.forEachIndexed { index, landingDestination ->
                        LandingUnknown(
                            text = stringResource(id = landingDestination.labelRes),
                            iconRes = landingDestination.iconRes,
                            onClick = { navigateToDestination(landingDestination) }
                        )
                        if (index != LandingDestination.entries.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MARGIN.dp),
        modifier = Modifier.padding(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp)
    ) {
        LandingPageAppBar()
        LandingPageAppBarDescription()
        UpdateButton()
    }
}


@AppPreview.Scale
@Composable
fun LandingPagePreview() = ThessBusTheme {
    LandingPage()
}
