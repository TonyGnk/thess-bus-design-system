package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionsLookTargetType


@Composable
fun DirectionsLookTarget(
    modifier: Modifier = Modifier,
    givenType: DirectionsLookTargetType = DirectionsLookTargetType.JustMap,
    query: String,
    onBack: () -> Unit,
    horizontalPadding: Int = 0,
    mapModifier: Modifier = Modifier,
) {
    DestinationOverviewMapLayer(
        givenType = givenType,
    )
    DestinationOverviewUiLayer(
        onBack = onBack,
        query = query,
        horizontalPadding = horizontalPadding,
        modifier = modifier
    )
}
