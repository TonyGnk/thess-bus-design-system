package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.preview

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextRange
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.LocationsPhases
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DirectionsPreviewModel : ViewModel() {
    val state = MutableStateFlow(DirectionsFeaturePreviewState())

    fun setInitialPhase(phase: LocationsPhases) {
        viewModelScope.launch {
            state.update {
                it.copy(
                    pagerState = PagerState(
                        currentPage = LocationsPhases.entries.indexOf(
                            phase
                        ), pageCount = { LocationsPhases.entries.size })
                )
            }
        }
    }

    fun clearText() {
        state.value.textState.clearText()
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
        currentPage = LocationsPhases.entries.indexOf(LocationsPhases.CARD),
        pageCount = { LocationsPhases.entries.size }),
    val cameraPositionState: CameraPositionState = CameraPositionState(
        CameraPosition(
            LatLng(40.63231, 22.96331),
            16f,
            20f,
            0f
        )
    )
)
