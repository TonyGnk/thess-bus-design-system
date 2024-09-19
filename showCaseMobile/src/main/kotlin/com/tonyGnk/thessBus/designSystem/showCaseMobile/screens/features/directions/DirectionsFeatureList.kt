package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionPhases
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget.DirectionsLookTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTargetFunctions
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.DirectionsStart
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalSharedTransitionScope
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedStatusBarsPadding
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared.Pixel4Phone
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DirectionsFeatureList(
    onBack: () -> Unit = {},
    onNavCardPreview: () -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(pageCount = {
        DirectionPhases.entries.size
    })
    val navigateTo: (DirectionPhases) -> Unit = {
        coroutineScope.launch {
            pagerState.animateScrollToPage(DirectionPhases.entries.indexOf(it))
        }
    }
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(6)
    )
    CompositionLocalProvider(value = LocalSharedTransitionScope provides null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .extendedStatusBarsPadding()
        ) {
            BasicTopBar(
                modifier = Modifier.fillMaxWidth(),
                labelRes = R.string.Directions_label_pager,
                backIcon = TopBarBackIcon(
                    iconRes = AppIcon.back,
                    onBack = onBack
                )
            )

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 80.dp),
                pageSpacing = 2.dp,
                flingBehavior = fling,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
            ) { page ->
                val type = DirectionPhases.entries[page]
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
                            when (type) {
                                DirectionPhases.START -> DirectionsStart(
                                    padding = PaddingValues(
                                        DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp,
                                    ),
                                    onSearchClick = { navigateTo(DirectionPhases.PICK_TARGET) }
                                )

                                DirectionPhases.PICK_TARGET -> DirectionsPickTarget(
                                    horizontalPadding = PaddingValues(
                                        horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
                                    ),
                                    verticalPadding = PaddingValues(
                                        top = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
                                    ),
                                    functions = DirectionsPickTargetFunctions(
                                        onResultClick = { navigateTo(DirectionPhases.LOOK_TARGET) },
                                        onSearchIme = { navigateTo(DirectionPhases.LOOK_TARGET) },
                                        onBack = { navigateTo(DirectionPhases.START) }
                                    ),
                                    textState = rememberTextFieldState()
                                )

                                DirectionPhases.LOOK_TARGET -> DirectionsLookTarget(
                                    onBack = { navigateTo(DirectionPhases.PICK_TARGET) },
                                    query = "",
                                )
                            }
                        }

                    }
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}


@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    DirectionsFeatureList()
}
