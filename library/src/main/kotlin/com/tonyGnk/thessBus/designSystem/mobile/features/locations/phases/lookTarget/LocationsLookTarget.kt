package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.CameraPositionState
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.map.CustomCameraPosition
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType

@Stable
data class DirectionsLookTargetItems(
    val givenType: DirectionsFeatureItemType,
    val query: String,
    val onPickItem: (DirectionsFeatureItemType) -> Unit,
    val onBack: () -> Unit,
    val applySystemBarPadding: Boolean,
    val paddingValues: PaddingValues,
    val onCameraPositionChanged: (CustomCameraPosition) -> Unit,
) {
    companion object {
        val preview = DirectionsLookTargetItems(
            givenType = DirectionsFeatureItemType.JustMap,
            query = "",
            onPickItem = {},
            onBack = {},
            applySystemBarPadding = true,
            paddingValues = PaddingValues(0.dp),
            onCameraPositionChanged = {},
        )
    }
}

@Composable
fun LocationsLookTarget(
    modifier: Modifier = Modifier,
    items: DirectionsLookTargetItems = DirectionsLookTargetItems.preview,
) {
    DestinationOverviewMapLayer(
        items = items
    )
    DestinationOverviewUiLayer(
        modifier = modifier,
        items = items
    )
}
