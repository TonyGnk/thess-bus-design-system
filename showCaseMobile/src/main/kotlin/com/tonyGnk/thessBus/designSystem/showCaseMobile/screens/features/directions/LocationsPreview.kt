package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions

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
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.preview.DirectionsPreviewModel

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
    model: DirectionsPreviewModel,
    onBack: () -> Unit,
    goToLookTarget: () -> Unit,
    goToCategories: () -> Unit,
) {
    val state by model.state.collectAsStateWithLifecycle()

    val items = LocationsPickTargetItems(
        onBack = onBack,
        onSearchIme = {},
        onResultClick = {
            goToLookTarget()
            model.setGivenType(it)
        },
        clearText = model::clearText,
        onCategoriesClick = goToCategories,
        requestFocus = false,//TODO
        applySystemBarPadding = true,
        textState = state.textState,
        results = PickTargetFakeResults,
        sharedElementText = "text",
        sharedElementCard = "card",
        sharedElementMagnifier = "icon",
        favorites = PickTargetFakeFavorites,
        history = PickTargetFakeHistory,
        horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
        onItemClick = {},
        onAddCollectionClick = {}
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
    model: DirectionsPreviewModel,
) {
    val state by model.state.collectAsStateWithLifecycle()

    val items = DirectionsLookTargetItems(
        applySystemBarPadding = true,
        query = state.textState.text.toString(),
        setType = { model.setGivenType(it) },
        cameraPositionState = state.cameraPositionState,
        paddingValues = PaddingValues(),
        onBack = onBack,
        givenType = state.givenType,
    )

    LocationsLookTarget(
        modifier = modifier,
        items = items
    )
}
