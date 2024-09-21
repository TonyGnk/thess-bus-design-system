package com.tonyGnk.thessBus.designSystem.mobile.features.topDestinations.explore.landing

import android.util.Log
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.TonalButton
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeFavorites
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeHistory
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget.MyGoogleMap
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview.Favorites
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview.PickTargetOverview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview.QuickActions
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.shared.searchContainer.SearchButton
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedStatusBarsPadding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun ExploreMainPage() {
//    PickTargetOverview(
//        horizontalPadding = PaddingValues(0.dp),
//        state = rememberLazyListState(),
//        onItemClick = {},
//        onCategoriesClick = {}
//    )
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black.copy(alpha = 0.5f))
//    )
    ExploreMainPageUi()
}

@Composable
fun ExploreMainPageUi() {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val size = remember { mutableStateOf(500.dp) }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState, enabled = false)  // Disable default scrolling
    ) {
        MyGoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight),
            cameraPositionState = rememberCameraPositionState(
                init = {
                    CameraPosition(
                        LatLng(40.63231, 22.96331),
                        1f,
                        20f,
                        0f
                    )
                }
            ),
            setType = {},
        )
        Column(
            Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    // .height(100.dp)
                    .background(AppColor.surfaceContainer)
                    .padding(horizontal = 12.dp)
            ) {
                SearchButton(
                    modifier = Modifier
                        .statusBarsPadding(),
                    color = AppColor.surfaceContainerLowest,
                    onClick = {},
                    rippleColor = AppColor.primary.copy(alpha = 0.2f),
                    searchLabel = "Αναζήτηση",
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size.value)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    // .height(1200.dp)
                    .background(AppColor.surfaceContainer)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            scope.launch {
                                if (dragAmount.y < 0) {
                                    val newSize = size.value + dragAmount.y.toDp()

                                    if (newSize > 0.toDp()) {
                                        size.value = newSize
                                    } else {
                                        size.value = 0.toDp()
                                        scrollState.scrollBy(-newSize.value)
                                    }
                                } else if (dragAmount.y > 0) {
                                    val newSize = size.value + dragAmount.y.toDp()

                                    if (scrollState.value != 0) {
                                        scrollState.scrollBy(-newSize.value)
                                    } else {
                                        size.value = newSize
                                    }
                                }
                            }
                        }
                    }
            ) {
                QuickActions(
                    onCategoriesClick = {},
                    horizontalPadding = PaddingValues(12.dp)
                )
                Favorites(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    label = "Saved Places",
                    items = PickTargetFakeFavorites,
                    onItemClick = {}
                )
                Favorites(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    label = "Saved Places",
                    items = PickTargetFakeHistory,
                    onItemClick = {}
                )
            }
        }

    }
}


@AppPreview.Light
@Composable
private fun Preview() = ClpTheme {
    ExploreMainPage()
}
