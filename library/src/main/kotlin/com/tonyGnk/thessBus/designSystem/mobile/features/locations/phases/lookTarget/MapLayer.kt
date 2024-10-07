package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberMarkerState
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.map.MyGoogleMap
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.map.bitmapDescriptorFromVector
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


@SuppressLint("UnrememberedMutableState")
@Composable
fun DestinationOverviewMapLayer(
    items: DirectionsLookTargetItems,
) {
    val context = LocalContext.current

    MyGoogleMap(
        setTypeOnMap = items.onPickItem,
        onCameraPositionChanged = items.onCameraPositionChanged,
        givenType = items.givenType,
    ) {


        when (items.givenType) {
            is DirectionsFeatureItemType.JustMap -> {}
            is DirectionsFeatureItemType.MultipleItems -> {}
            is DirectionsFeatureItemType.SingleItem -> {
                val markerState = rememberMarkerState(
                    position = LatLng(
                        items.givenType.lat, items.givenType.lon
                    )
                )
                val previousLat = remember { mutableDoubleStateOf(0.0) }
                val previousLng = remember { mutableDoubleStateOf(0.0) }

                LaunchedEffect(items.givenType.lat, items.givenType.lon) {
                    //if new is different then animate
                    if (
                        previousLat.doubleValue != items.givenType.lat &&
                        previousLng.doubleValue != items.givenType.lon
                    ) {

                        animateMarker(
                            markerState = MarkerState(
                                LatLng(previousLat.doubleValue, previousLng.doubleValue)
                            ),
                            targetPosition = LatLng(items.givenType.lat, items.givenType.lon),
                        ) { newPosition ->
                            markerState.position = newPosition  // Update marker position
                        }
                        previousLat.doubleValue = items.givenType.lat
                        previousLng.doubleValue = items.givenType.lon
                    }
                }



                Marker(
                    state = markerState
//                    MarkerState(
//                        position = LatLng(
//                            //items.givenType.lat, items.givenType.lon
//                        )
//                    ),
                    ,
                    draggable = false,
                    flat = false,
                    zIndex = 0f,
                    icon = bitmapDescriptorFromVector(
                        context = context,
                        vectorResId = AppIcon.locationSolid,
                        color = AppColor.red.toArgb()
                    )
                )
            }
        }
    }
}

suspend fun animateMarker(
    markerState: MarkerState,
    targetPosition: LatLng,
    duration: Long = 300L,  // Duration in milliseconds
    onUpdate: (LatLng) -> Unit  // Callback to update marker's position
) {
    val startPosition = markerState.position
    val startLat = startPosition.latitude
    val startLng = startPosition.longitude
    val endLat = targetPosition.latitude
    val endLng = targetPosition.longitude

    val latDifference = endLat - startLat
    val lngDifference = endLng - startLng

    val steps = 360  // The number of steps for smoothness
    val delayTime = duration / steps

    for (i in 0..steps) {
        val fraction = i / steps.toFloat()
        val newLat = startLat + (latDifference * fraction)
        val newLng = startLng + (lngDifference * fraction)
        val newPosition = LatLng(newLat, newLng)

        // Update marker's position in each step
        withContext(Dispatchers.Main) {
            onUpdate(newPosition)
        }
        delay(delayTime)
    }
}
