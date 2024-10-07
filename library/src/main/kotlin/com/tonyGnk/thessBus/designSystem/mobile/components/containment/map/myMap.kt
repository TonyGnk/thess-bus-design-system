package com.tonyGnk.thessBus.designSystem.mobile.components.containment.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.PointOfInterest
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.isOutOfBounds

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int,
    color: Int? = null,
    scaleFactor: Float = 1.5f
): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        val drawable = if (color != null) {
            val wrappedDrawable = DrawableCompat.wrap(this).mutate()
            DrawableCompat.setTint(wrappedDrawable, color)
            wrappedDrawable
        } else {
            this
        }

        val scaledWidth = (intrinsicWidth * scaleFactor).toInt()
        val scaledHeight = (intrinsicHeight * scaleFactor).toInt()

        drawable.setBounds(0, 0, scaledWidth, scaledHeight)
        val bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888)
        drawable.draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}

@Composable
fun MyGoogleMap(
    modifier: Modifier = Modifier,
    givenType: DirectionsFeatureItemType,
    onCameraPositionChanged: (CustomCameraPosition) -> Unit,
    setTypeOnMap: (DirectionsFeatureItemType.SingleItem) -> Unit,
    content: @Composable @GoogleMapComposable () -> Unit = {},
) {
    Log.d("MyGoogleMap", "Item type: $givenType")

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = when (givenType) {
            is DirectionsFeatureItemType.JustMap -> CameraPosition(
                LatLng(
                    DefaultMapValues.DEFAULT_LAT, DefaultMapValues.DEFAULT_LON
                ),
                DefaultMapValues.DEFAULT_ZOOM,
                DefaultMapValues.DEFAULT_TILT,
                0f
            )

            is DirectionsFeatureItemType.MultipleItems -> CameraPosition(
                LatLng(
                    DefaultMapValues.DEFAULT_LAT, DefaultMapValues.DEFAULT_LON
                ),
                DefaultMapValues.DEFAULT_ZOOM,
                DefaultMapValues.DEFAULT_TILT,
                0f
            )

            is DirectionsFeatureItemType.SingleItem -> CameraPosition(
                LatLng(
                    givenType.lat, givenType.lon
                ),
                DefaultMapValues.MARKET_ZOOM,
                DefaultMapValues.DEFAULT_TILT,
                0f
            )
        }
    }



    LaunchedEffect(givenType) {
        val sameZoom = cameraPositionState.position.zoom
        val sameTilt = cameraPositionState.position.tilt
        val sameBearing = cameraPositionState.position.bearing

        val latLng = when (givenType) {
            is DirectionsFeatureItemType.JustMap -> LatLng(
                DefaultMapValues.DEFAULT_LAT,
                DefaultMapValues.DEFAULT_LON
            )

            is DirectionsFeatureItemType.MultipleItems -> LatLng(
                DefaultMapValues.DEFAULT_LAT,
                DefaultMapValues.DEFAULT_LON
            )

            is DirectionsFeatureItemType.SingleItem -> LatLng(
                givenType.lat,
                givenType.lon
            )
        }


        val newPositionIsDifferent =
            cameraPositionState.position.target.latitude != latLng.latitude ||
                    cameraPositionState.position.target.longitude != latLng.longitude

        if (
            newPositionIsDifferent && isOutOfBounds(
                cameraPositionState, latLng
            )
        ) cameraPositionState.animate(
            update = CameraUpdateFactory.newCameraPosition(
                when (givenType) {
                    is DirectionsFeatureItemType.JustMap -> CameraPosition(
                        LatLng(
                            DefaultMapValues.DEFAULT_LAT, DefaultMapValues.DEFAULT_LON
                        ),
                        sameZoom,
                        sameTilt,
                        sameBearing
                    )

                    is DirectionsFeatureItemType.MultipleItems -> CameraPosition(
                        LatLng(
                            DefaultMapValues.DEFAULT_LAT, DefaultMapValues.DEFAULT_LON
                        ),
                        DefaultMapValues.DEFAULT_ZOOM,
                        sameTilt,
                        sameBearing
                    )

                    is DirectionsFeatureItemType.SingleItem -> CameraPosition(
                        LatLng(
                            givenType.lat, givenType.lon
                        ),
                        sameZoom,
                        sameTilt,
                        sameBearing
                    )
                }
            ),
            durationMs = 700
        )
    }


    LaunchedEffect(cameraPositionState.position) {
        val newCamera = CustomCameraPosition(
            cameraPositionState.position.target,
            cameraPositionState.position.zoom,
            cameraPositionState.position.tilt,
            cameraPositionState.position.bearing
        )
        onCameraPositionChanged(newCamera)
    }


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
            mapStyleOptions = MapStyleOptions(MAP_STYLE_JSON),
        )
    }

    val onPOIClick = remember {
        { poi: PointOfInterest ->
            setTypeOnMap(
                DirectionsFeatureItemType.SingleItem(
                    title = poi.name,
                    id = poi.placeId,
                    iconRes = 0,
                    subTitle = "Σημείο στο χάρτη",
                    lat = poi.latLng.latitude,
                    lon = poi.latLng.longitude
                )
            )
        }
    }

    val onMapLongClick = remember {
        { latLng: LatLng ->
            setTypeOnMap(
                DirectionsFeatureItemType.SingleItem(
                    title = "Σημείο",
                    id = "",
                    iconRes = 0,
                    subTitle = "",
                    lat = latLng.latitude,
                    lon = latLng.longitude
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
        mapColorScheme = if (isSystemInDarkTheme()) ComposeMapColorScheme.DARK else ComposeMapColorScheme.LIGHT,
        content = content
    )
}
