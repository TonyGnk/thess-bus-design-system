package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionPhases
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.SharedTransitionWrapper
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget.DirectionsLookTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTargetFunctions
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.DirectionsStart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class DirectionsFeaturePreviewModel : ViewModel() {
    val state = MutableStateFlow(DirectionsFeaturePreviewState())

    fun setInitialPhase(phase: DirectionPhases) {
        viewModelScope.launch {
            state.update {
                it.copy(
                    pagerState = PagerState(
                        currentPage = DirectionPhases.entries.indexOf(
                            phase
                        ), pageCount = { DirectionPhases.entries.size })
                )
            }
        }
    }


    fun setGivenType(givenType: DirectionsFeatureItemType) {
        state.value = state.value.copy(givenType = givenType)
    }
}

@Stable
data class DirectionsFeaturePreviewState(
    val textState: TextFieldState = TextFieldState(
        initialText = "", initialSelection = TextRange("".length),
    ),
    val givenType: DirectionsFeatureItemType = DirectionsFeatureItemType.JustMap,
    val pagerState: PagerState = PagerState(
        currentPage = DirectionPhases.entries.indexOf(DirectionPhases.START),
        pageCount = { DirectionPhases.entries.size }),
    val cameraPositionState: CameraPositionState = CameraPositionState(
        CameraPosition(
            LatLng(40.63231, 22.96331),
            16f,
            20f,
            0f
        )
    )
)

@Composable
fun DirectionsFeaturePreview(
    model: DirectionsFeaturePreviewModel,
    modifier: Modifier = Modifier,
) {
    val state by model.state.collectAsStateWithLifecycle()
    val phase: DirectionPhases = DirectionPhases.entries[state.pagerState.currentPage]

    val goToStart = { model.setInitialPhase(DirectionPhases.START) }
    val goToPickTarget = { model.setInitialPhase(DirectionPhases.PICK_TARGET) }
    val goToCategories = { model.setInitialPhase(DirectionPhases.PICK_CATEGORY) }
    val goToLookTargetJustMap: (List<DirectionsFeatureItemType.SingleItem>) -> Unit = { items ->
        model.setGivenType(DirectionsFeatureItemType.JustMap)
        model.setInitialPhase(DirectionPhases.LOOK_TARGET)
    }
    val goToLookTarget: (DirectionsFeatureItemType.SingleItem) -> Unit = { item ->
        model.setGivenType(item)
        model.setInitialPhase(DirectionPhases.LOOK_TARGET)
    }

    SharedTransitionWrapper(phase) {
        when (it) {
            DirectionPhases.START -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = WindowInsets.systemBars.asPaddingValues()
            ) {
                item {
                    DirectionsStart(
                        modifier = Modifier.padding(
                            horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
                        ),
                        onSearchClick = goToPickTarget
                    )
                }
            }

            DirectionPhases.PICK_TARGET -> {
                val functions = remember {
                    DirectionsPickTargetFunctions(
                        onBack = goToStart,
                        onSearchIme = { goToLookTargetJustMap(emptyList()) },
                        onResultClick = { item -> goToLookTarget(item) },
                    )
                }

                DirectionsPickTarget(
                    modifier = modifier,
                    requestFocus = true,
                    functions = functions,
                    textState = state.textState,
                    onCategoriesClick = goToCategories,
                )
            }

            DirectionPhases.LOOK_TARGET -> DirectionsLookTarget(
                givenType = state.givenType,
                setType = model::setGivenType,
                cameraPositionState = state.cameraPositionState,
                query = "Search here",
                onBack = goToPickTarget,
            )

            DirectionPhases.PICK_CATEGORY -> {}
        }
    }
}


//@AppPreview.Brightness
//@Composable
//private fun Preview() = ClpTheme {
//    DirectionsFeaturePreview()
//}
