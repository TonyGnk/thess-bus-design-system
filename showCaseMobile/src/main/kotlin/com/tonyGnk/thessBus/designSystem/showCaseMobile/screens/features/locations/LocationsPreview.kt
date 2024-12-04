package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.data.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.map.DirectionsMap
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.map.LocationsLookTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.overview.DirectionsOverview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.overview.DirectionsOverviewItems
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.favorites.deleteFakeFavorite
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.recent.FakeRecentItems
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.target.DirectionsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.target.LocationsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.shared.card.LocationsCard
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.shared.card.LocationsCardItems
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getExtendedWindowInsets
import org.maplibre.android.maps.Style

@Composable
fun LocationsOverviewPre(
    modifier: Modifier = Modifier,
    model: LocationsFeatureModel,
    goToPickTarget: () -> Unit = {},
) {
    val state by model.state.collectAsStateWithLifecycle()
    val placeHolder = state.textState.text.toString()

    val items = DirectionsOverviewItems(
        pickedItem = state.pickedItem,
        clearPickedItem = model::clearPickedItem,
    )

    DirectionsOverview(
        goToPickTarget = goToPickTarget,
        items = items,
        placeHolder = placeHolder
    )
}


@Composable
fun LocationsStartPre(
    modifier: Modifier = Modifier,
    goToPickTarget: () -> Unit,
) {
    /*
        val placeHolder: String = "placeHolder",
    val text: String = "text",
    val searchBar: String = "searchBar",
    val magnifier: String = "magnifier"
     */
    val items = LocationsCardItems(
        sharedElementCard = "searchBar",
        sharedElementText = "placeHolder",
        sharedElementMagnifier = "magnifier",
        onSearchClick = goToPickTarget,
    )
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = getExtendedWindowInsets(
            DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
        )
    ) {
        item {
            LocationsCard(
                modifier = Modifier
                    .padding(
                        horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
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
        onBack = onBack,
        searchState = LocationsPickTargetItems.SearchState(
            requestFocus = true,
            onSearchIme = {},
            onClick = { item ->
                model.setGivenType(item)
                model.setTextField(item?.title)
                onBack()
            },
            clearText = model::clearPickedItem,
            textFieldState = state.textState,
            results = PickTargetFakeResults,
        ),

        applySystemBarPadding = true,
        horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
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

    DirectionsPickTarget(
        modifier = modifier.fillMaxSize(),
        items = items
    )
}

@Composable
fun DirectionsMapPre(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    goToPickStart: () -> Unit,
    styleBuilder: Style.Builder,
    model: LocationsFeatureModel,
) {
    val state by model.state.collectAsStateWithLifecycle()

    val items = LocationsLookTargetItems(
        pickedItem = state.pickedItem,
        onMapLongClick = model::onMapLongClick,
    )

    DirectionsMap(
        modifier = modifier,
        styleBuilder = styleBuilder,
        items = items
    )
}
