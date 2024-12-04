package com.tonyGnk.thessBus.designSystem.mobile.features.directions.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.map.RamaniMap
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.data.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues.DEFAULT_ITEM_ZOOM
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues.DEFAULT_TILT
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues.DEFAULT_ZOOM
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.maplibre.android.style.layers.Property.ICON_ANCHOR_CENTER
import org.ramani.compose.CameraPosition
import org.ramani.compose.Symbol


@Stable
data class LocationsLookTargetItems(
    val pickedItem: DirectionsFeatureItemType,
    val onMapLongClick: (LatLng) -> Unit,
) {
    companion object {
        val preview = LocationsLookTargetItems(
            pickedItem = DirectionsFeatureItemType.JustMap,
            onMapLongClick = {}
        )
    }
}

@Composable
fun DirectionsMap(
    modifier: Modifier = Modifier,
    styleBuilder: Style.Builder,
    items: LocationsLookTargetItems = LocationsLookTargetItems.preview,
) {
    val cameraPosition = rememberSaveable {
        CameraPosition(
            target = getLatLngForType(items.pickedItem),
            zoom = if (items.pickedItem is DirectionsFeatureItemType.Point) DEFAULT_ITEM_ZOOM else DEFAULT_ZOOM,
            tilt = DEFAULT_TILT,
        )
    }




    RamaniMap(
        modifier = modifier,
        styleBuilder = styleBuilder,
        onMapLongClick = items.onMapLongClick,
        cameraPosition = cameraPosition
    ) {
        when (items.pickedItem) {
            is DirectionsFeatureItemType.JustMap -> {}
            is DirectionsFeatureItemType.MultipleItems -> {}
            is DirectionsFeatureItemType.Point ->
                Symbol(
                    center = getLatLngForType(items.pickedItem),
                    size = 0.26f, // Adjust size to make it visible
                    color = "#FF0000", // Can be used for colorizing the image
                    isDraggable = false,
                    imageId = R.drawable.marker2, // Replace with your drawable resource ID
                    imageAnchor = ICON_ANCHOR_CENTER,
                )
        }
    }
}


fun getLatLngForType(givenType: DirectionsFeatureItemType): LatLng {
    return when (givenType) {
        is DirectionsFeatureItemType.JustMap,
        is DirectionsFeatureItemType.MultipleItems -> LatLng(
            DefaultMapValues.DEFAULT_LAT, DefaultMapValues.DEFAULT_LON
        )

        is DirectionsFeatureItemType.Point -> LatLng(givenType.lat, givenType.lon)
    }
}
