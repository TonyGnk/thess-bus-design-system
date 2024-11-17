package com.tonyGnk.thessBus.designSystem.mobile.components.containment.map

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues.DEFAULT_ITEM_ZOOM
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues.DEFAULT_TILT
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues.DEFAULT_ZOOM
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.CameraPosition
import org.ramani.compose.MapLibre
import org.ramani.compose.MapProperties
import org.ramani.compose.UiSettings
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.InputStream

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

private fun copyStreamToFile(inputStream: InputStream, file: File) {
    try {
        inputStream.use { input ->
            file.outputStream().use { output ->
                val bytesCount = input.copyTo(output)
            }
        }
    } catch (e: Exception) {
        throw e
    }
}

private fun getFileFromAssets(context: Context, fileName: String): File {
    try {
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val file = File(context.filesDir, fileName)
        copyStreamToFile(inputStream, file)
        return file
    } catch (e: Exception) {
        throw e
    }
}

private fun setupAndGetStyle(context: Context): File {
    val styleJsonInputStream = context.assets.open("goodStyle.json")
    val dir = File(context.filesDir.absolutePath)
    val styleFile = File(dir, "goodStyle.json")
    copyStreamToFile(styleJsonInputStream, styleFile)

    val mbtilesFile: File = getFileFromAssets(context, "gr.mbtiles")

    val styleContent = styleFile.inputStream().bufferedReader().use { it.readText() }

    val newFileStr = styleContent.replace(
        "___FILE_URI___",
        "mbtiles:///${mbtilesFile.absolutePath}"
    )

    val gpxWriter = FileWriter(styleFile)
    BufferedWriter(gpxWriter).use { out ->
        out.write(newFileStr)
    }
    return styleFile
}

@Composable
fun MyLibreMap(
    modifier: Modifier = Modifier,
    pickedItem: DirectionsFeatureItemType,
) {
    val context = LocalContext.current

    val styleBuilder = remember {
        val style = setupAndGetStyle(context)
        Style.Builder().fromUri(
            Uri.fromFile(style).toString()
        )
    }
    val uiSettings = rememberSaveable {
        UiSettings()
    }
    val mapProperties = rememberSaveable {
        MapProperties()
    }

    val cameraPosition = rememberSaveable {
        CameraPosition(
            target = getLatLngForType(pickedItem),
            zoom = if (pickedItem is DirectionsFeatureItemType.SingleItem) DEFAULT_ITEM_ZOOM else DEFAULT_ZOOM,
            tilt = DEFAULT_TILT,
        )
    }

    MapLibre(
        modifier = modifier,
        uiSettings = uiSettings,
        properties = mapProperties,
        styleBuilder = styleBuilder,
        cameraPosition = cameraPosition
    ) {

    }
}

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
private fun getLatLngForType(givenType: DirectionsFeatureItemType): LatLng {
    return when (givenType) {
        is DirectionsFeatureItemType.JustMap,
        is DirectionsFeatureItemType.MultipleItems -> LatLng(
            DefaultMapValues.DEFAULT_LAT, DefaultMapValues.DEFAULT_LON
        )

        is DirectionsFeatureItemType.SingleItem -> LatLng(givenType.lat, givenType.lon)
    }
}

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
