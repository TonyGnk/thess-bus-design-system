package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.layoutScreens.navCardScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.floatingActionButtons.FloatingActionButtonAnimated
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget.DirectionsLookTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTargetFunctions
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.DirectionsStart
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R


@Composable
fun DirectionsFeatureList(
    onBack: () -> Unit = {},
    onNavCardPreview: () -> Unit = {}
) {
    val lazyListState = rememberLazyListState()
    val isScrolledDown = remember {
        derivedStateOf {
            lazyListState.firstVisibleItemScrollOffset > 0
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButtonAnimated(
                iconRes = R.drawable.circle_nodes,
                text = "Preview",
                onClick = onNavCardPreview,
                forceExtended = !isScrolledDown.value
            )
        }
    ) {
        LazyColumn(
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.NORMAL_MARGIN.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                BasicTopBar(
                    modifier = Modifier.padding(it),
                    labelRes = R.string.landing_destinations_layout,
                    backIcon = TopBarBackIcon(
                        onBack = onBack
                    )
                )
            }
            navCardItem(this) {
                DirectionsStart()
            }

            navCardItem(this) {
//                DirectionsPickTarget(
//                    query = "",
//                    modifier = Modifier.height(256.dp),
//                    requestFocus = false,
//                    functions = DirectionsPickTargetFunctions.Empty,
//                )
            }

            navCardItem(this) {
//                DirectionsPickTarget(
//                    query = "Αριστοτέλους",
//                    requestFocus = false,
//                    functions = DirectionsPickTargetFunctions.Empty,
//                )
            }

            navCardItem(this) {
                DirectionsLookTarget(
                    query = "Nova Store",
                    onBack = {}
                )
            }

            item { Box(Modifier.height(DefaultScaffoldValues.NORMAL_MARGIN.dp)) }
        }
    }
}


private fun navCardItem(
    lazyListScope: LazyListScope,
    content: @Composable () -> Unit = {}
) {
    with(lazyListScope) {
        item {
            Box(
                modifier = Modifier
                    .padding(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp)
                    .heightIn(max = 500.dp)
                    .background(AppColor.secondaryContainer, AppShape.round20)
                    .padding(14.dp),
            ) {
                content()
            }
        }
    }
}


@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    DirectionsFeatureList()
}