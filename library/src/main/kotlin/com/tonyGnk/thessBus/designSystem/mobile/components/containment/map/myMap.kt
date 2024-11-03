package com.tonyGnk.thessBus.designSystem.mobile.components.containment.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues

//
//fun bitmapDescriptorFromVector(
//    context: Context,
//    vectorResId: Int,
//    color: Int? = null,
//    scaleFactor: Float = 1.5f
//): BitmapDescriptor? {
//    return ContextCompat.getDrawable(context, vectorResId)?.run {
//        val drawable = if (color != null) {
//            val wrappedDrawable = DrawableCompat.wrap(this).mutate()
//            DrawableCompat.setTint(wrappedDrawable, color)
//            wrappedDrawable
//        } else {
//            this
//        }
//
//        val scaledWidth = (intrinsicWidth * scaleFactor).toInt()
//        val scaledHeight = (intrinsicHeight * scaleFactor).toInt()
//
//        drawable.setBounds(0, 0, scaledWidth, scaledHeight)
//        val bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888)
//        drawable.draw(Canvas(bitmap))
//        BitmapDescriptorFactory.fromBitmap(bitmap)
//    }
//}
//
//@Composable
//fun MyGoogleMap(
//    modifier: Modifier = Modifier,
//    givenType: DirectionsFeatureItemType,
//    onCameraPositionChanged: (CustomCameraPosition) -> Unit,
//    setTypeOnMap: (DirectionsFeatureItemType.SingleItem) -> Unit,
//    content: @Composable @GoogleMapComposable () -> Unit = {},
//) {
//    val latLng = getLatLngForType(givenType)
//
//    // Extract default zoom and tilt values
//    val defaultZoom = getDefaultZoomForType(givenType)
//    val defaultTilt = DefaultMapValues.DEFAULT_TILT
//
//    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
//        position = CameraPosition(
//            latLng,
//            defaultZoom,
//            defaultTilt,
//            0f
//        )
//    }
//
//    LaunchedEffect(givenType) {
//        val currentPosition = cameraPositionState.position
//        val latLngChanged = latLngHasChanged(currentPosition.target, latLng)
//        val animateOnly = latLngChanged && isOutOfBounds(
//            camera = cameraPositionState,
//            latLng = latLng
//        ) && givenType is DirectionsFeatureItemType.SingleItem
//
//        if (animateOnly) {
//            cameraPositionState.animate(
//                update = CameraUpdateFactory.newCameraPosition(
//                    CameraPosition(
//                        latLng,
//                        currentPosition.zoom,
//                        currentPosition.tilt,
//                        currentPosition.bearing
//                    )
//                ),
//                durationMs = 700
//            )
//        }
//    }
//
//
//    LaunchedEffect(cameraPositionState.position) {
//        val newCamera = CustomCameraPosition(
//            cameraPositionState.position.target,
//            cameraPositionState.position.zoom,
//            cameraPositionState.position.tilt,
//            cameraPositionState.position.bearing
//        )
//        onCameraPositionChanged(newCamera)
//    }
//
//
//    val mapUiSettings = remember {
//        MapUiSettings(
//            myLocationButtonEnabled = false,
//            mapToolbarEnabled = false,
//            compassEnabled = false,
//            zoomControlsEnabled = false,
//        )
//    }
//
//    val mapProperties = remember {
//        MapProperties(
//            mapStyleOptions = MapStyleOptions(MAP_STYLE_JSON),
//        )
//    }
//
//    val onPOIClick = remember {
//        { poi: PointOfInterest ->
//            setTypeOnMap(
//                DirectionsFeatureItemType.SingleItem(
//                    title = poi.name,
//                    id = poi.placeId,
//                    iconRes = 0,
//                    subTitle = "Σημείο στο χάρτη",
//                    lat = poi.latLng.latitude,
//                    lon = poi.latLng.longitude
//                )
//            )
//        }
//    }
//
//    val onMapLongClick = remember {
//        { latLng: LatLng ->
//            setTypeOnMap(
//                DirectionsFeatureItemType.SingleItem(
//                    title = "Σημείο",
//                    id = "",
//                    iconRes = 0,
//                    subTitle = "",
//                    lat = latLng.latitude,
//                    lon = latLng.longitude
//                )
//            )
//        }
//    }
//
//    GoogleMap(
//        modifier = modifier,
//        cameraPositionState = cameraPositionState,
//        uiSettings = mapUiSettings,
//        onPOIClick = onPOIClick,
//        onMapLongClick = onMapLongClick,
//        properties = mapProperties,
//        mapColorScheme = if (isSystemInDarkTheme()) ComposeMapColorScheme.DARK else ComposeMapColorScheme.LIGHT,
//        content = content
//    )
//}
//
//
//private fun getLatLngForType(givenType: DirectionsFeatureItemType): LatLng {
//    return when (givenType) {
//        is DirectionsFeatureItemType.JustMap,
//        is DirectionsFeatureItemType.MultipleItems -> LatLng(
//            DefaultMapValues.DEFAULT_LAT, DefaultMapValues.DEFAULT_LON
//        )
//
//        is DirectionsFeatureItemType.SingleItem -> LatLng(givenType.lat, givenType.lon)
//    }
//}

//private fun getDefaultZoomForType(givenType: DirectionsFeatureItemType): Float {
//    return when (givenType) {
//        is DirectionsFeatureItemType.JustMap,
//        is DirectionsFeatureItemType.MultipleItems -> DefaultMapValues.DEFAULT_ZOOM
//
//        is DirectionsFeatureItemType.SingleItem -> DefaultMapValues.MARKET_ZOOM
//    }
//}
//
//private fun latLngHasChanged(currentLatLng: LatLng, newLatLng: LatLng): Boolean {
//    return currentLatLng.latitude != newLatLng.latitude || currentLatLng.longitude != newLatLng.longitude
//}
