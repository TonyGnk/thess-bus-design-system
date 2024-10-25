package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.data

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeFavorites
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeHistory
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.CollectionBottomSheetType

@Stable
data class LocationsPickTargetItems(
    val onBack: () -> Unit,
    val onCategoriesClick: () -> Unit,
    val applySystemBarPadding: Boolean,
    val collectionsBottomSheetType: CollectionBottomSheetType,
    val setBottomSheetType: (CollectionBottomSheetType) -> Unit,
    val historyState: HistoryState = HistoryState(),
    val horizontalPadding: PaddingValues,
    val searchState: SearchState,
    val collectionsState: CollectionState,
    val sharedElementIds: SharedElementIds = SharedElementIds()
) {
    companion object {
        val preview = LocationsPickTargetItems(
            searchState = SearchState(),
            collectionsState = CollectionState(),
            onBack = {},
            setBottomSheetType = {},
            collectionsBottomSheetType = CollectionBottomSheetType.Hidden,
            onCategoriesClick = {},
            applySystemBarPadding = true,
            horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
        )
    }


    data class SharedElementIds(
        val placeHolder: String = "placeHolder",
        val text: String = "text",
        val searchBar: String = "searchBar",
        val magnifier: String = "magnifier"
    )

    data class SearchState(
        val onSearchIme: () -> Unit = {},
        val onResultClick: (DirectionsFeatureItemType.SingleItem?) -> Unit = {},
        val textFieldState: TextFieldState = TextFieldState(),
        val results: List<DirectionsFeatureItemType.SingleItem> = emptyList(),
        val requestFocus: Boolean = false,
        val clearText: () -> Unit = {}
    )

    data class CollectionState(
        val onAddCollectionClick: () -> Unit = {},
        val items: List<DirectionsFeatureItemType.SingleItem> = PickTargetFakeFavorites,
        val selectedId: Int? = null,
        val onNotConfiguredClick: () -> Unit = {},
        val onSavedLocationClick: (DirectionsFeatureItemType.SingleItem?) -> Unit = {},
        val updateSelectedFavoriteItemId: (Int?) -> Unit = {}
    )

    data class HistoryState(
        val items: List<DirectionsFeatureItemType.SingleItem> = PickTargetFakeHistory,
        val onClick: (DirectionsFeatureItemType.SingleItem) -> Unit = {}
    )
}
