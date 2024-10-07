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
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeHistory
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget.DirectionsLookTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget.LocationsLookTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.LocationsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.LocationsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsCard
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsCardItems
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedWindowInsets
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
    goToCategories: () -> Unit,
) {
    val state by model.state.collectAsStateWithLifecycle()

    val items = LocationsPickTargetItems(
        onBack = onBack,
        requestFocus = false,//TODO
        applySystemBarPadding = true,
        sharedElementText = "text",
        sharedElementCard = "card",
        sharedElementMagnifier = "icon",
        horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
        onSearchIme = {},
        clearText = model::clearSearchField,
        onCategoriesClick = goToCategories,
        textState = state.textState,
        results = PickTargetFakeResults,
        favorites = PickTargetFakeFavorites,
        history = PickTargetFakeHistory,
        onPickItem = { item ->
            goToLookTarget()
            model.setGivenType(item)
        },
        onAddCollectionClick = {},
        onFavoriteNotConfiguredClick = {},
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
    model: LocationsFeatureModel,
) {
    val state by model.state.collectAsStateWithLifecycle()

    val items = DirectionsLookTargetItems(
        applySystemBarPadding = true,
        query = state.textState.text.toString(),
        paddingValues = PaddingValues(),
        onBack = onBack,
        onPickItem = {
            model.setGivenType(it)
        },
        givenType = state.givenType,
        onCameraPositionChanged = { model.updateCameraPosition(it) },
    )

    LocationsLookTarget(
        modifier = modifier,
        items = items
    )
}
