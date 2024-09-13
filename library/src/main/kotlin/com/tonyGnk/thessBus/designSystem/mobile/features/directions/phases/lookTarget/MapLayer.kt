package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget

import android.util.Log
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
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionsLookTargetType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetItem
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetPointsType


@Composable
fun DestinationOverviewMapLayer(
    givenType: DirectionsLookTargetType = DirectionsLookTargetType.JustMap,
) {
    when (givenType) {
        is DirectionsLookTargetType.JustMap -> {}
        is DirectionsLookTargetType.MultipleItems -> {}
        is PickTargetItem -> when (givenType.points) {
            is PickTargetPointsType.Multi -> MapMulti(givenType, givenType.points)
            is PickTargetPointsType.Single -> MapSingle(givenType, givenType.points)
        }
    }
}

private fun findCenter(listOfPairs: List<Pair<Double, Double>>): Pair<Double, Double> {
    var sumLat = 0.0
    var sumLng = 0.0
    for (pair in listOfPairs) {
        sumLat += pair.first
        sumLng += pair.second
    }
    return Pair(sumLat / listOfPairs.size, sumLng / listOfPairs.size)
}

@Composable
private fun MapMulti(
    item: PickTargetItem,
    points: PickTargetPointsType.Multi
) {
    val listOfPairs = points.points

    //Find the center of the points and add marker
    val center = findCenter(listOfPairs)
    val markerState = rememberMarkerState(
        position = LatLng(center.first, center.second)
    )
    val properties = MapProperties(
        minZoomPreference = 10f,
    )
    val cameraPositionState: CameraPositionState = rememberCameraPositionState(
        init = {
            position = CameraPosition(
                LatLng(center.first, center.second),
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
        mapColorScheme = ComposeMapColorScheme.FOLLOW_SYSTEM,
    ) {
        Marker(
            state = markerState,
            draggable = false,
            flat = false,
            zIndex = 0f,
            icon = BitmapDescriptorFactory.defaultMarker(12f)
        )
        Polygon(
            points = listOfPairs.map { LatLng(it.first, it.second) },
            fillColor = AppColor.primary.copy(alpha = 0.5f),
            strokeColor = AppColor.primary,
            clickable = false,
            geodesic = true,
        )
    }
}

@Composable
private fun MapSingle(
    item: PickTargetItem,
    points: PickTargetPointsType.Single
) {

    //Find the center of the points and add marker
    val markerState = rememberMarkerState(
        position = LatLng(points.lat, points.lon)
    )
    val properties = MapProperties(
        minZoomPreference = 10f,
    )
    val cameraPositionState: CameraPositionState = rememberCameraPositionState(
        init = {
            position = CameraPosition(
                LatLng(points.lat, points.lon),
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
        mapColorScheme = ComposeMapColorScheme.FOLLOW_SYSTEM,
    ) {
        Marker(
            state = markerState,
            draggable = false,
            flat = false,
            zIndex = 0f,
            icon = BitmapDescriptorFactory.defaultMarker(12f)
        )
    }
}


@Composable
fun DestinationOverviewMapLayer2(
    givenType: DirectionsLookTargetType? = null,
) {
    val markerState = rememberMarkerState(
        // position = LatLng(latitude, longitude)
    )
    val properties = MapProperties(
        minZoomPreference = 10f,
    )
//    val cameraPositionState: CameraPositionState = rememberCameraPositionState(
//        init = {
//            position = CameraPosition(
//                LatLng(latitude, longitude),
//                16f,
//                20f,
//                0f
//            )
//        }
//    )
    val uiSettings = MapUiSettings(
        myLocationButtonEnabled = false,
        mapToolbarEnabled = false,
        compassEnabled = false,
        zoomControlsEnabled = false,
    )

    GoogleMap(
        // cameraPositionState = cameraPositionState,
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

//@Composable
//@AppPreview.Dark
//private fun Preview() = DestinationOverviewMapLayer(
//    latitude = 40.63231,
//    longitude = 22.96331
//)
