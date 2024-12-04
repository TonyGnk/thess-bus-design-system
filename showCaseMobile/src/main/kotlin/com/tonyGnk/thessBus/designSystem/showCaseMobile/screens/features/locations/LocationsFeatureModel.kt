package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextRange
import androidx.lifecycle.ViewModel
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.data.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.data.DirectionsViewModel
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.data.toPoint
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.bottomSheet.CollectionBottomSheetType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.maplibre.android.geometry.LatLng

class LocationsFeatureModel : ViewModel(), DirectionsViewModel {
    private val _state = MutableStateFlow(LocationsFeaturePreviewState())
    val state: StateFlow<LocationsFeaturePreviewState> = _state.asStateFlow()

    fun setGivenType(givenType: DirectionsFeatureItemType?) {
        if (givenType == null) return

        _state.update {
            it.copy(
                pickedItem = givenType,
            )
        }
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

    override fun onMapLongClick(latLng: LatLng) {
        _state.update {
            it.copy(
                pickedItem = latLng.toPoint()
            )
        }
    }

    override fun clearPickedItem() {
        _state.update {
            it.copy(
                pickedItem = DirectionsFeatureItemType.JustMap
            )
        }
        _state.value.textState.clearText()
    }

}

@Stable
data class LocationsFeaturePreviewState(
    val textState: TextFieldState = TextFieldState(
        initialText = "", initialSelection = TextRange("".length),
    ),
    val pickedItem: DirectionsFeatureItemType = DirectionsFeatureItemType.JustMap,
    val selectedFavoriteItemId: Int? = null,
    val collectionsBottomSheetType: CollectionBottomSheetType = CollectionBottomSheetType.Hidden
)
