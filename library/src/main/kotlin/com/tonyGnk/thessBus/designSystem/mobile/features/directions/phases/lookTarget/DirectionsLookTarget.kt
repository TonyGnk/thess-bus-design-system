package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun DirectionsLookTarget(
    modifier: Modifier = Modifier,
    query: String,
    onBack: () -> Unit,
    horizontalPadding: Int = 0,
    mapModifier: Modifier = Modifier,
    latitude: Double = 40.63231,
    longitude: Double = 22.96331,
    poiTitle: String,
    poiCategory: String,
) {
    DestinationOverviewMapLayer(latitude, longitude)
    DestinationOverviewUiLayer(
        onBack = onBack,
        query = query,
        poiTitle = poiTitle,
        poiCategory = poiCategory,
        horizontalPadding = horizontalPadding,
        modifier = modifier
    )
}
