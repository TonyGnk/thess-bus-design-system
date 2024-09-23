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
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget.DirectionsLookTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget.LocationsLookTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.LocationsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.LocationsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.LocationsCard
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.LocationsCardItems
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
        requestFocus = true,
        applySystemBarPadding = true,
        textState = state.textState,
        onCategoriesClick = goToCategories,
        onResultClick = {
            goToLookTarget()
            model.setGivenType(it)
        },
        onSearchIme = {},
        clearText = model::clearText,
        results = emptyList(),
        sharedElementCard = "card",
        sharedElementText = "text",
        sharedElementMagnifier = "icon",
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
