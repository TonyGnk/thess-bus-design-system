package com.tonyGnk.thessBus.designSystem.mobile.components.containment.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.CameraPosition
import org.ramani.compose.MapLibre
import org.ramani.compose.MapLibreComposable
import org.ramani.compose.MapProperties
import org.ramani.compose.UiSettings

@Composable
fun RamaniMap(
    modifier: Modifier = Modifier,
    styleBuilder: Style.Builder,
    cameraPosition: CameraPosition,
    onMapLongClick: (LatLng) -> Unit = {},
    content: (@Composable @MapLibreComposable () -> Unit),
) {
    val uiSettings = rememberSaveable {
        UiSettings(
            isLogoEnabled = false,
            isAttributionEnabled = false,
            rotateGesturesEnabled = false,
        )
    }
    val mapProperties = rememberSaveable {
        MapProperties(
            minZoom = 11.0,
            minPitch = 11.0,
            maxZoom = 18.0,
            maxPitch = 18.0,
        )
    }

    MapLibre(
        modifier = modifier,
        uiSettings = uiSettings,
        properties = mapProperties,
        onMapLongClick = onMapLongClick,
        styleBuilder = styleBuilder,
        cameraPosition = cameraPosition
    ) {
        content()
    }

}
