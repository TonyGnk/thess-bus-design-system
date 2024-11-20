package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsCard
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsCardItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget.LocationsLookTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget.LocationsLookTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickStart.LocationsPickStart
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.target.LocationsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.target.LocationsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.favorites.deleteFakeFavorite
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.recent.FakeRecentItems
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getExtendedWindowInsets
import org.maplibre.android.maps.Style

@Composable
fun LocationsStartPre(
    modifier: Modifier = Modifier,
    goToPickTarget: () -> Unit,
) {
    val items = LocationsCardItems(
        sharedElementCard = "card",
        sharedElementText = "text",
        sharedElementMagnifier = "icon",
        onSearchClick = goToPickTarget,
    )
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = getExtendedWindowInsets()
    ) {
        item {
            LocationsCard(
                modifier = Modifier
                    .padding(
                        horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
                    ),
                items = items
            )
        }
    }
}

@Composable
fun LocationsPickTargetPre(
    modifier: Modifier = Modifier,
    model: LocationsFeatureModel,
    onBack: () -> Unit,
    goToLookTarget: () -> Unit,
    goToPickStart: () -> Unit,
    goToCategories: () -> Unit,
) {
    val state by model.state.collectAsStateWithLifecycle()

    val items = LocationsPickTargetItems(
        searchState = LocationsPickTargetItems.SearchState(
            requestFocus = false,
            onSearchIme = {},
            onClick = { item ->
                model.setGivenType(item)
                model.setTextField(item?.title)
                goToLookTarget()
            },
            clearText = model::clearSearchField,
            textFieldState = state.textState,
            results = PickTargetFakeResults,
        ),

        applySystemBarPadding = true,
        horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
        onBack = onBack,
        favoritesState = LocationsPickTargetItems.FavoritesState(
            onClick = { item ->
                model.setGivenType(item)
                //goToPickStart()
            },
            onDeleteItem = { deleteFakeFavorite(it) },
            selectedId = state.selectedFavoriteItemId,
            onNotConfigured = {},
            updateSelectedFavoriteItemId = model::updateSelectedFavoriteItemId,
        ),
        collectionsBottomSheetType = state.collectionsBottomSheetType,
        setBottomSheetType = model::setBottomSheetType,
        recentState = LocationsPickTargetItems.RecentState(
            items = FakeRecentItems,
            onClick = {},
        ),
    )

    LocationsPickTarget(
        modifier = modifier.fillMaxSize(),
        items = items
    )
}

@Composable
fun LocationsLookTargetPre(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    goToPickStart: () -> Unit,
    styleBuilder: Style.Builder,
    model: LocationsFeatureModel,
) {
    val state by model.state.collectAsStateWithLifecycle()

    val items = LocationsLookTargetItems(
        applySystemBarPadding = true,
        query = state.textState.text.toString(),
        paddingValues = PaddingValues(),
        goToPickTargetResults = onBack,
        onPickItem = {
            model.clearSearchField()
            model.setGivenType(it)
        },
        onNavigate = {
            goToPickStart()
        },
        pickedItem = state.pickedItem,
        clearTextField = model::clearSearchField,
        onCameraPositionChanged = { model.updateCameraPosition(it) },
    )

    LocationsLookTarget(
        modifier = modifier,
        styleBuilder = styleBuilder,
        items = items
    )
}

@Composable
fun LocationsPickStartPre(
    modifier: Modifier = Modifier,
    model: LocationsFeatureModel,
    goToDirections: (String, Double, Double, String, Double, Double) -> Unit,
    onBack: () -> Unit,
) {
    val state by model.state.collectAsStateWithLifecycle()
    val pickedItem = state.pickedItem
    val onResultClick = { item: DirectionsFeatureItemType.SingleItem? ->
        if (item != null && pickedItem is DirectionsFeatureItemType.SingleItem) goToDirections(
            pickedItem.title,
            pickedItem.lat,
            pickedItem.lon,
            item.title,
            item.lat,
            item.lon
        )
    }

    val items = LocationsPickTargetItems(
        searchState = LocationsPickTargetItems.SearchState(
            requestFocus = true,
            onSearchIme = {},
            onClick = onResultClick,
            clearText = {},
            textFieldState = rememberTextFieldState(),
            results = PickTargetFakeResults,
        ),

        applySystemBarPadding = true,
        horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
        onBack = onBack,
        favoritesState = LocationsPickTargetItems.FavoritesState(
            items = emptyList(),
            selectedId = state.selectedFavoriteItemId,
            onNotConfigured = {},
            updateSelectedFavoriteItemId = model::updateSelectedFavoriteItemId,
        ),
        collectionsBottomSheetType = state.collectionsBottomSheetType,
        setBottomSheetType = model::setBottomSheetType,
        recentState = LocationsPickTargetItems.RecentState(
            items = FakeRecentItems,
            onClick = {},
        ),
    )

    LocationsPickStart(
        modifier = modifier.fillMaxSize(),
        items = items,
    )
}
