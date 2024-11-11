package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.data

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeHistory
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.CollectionBottomSheetType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview.collection.FavoriteItem
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview.collection.FakeFavoritesItems

@Stable
data class LocationsPickTargetItems(
    val onBack: () -> Unit,
//    val onCategoriesClick: () -> Unit,
    val applySystemBarPadding: Boolean,
    val collectionsBottomSheetType: CollectionBottomSheetType,
    val setBottomSheetType: (CollectionBottomSheetType) -> Unit,
    val historyState: HistoryState = HistoryState(),
    val horizontalPadding: PaddingValues,
    val searchState: SearchState,
    val favoritesState: FavoritesState,
    val sharedElementIds: SharedElementIds = SharedElementIds()
) {
    companion object {
        val preview = LocationsPickTargetItems(
            searchState = SearchState(),
            favoritesState = FavoritesState(),
            onBack = {},
            setBottomSheetType = {},
            collectionsBottomSheetType = CollectionBottomSheetType.Hidden,
            // onCategoriesClick = {},
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

    data class FavoritesState(
        val items: List<FavoriteItem> = FakeFavoritesItems,
        val selectedId: Int? = null,
        val onAdd: () -> Unit = {},
        val onNotConfigured: () -> Unit = {},
        val onClick: (DirectionsFeatureItemType.SingleItem?) -> Unit = {},
        val onLongPress: (Int?) -> Unit = {},
        val onEditItem: (Int) -> Unit = {},
        val onDeleteItem: (Int) -> Unit = {},
        val onUnpinItem: (Int) -> Unit = {}
    )

    data class HistoryState(
        val items: List<DirectionsFeatureItemType.SingleItem> = PickTargetFakeHistory,
        val onClick: (DirectionsFeatureItemType.SingleItem) -> Unit = {}
    )


    data class SearchState(
        val onSearchIme: () -> Unit = {},
        val onClick: (DirectionsFeatureItemType.SingleItem?) -> Unit = {},
        val textFieldState: TextFieldState = TextFieldState(),
        val results: List<DirectionsFeatureItemType.SingleItem> = emptyList(),
        val requestFocus: Boolean = false,
        val clearText: () -> Unit = {}
    )
}
