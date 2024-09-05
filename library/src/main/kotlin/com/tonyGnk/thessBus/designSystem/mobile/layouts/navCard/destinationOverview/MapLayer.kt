package com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.destinationOverview

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon


@Composable
fun DestinationOverviewMapLayer(
    latitude: Double = 40.63231,
    longitude: Double = 22.96331
) {
    val markerState = rememberMarkerState(
        position = LatLng(latitude, longitude)
    )
    val properties = MapProperties(
        minZoomPreference = 10f,
    )
    val cameraPositionState: CameraPositionState = rememberCameraPositionState(
        init = {
            position = CameraPosition(
                LatLng(latitude, longitude),
                16f,
                20f,
                0f
            )
        }
    )
    val uiSettings = MapUiSettings(
        myLocationButtonEnabled = false,
        mapToolbarEnabled = false,
        compassEnabled = false,
        zoomControlsEnabled = false,
    )

    GoogleMap(
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings,
        properties = properties,
        onMapLoaded = {
            Log.d("maps", "Map loaded")
        },
        mapColorScheme = ComposeMapColorScheme.FOLLOW_SYSTEM,
        onPOIClick = {
            val item = it
            Log.d("maps", item.name + item.latLng + item.placeId)
        }
    ) {
//        Polyline(
//            points = listOf(
//                LatLng(latitude, longitude),
//                LatLng(40.63100, 22.96331),
//            ),
//            color = AppColor.primary,
//            clickable = false,
//            geodesic = false,
//        )
//        Polygon(
//            points = listOf(
//                LatLng(40.63231, 22.96331),
//                LatLng(40.62231, 22.96331),
//                LatLng(40.62231, 22.97331),
//                LatLng(40.63231, 22.97331),
//            ),
//            fillColor = AppColor.primary.copy(alpha = 0.5f),
//            strokeColor = AppColor.primary,
//            clickable = false,
//            geodesic = true,
//        )
        Marker(
            state = markerState,
            draggable = true,
            flat = false,
            zIndex = 0f,
            icon = BitmapDescriptorFactory.defaultMarker(12f)
        )
    }
}

@Composable
@AppPreview.Dark
private fun Preview() = DestinationOverviewMapLayer(
)
