package com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.target

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar.SearchBarSharedElementIds
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.data.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.bottomSheet.CollectionBottomSheetType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.favorites.FakeFavoritesItems
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.favorites.FavoriteItem
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.recent.FakeRecentItems
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.recent.RecentItem

@Stable
data class LocationsPickTargetItems(
    val onBack: () -> Unit,
    val applySystemBarPadding: Boolean,
    val collectionsBottomSheetType: CollectionBottomSheetType,
    val setBottomSheetType: (CollectionBottomSheetType) -> Unit,
    val recentState: RecentState,
    val horizontalPadding: PaddingValues,
    val searchState: SearchState,
    val favoritesState: FavoritesState,
    val searchSharedElements: SearchBarSharedElementIds = SearchBarSharedElementIds(),
) {
    companion object {
        val preview = LocationsPickTargetItems(
            searchState = SearchState(),
            favoritesState = FavoritesState(),
            recentState = RecentState(),
            onBack = {},
            setBottomSheetType = {},
            collectionsBottomSheetType = CollectionBottomSheetType.Hidden,
            applySystemBarPadding = true,
            horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
        )
    }

    data class FavoritesState(
        val items: List<FavoriteItem> = FakeFavoritesItems,
        val selectedId: Int? = null,
        val onAdd: () -> Unit = {},
        val onNotConfigured: () -> Unit = {},
        val onClick: (DirectionsFeatureItemType.Point?) -> Unit = {},
        val updateSelectedFavoriteItemId: (Int?) -> Unit = {},
        val onEditItem: (Int) -> Unit = {},
        val onDeleteItem: (Int) -> Unit = {},
        val onUnpinItem: (Int) -> Unit = {}
    )

    data class RecentState(
        val items: List<RecentItem> = FakeRecentItems,
        val onClick: (RecentItem) -> Unit = {}
    )


    data class SearchState(
        val onSearchIme: () -> Unit = {},
        val onClick: (DirectionsFeatureItemType.Point?) -> Unit = {},
        val onPickOnMap: () -> Unit = {},
        val textFieldState: TextFieldState = TextFieldState(),
        val results: List<DirectionsFeatureItemType.Point> = emptyList(),
        val requestFocus: Boolean = false,
        val clearText: () -> Unit = {}
    )
}
