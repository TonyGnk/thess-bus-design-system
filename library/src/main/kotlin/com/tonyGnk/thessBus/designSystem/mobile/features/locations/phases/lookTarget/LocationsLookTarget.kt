package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.map.CustomCameraPosition
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType

data class LocationsLookTargetSharedElements(
    val searchBar: String = "searchBar",
)

@Stable
data class LocationsLookTargetItems(
    val givenType: DirectionsFeatureItemType,
    val query: String,
    val onPickItem: (DirectionsFeatureItemType) -> Unit,
    val goToPickTargetResults: () -> Unit,
    val clearTextField: () -> Unit,
    val applySystemBarPadding: Boolean,
    val paddingValues: PaddingValues,
    val sharedElements: LocationsLookTargetSharedElements,
    val onCameraPositionChanged: (CustomCameraPosition) -> Unit,
) {
    companion object {
        val preview = LocationsLookTargetItems(
            givenType = DirectionsFeatureItemType.JustMap,
            query = "",
            onPickItem = {},
            goToPickTargetResults = {},
            applySystemBarPadding = true,
            paddingValues = PaddingValues(0.dp),
            onCameraPositionChanged = {},
            clearTextField = {},
            sharedElements = LocationsLookTargetSharedElements(),
        )
    }
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
