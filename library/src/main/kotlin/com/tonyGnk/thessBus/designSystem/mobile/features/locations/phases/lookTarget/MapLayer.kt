package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.map.MyLibreMap
import org.maplibre.android.maps.Style


@Composable
fun DestinationOverviewMapLayer(
    items: LocationsLookTargetItems,
    styleBuilder: Style.Builder,

    ) {
    MyLibreMap(
        modifier = Modifier.fillMaxSize(),
        pickedItem = items.pickedItem,
        styleBuilder = styleBuilder
    ) {}
}
