package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.preview

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextRange
import androidx.lifecycle.ViewModel
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.map.CustomCameraPosition
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.LocationsPhases
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.CollectionBottomSheetType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LocationsFeatureModel : ViewModel() {
    private val _state = MutableStateFlow(DirectionsFeaturePreviewState())
    val state: StateFlow<DirectionsFeaturePreviewState> = _state.asStateFlow()

    private val _mapCamera = MutableStateFlow(CustomCameraPosition.DEFAULT)

    fun clearSearchField() {
        _state.value.textState.clearText()
    }

    fun setGivenType(givenType: DirectionsFeatureItemType?) {
        if (givenType == null) return

        _state.update {
            it.copy(
                pickedItem = givenType,
            )
        }
    }

    fun updateCameraPosition(newPosition: CustomCameraPosition) {
        _mapCamera.update { newPosition }
    }

    fun updateSelectedFavoriteItemId(newId: Int?) {
        _state.update {
            it.copy(
                selectedFavoriteItemId = newId
            )
        }
    }

    fun setTextField(title: String?) {
        if (title != null) _state.value.textState.setTextAndPlaceCursorAtEnd(title)
    }

    fun setBottomSheetType(type: CollectionBottomSheetType) {
        _state.update {
            it.copy(
                collectionsBottomSheetType = type
            )
        }
    }

}

@Stable
data class DirectionsFeaturePreviewState(
    val textState: TextFieldState = TextFieldState(
        initialText = "", initialSelection = TextRange("".length),
    ),
    val pickedItem: DirectionsFeatureItemType = DirectionsFeatureItemType.JustMap,
    val pagerState: PagerState = PagerState(
        currentPage = LocationsPhases.entries.indexOf(LocationsPhases.CARD),
        pageCount = { LocationsPhases.entries.size }
    ),
    val selectedFavoriteItemId: Int? = null,
    val collectionsBottomSheetType: CollectionBottomSheetType = CollectionBottomSheetType.Hidden
)
