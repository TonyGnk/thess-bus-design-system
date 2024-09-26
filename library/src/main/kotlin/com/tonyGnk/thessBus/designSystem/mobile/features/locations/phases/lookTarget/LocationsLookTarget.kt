package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.CameraPositionState
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType

@Stable
data class DirectionsLookTargetItems(
    val givenType: DirectionsFeatureItemType,
    val query: String,
    val setType: (DirectionsFeatureItemType) -> Unit,
    val onBack: () -> Unit,
    val cameraPositionState: CameraPositionState,
    val applySystemBarPadding: Boolean,
    val paddingValues: PaddingValues,
) {
    companion object {
        val preview = DirectionsLookTargetItems(
            givenType = DirectionsFeatureItemType.JustMap,
            query = "",
            setType = {},
            onBack = {},
            cameraPositionState = CameraPositionState(),
            applySystemBarPadding = true,
            paddingValues = PaddingValues(0.dp),
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
