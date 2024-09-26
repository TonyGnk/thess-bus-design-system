package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.LocationsPhases
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedWindowInsets
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.preview.DirectionsPreviewModel
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared.Pixel4Phone
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@Composable
fun DirectionsFeaturePager(
    onBack: () -> Unit = {},
    model: DirectionsPreviewModel,
    onFullScreen: () -> Unit = { },
) {
    val coroutineScope = rememberCoroutineScope()

    val state by model.state.collectAsStateWithLifecycle()

    val navigateTo: (LocationsPhases) -> Unit = {
        coroutineScope.launch {
            state.pagerState.animateScrollToPage(LocationsPhases.entries.indexOf(it))
        }
    }

//    CompositionLocalProvider(value = LocalSharedTransitionScope provides null) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .extendedWindowInsets()
    ) {
        BasicTopBar(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.Directions_label_pager),
            backIcon = TopBarBackIcon(
                iconRes = AppIcon.back,
                onBack = onBack
            ),
            rightContent = TopBarBackIcon(
                iconRes = R.drawable.expand,
                onBack = {
                    onFullScreen()
                }
            )
        )

        FeaturePager(
            modifier = Modifier
                .weight(4f)
                .fillMaxWidth(),
            pagerState = state.pagerState,
        ) { page ->
            val type = LocationsPhases.entries[page]
            when (type) {
                LocationsPhases.CARD -> LocationsStartPre(
                    modifier = Modifier.padding(
                        vertical = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
                    ),
                    goToPickTarget = { navigateTo(LocationsPhases.PICK_TARGET) },
                )

                LocationsPhases.PICK_TARGET -> {}

                LocationsPhases.LOOK_TARGET -> {}
//                    DirectionsLookTarget(
//                        modifier = Modifier.padding(
//                            vertical = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
//                        ),
//                        applySystemBarPadding = false,
//                        givenType = state.givenType,
//                        setType = model::setGivenType,
//                        cameraPositionState = rememberCameraPositionState(
//                            init = {
//                                CameraPosition(
//                                    LatLng(40.63231, 22.96331),
//                                    16f,
//                                    20f,
//                                    0f
//                                )
//                            }
//                        ),
//                        onBack = { navigateTo(DirectionPhases.PICK_TARGET) },
//                        query = "Search here",
//                        paddingValues = PaddingValues(
//                            top = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
//                        )
//                    )

                LocationsPhases.PICK_CATEGORY -> {
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
    //  }
}


@Composable
fun FeaturePager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    content: @Composable (Int) -> Unit
) {
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(2)
    )

    HorizontalPager(
        key = { it },
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 80.dp),
        pageSpacing = 2.dp,
        flingBehavior = fling,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) { page ->

        val di = pagerState.getOffsetDistanceInPages(page).absoluteValue

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Pixel4Phone(
                modifier = Modifier
                    .weight(14f)
                    .zIndex(10f)
                    .scale(0.9f + 0.1f * (1 - di))
                    .aspectRatio(9f / 19f)
            ) {
                CompositionLocalProvider(
                    LocalDensity provides LocalDensity.providesDefault(Density(density = 1.9f)).value
                ) {
                    content(page)
                }
            }
        }
    }
}

//@AppPreview.Brightness
//@Composable
//private fun Preview() = ClpTheme {
//    DirectionsFeaturePager()
//}
