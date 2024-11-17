package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.map.MyLibreMap


@Composable
fun DestinationOverviewMapLayer(
    items: LocationsLookTargetItems,
) {


//    val cameraPosition = rememberSaveable {
//        mutableStateOf(
//            CameraPosition(
//                target = LatLng(40.631619, 22.953482), zoom = 13.0, tilt = 15.0
//            )
//        )
//    }
    MyLibreMap(
        modifier = Modifier.fillMaxSize(),
        pickedItem = items.pickedItem
    )
//    MapLibre(
//        modifier = Modifier.fillMaxSize(),
//        uiSettings = UiSettings(),
//        properties = MapProperties(),
//        styleBuilder = Style.Builder().fromUri(
//            Uri.fromFile(mapStyle).toString()
//        ),
//        cameraPosition = when (val pickedItem = items.pickedItem) {
//            is DirectionsFeatureItemType.JustMap -> cameraPosition.value
//            is DirectionsFeatureItemType.MultipleItems -> cameraPosition.value
//            is DirectionsFeatureItemType.SingleItem -> CameraPosition(
//                target = LatLng(pickedItem.lat, pickedItem.lon),
//                zoom = 13.0,
//                tilt = 15.0
//            )
//        },
//    ) {
//        // Create a handle for each vertex (those are blue circles)
//
//    }


    // MyGoogleMap(
//        setTypeOnMap = items.onPickItem,
//        onCameraPositionChanged = items.onCameraPositionChanged,
//        givenType = items.pickedItem,
//    ) {
//        when (items.pickedItem) {
//            is DirectionsFeatureItemType.JustMap -> {}
//            is DirectionsFeatureItemType.MultipleItems -> {}
//            is DirectionsFeatureItemType.SingleItem -> {
//                val markerState = rememberMarkerState(
//                    position = LatLng(
//                        items.pickedItem.lat, items.pickedItem.lon
//                    )
//                )
//                val previousLat = remember { mutableDoubleStateOf(markerState.position.latitude) }
//                val previousLng = remember { mutableDoubleStateOf(markerState.position.longitude) }
//
//                LaunchedEffect(items.pickedItem.lat, items.pickedItem.lon) {
//                    //if new is different then animate
//                    if (
//                        previousLat.doubleValue != items.pickedItem.lat &&
//                        previousLng.doubleValue != items.pickedItem.lon
//                    ) {
//                        animateMarker(
//                            markerState = MarkerState(
//                                LatLng(previousLat.doubleValue, previousLng.doubleValue)
//                            ),
//                            targetPosition = LatLng(items.pickedItem.lat, items.pickedItem.lon),
//                        ) { newPosition ->
//                            markerState.position = newPosition  // Update marker position
//                        }
//                        previousLat.doubleValue = items.pickedItem.lat
//                        previousLng.doubleValue = items.pickedItem.lon
//                    }
//                }
//
//
//                Marker(
//                    state = markerState,
//                    draggable = false,
//                    flat = false,
//                    zIndex = 0f,
//                    icon = bitmapDescriptorFromVector(
//                        context = context,
//                        vectorResId = AppIcon.locationSolid,
//                        color = AppColor.red.toArgb(),
//                    )
//                )
//            }
//        }
//    }
}

//suspend fun animateMarker(
//    markerState: MarkerState,
//    targetPosition: LatLng,
//    duration: Long = 400L,  // Duration in milliseconds
//    onUpdate: (LatLng) -> Unit  // Callback to update marker's position
//) {
//    val startPosition = markerState.position
//    val startLat = startPosition.latitude
//    val startLng = startPosition.longitude
//    val endLat = targetPosition.latitude
//    val endLng = targetPosition.longitude
//
//    val latDifference = endLat - startLat
//    val lngDifference = endLng - startLng
//
//    val steps = 1440  // The number of steps for smoothness
//    val delayTime = duration / steps
//
//    for (i in 0..steps) {
//        val fraction = i / steps.toFloat()
//        val newLat = startLat + (latDifference * fraction)
//        val newLng = startLng + (lngDifference * fraction)
//        val newPosition = LatLng(newLat, newLng)
//
//        // Update marker's position in each step
//        withContext(Dispatchers.Main) {
//            onUpdate(newPosition)
//        }
//        delay(delayTime)
//    }
//}
