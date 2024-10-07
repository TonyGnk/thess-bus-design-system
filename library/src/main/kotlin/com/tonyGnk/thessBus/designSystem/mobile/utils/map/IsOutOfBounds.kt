package com.tonyGnk.thessBus.designSystem.mobile.utils.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

//fun isOutOfBounds(
//    camera: CameraPositionState,
//    latLng: LatLng,
//): Boolean {
//
//    val projection = camera.projection
//    if (projection != null) {
//        val visibleWindow = projection.visibleRegion.latLngBounds
//        val a1 = visibleWindow.southwest
//        val a2 = visibleWindow.northeast
//        val a3 = projection.visibleRegion.farLeft
//        val a4 = projection.visibleRegion.farRight
//        val center = visibleWindow.center
//
//        //If the new item is closer to one of the corners of the visible map than the center, then animate the camera to that position
//
//
////        val latDiff = abs(camera.position.target.latitude - latLng.latitude)
////        val lonDiff = abs(camera.position.target.longitude - latLng.longitude)
////        return latDiff > threshold || lonDiff > threshold
//    } else return false
//}


//When zoom 12 then is 0.01

fun isOutOfBounds(
    camera: CameraPositionState,
    latLng: LatLng,
): Boolean {
    val projection = camera.projection ?: return false

    val visibleRegion = projection.visibleRegion
    val bounds = visibleRegion.latLngBounds
    val center = bounds.center

    // Calculate distances to corners and center
    val distanceToCenter = distance(latLng, center)
    val distanceToSouthWest = distance(latLng, bounds.southwest)
    val distanceToNorthEast = distance(latLng, bounds.northeast)
    val distanceToNorthWest = distance(latLng, visibleRegion.farLeft)
    val distanceToSouthEast = distance(latLng, visibleRegion.farRight)

    // Find the minimum distance to any corner
    val minCornerDistance = minOf(
        distanceToSouthWest,
        distanceToNorthEast,
        distanceToNorthWest,
        distanceToSouthEast
    )

    // If the point is closer to any corner than to the center, it's considered "out of bounds"
    return minCornerDistance < distanceToCenter
}

// Helper function to calculate distance between two LatLng points
private fun distance(point1: LatLng, point2: LatLng): Double {
    val lat1 = Math.toRadians(point1.latitude)
    val lon1 = Math.toRadians(point1.longitude)
    val lat2 = Math.toRadians(point2.latitude)
    val lon2 = Math.toRadians(point2.longitude)

    val dlon = lon2 - lon1
    val dlat = lat2 - lat1

    val a = sin(dlat / 2).pow(2) + cos(lat1) * cos(lat2) * sin(dlon / 2).pow(2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    // Radius of Earth in kilometers
    val radius = 6371.0
    return radius * c
}
