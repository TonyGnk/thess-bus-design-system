package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.SharedButtonContent
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.ListItem
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Surface
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.HorizontalDivider
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import kotlinx.serialization.Serializable

@Serializable
data object LandingPageRoute

private const val MARGIN = 18

@Composable
fun LandingPage(
    navigateToDestination: (LandingDestination) -> Unit = {},
) {
    Scaffold {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(MARGIN.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item { Spacer(modifier = Modifier.padding(it)) }
            item { Header() }
            item { ListContainer(navigateToNavBar = navigateToDestination) }
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

@Composable
fun ListContainer(
    modifier: Modifier = Modifier,
    navigateToNavBar: (LandingDestination) -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = AppColor.surfaceContainerLowest,
        shadowElevation = 2.dp,
        shape = AppShape.round30
    ) {
        Column {
            LandingDestination.entries.forEachIndexed { index, destination ->
                Column {
                    LandingListItem(
                        destination = destination,
                        navigateToNavBar = { navigateToNavBar(destination) }
                    )
                    //If is not last then add a divider
                    if (index != LandingDestination.entries.size - 1) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}


@Composable
private fun LandingListItem(
    destination: LandingDestination,
    navigateToNavBar: () -> Unit
) {
    val paddingValues = DefaultScaffoldValues.NORMAL_BEZEL_PADDING
    ListItem(
        shape = AppShape.rectangle,
        padding = PaddingValues(paddingValues.dp),
        onClick = navigateToNavBar,
    ) {
        SharedButtonContent(
            text = stringResource(id = destination.labelRes),
            iconRes = destination.iconRes,
            contentColor = AppColor.onSurface,
            style = AppTypo.bodyLarge,
            padding = paddingValues
        )
    }
}

@AppPreview.Brightness
@Composable
fun LandingPagePreview() = ClpTheme {
    LandingPage()
}
