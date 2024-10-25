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
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeFavorites
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget.LocationsLookTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget.LocationsLookTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.LocationsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.data.LocationsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsCard
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsCardItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickStart.LocationsPickStart
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.extendedWindowInsets
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.locations.preview.LocationsFeatureModel

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
        contentPadding = extendedWindowInsets
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
            onResultClick = { item ->
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
        onCategoriesClick = goToCategories,
        collectionsState = LocationsPickTargetItems.CollectionState(
            onSavedLocationClick = { item ->
                model.setGivenType(item)
                goToPickStart()
            },
            items = PickTargetFakeFavorites,
            selectedId = state.selectedFavoriteItemId,
            onNotConfiguredClick = {},
            updateSelectedFavoriteItemId = model::updateSelectedFavoriteItemId,
        ),
        collectionsBottomSheetType = state.collectionsBottomSheetType,
        setBottomSheetType = model::setBottomSheetType,
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
        items = items
    )
}

@Composable
fun LocationsPickStartPre(
    modifier: Modifier = Modifier,
    model: LocationsFeatureModel,
    onBack: () -> Unit,
) {
    val state by model.state.collectAsStateWithLifecycle()

//    val items = LocationsPickTargetItems(
//        searchState = LocationsPickTargetItems.SearchState(
//            requestFocus = true,
//            onSearchIme = {},
//            onResultClick = { item ->
//                model.setGivenType(item)
//                model.setTextField(item?.title)
//                goToLookTarget()
//            },
//            clearText = model::clearSearchField,
//            textFieldState = state.textState,
//            results = PickTargetFakeResults,
//        ),
//
//        applySystemBarPadding = true,
//        horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
//        onBack = onBack,
//        onCategoriesClick = goToCategories,
//        collectionsState = LocationsPickTargetItems.CollectionState(
//            onClick = {},
//            items = PickTargetFakeFavorites,
//            selectedId = state.selectedFavoriteItemId,
//            onNotConfiguredClick = {},
//            updateSelectedFavoriteItemId = model::updateSelectedFavoriteItemId,
//        ),
//        collectionsBottomSheetType = state.collectionsBottomSheetType,
//        setBottomSheetType = model::setBottomSheetType,
//    )

    LocationsPickStart(
        modifier = modifier.fillMaxSize(),
    )
}
