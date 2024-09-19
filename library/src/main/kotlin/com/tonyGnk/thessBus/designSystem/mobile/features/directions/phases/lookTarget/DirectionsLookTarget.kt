package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionsFeatureItemType


@Composable
fun DirectionsLookTarget(
    modifier: Modifier = Modifier,
    givenType: DirectionsFeatureItemType = DirectionsFeatureItemType.JustMap,
    query: String = "",
    setType: (DirectionsFeatureItemType) -> Unit = {},
    onBack: () -> Unit = {},
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    applySystemBarPadding: Boolean = true,
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    DestinationOverviewMapLayer(
        setType = setType,
        givenType = givenType,
        cameraPositionState = cameraPositionState,
    )
    DestinationOverviewUiLayer(
        onBack = onBack,
        applySystemBarPadding = applySystemBarPadding,
        query = query,
        setType = setType,
        paddingValues = paddingValues,
        givenType = givenType,
        modifier = modifier
    )
}
