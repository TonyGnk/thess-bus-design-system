package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget

import android.util.Log
import androidx.compose.material3.ModalBottomSheetDefaults.properties
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PointOfInterest
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetPointsType


@Composable
fun DestinationOverviewMapLayer(
    items: DirectionsLookTargetItems
) {
    MyGoogleMap(
        setType = items.setType,
        cameraPositionState = items.cameraPositionState
    ) {
        when (items.givenType) {
            is DirectionsFeatureItemType.JustMap -> {}

            is DirectionsFeatureItemType.MultipleItems -> {}
            is DirectionsFeatureItemType.SingleItem -> when (items.givenType.points) {
                is PickTargetPointsType.Multi -> {
                    val listOfPairs = items.givenType.points.points
                    val center = findCenter(listOfPairs)
                    val markerState = rememberMarkerState(
                        position = LatLng(center.first, center.second)
                    )

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

                is PickTargetPointsType.Single -> {
                    val markerState = rememberMarkerState(
                        position = LatLng(items.givenType.points.lat, items.givenType.points.lon)
                    )

                    Marker(
                        state = markerState,
                        draggable = false,
                        flat = false,
                        zIndex = 0f,
                        icon = BitmapDescriptorFactory.defaultMarker(12f)
                    )
                }
            }
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
fun MyGoogleMap(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    setType: (DirectionsFeatureItemType) -> Unit,
    content: @Composable @GoogleMapComposable () -> Unit = {},
) {
    val mapUiSettings = remember {
        MapUiSettings(
            myLocationButtonEnabled = false,
            mapToolbarEnabled = false,
            compassEnabled = false,
            zoomControlsEnabled = false,
        )
    }

    val mapProperties = remember {
        MapProperties(
            //  minZoomPreference = 10f,
        )
    }

    val onPOIClick = remember {
        { poi: PointOfInterest ->
            setType(
                DirectionsFeatureItemType.SingleItem(
                    title = poi.name,
                    id = poi.placeId,
                    iconRes = 0,
                    subTitle = "Σημείο στο χάρτη",
                    points = PickTargetPointsType.Single(
                        lat = poi.latLng.latitude,
                        lon = poi.latLng.longitude
                    )
                )
            )
        }
    }

    val onMapLongClick = remember {
        { latLng: LatLng ->
            setType(
                DirectionsFeatureItemType.SingleItem(
                    title = "Σημείο",
                    id = "",
                    iconRes = 0,
                    subTitle = "",
                    points = PickTargetPointsType.Single(
                        lat = latLng.latitude,
                        lon = latLng.longitude
                    )
                )
            )
        }
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        onPOIClick = onPOIClick,
        onMapLongClick = onMapLongClick,
        properties = mapProperties,
        mapColorScheme = ComposeMapColorScheme.FOLLOW_SYSTEM,
        content = content
    )

//    GoogleMap(
//        modifier = modifier,
//        cameraPositionState = cameraPositionState,
//        uiSettings = MapUiSettings(
//            myLocationButtonEnabled = false,
//            mapToolbarEnabled = false,
//            compassEnabled = false,
//            zoomControlsEnabled = false,
//        ),
//        onPOIClick = { poi ->
//            setType(
//                DirectionsFeatureItemType.SingleItem(
//                    title = poi.name,
//                    id = poi.placeId,
//                    iconRes = 0,
//                    subTitle = "Σημείο στο χάρτη",
//                    points = PickTargetPointsType.Single(
//                        lat = poi.latLng.latitude,
//                        lon = poi.latLng.longitude
//                    )
//                )
//            )
//        },
//        onMapLongClick = { latLng: LatLng ->
//            setType(
//                DirectionsFeatureItemType.SingleItem(
//                    title = "Σημείο",
//                    id = "",
//                    iconRes = 0,
//                    subTitle = "",
//                    points = PickTargetPointsType.Single(
//                        lat = latLng.latitude,
//                        lon = latLng.longitude
//                    )
//                )
//            )
//        },
//        properties = MapProperties(
//            minZoomPreference = 10f,
//        ),
//        mapColorScheme = ComposeMapColorScheme.FOLLOW_SYSTEM,
//        content = content
//    )
}


@Composable
fun DestinationOverviewMapLayer2(
    givenType: DirectionsFeatureItemType? = null,
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
