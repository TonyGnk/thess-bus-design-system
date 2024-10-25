package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.map.CustomCameraPosition
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType


@Stable
data class LocationsLookTargetItems(
    val pickedItem: DirectionsFeatureItemType,
    val query: String,
    val onPickItem: (DirectionsFeatureItemType) -> Unit,
    val goToPickTargetResults: () -> Unit,
    val clearTextField: () -> Unit,
    val applySystemBarPadding: Boolean,
    val onNavigate: (DirectionsFeatureItemType.SingleItem) -> Unit,
    val paddingValues: PaddingValues,
    val sharedElements: SharedElementIds = SharedElementIds(),
    val onCameraPositionChanged: (CustomCameraPosition) -> Unit,
) {
    companion object {
        val preview = LocationsLookTargetItems(
            pickedItem = DirectionsFeatureItemType.JustMap,
            query = "",
            onPickItem = {},
            goToPickTargetResults = {},
            applySystemBarPadding = true,
            paddingValues = PaddingValues(0.dp),
            onCameraPositionChanged = {},
            clearTextField = {},
            sharedElements = SharedElementIds(),
            onNavigate = {},
        )
    }

    data class SharedElementIds(
        val searchBar: String = "searchBar",
    )
}

@Composable
fun LocationsLookTarget(
    modifier: Modifier = Modifier,
    items: LocationsLookTargetItems = LocationsLookTargetItems.preview,
) {
    DestinationOverviewMapLayer(
        items = items
    )
    DestinationOverviewUiLayer(
        modifier = modifier,
        items = items
    )
}
