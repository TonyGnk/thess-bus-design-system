package com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.destinationOverview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun DestinationOverview(
    query: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
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
        modifier = modifier
    )
}
