package com.tonyGnk.thessBus.designSystem.mobile.components.containment.map

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues.DEFAULT_ITEM_ZOOM
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues.DEFAULT_TILT
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues.DEFAULT_ZOOM
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.CameraPosition
import org.ramani.compose.MapLibre
import org.ramani.compose.MapLibreComposable
import org.ramani.compose.MapProperties
import org.ramani.compose.UiSettings
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.InputStream

@Composable
fun MyLibreMap(
    modifier: Modifier = Modifier,
    pickedItem: DirectionsFeatureItemType,
    styleBuilder: Style.Builder,
    content: (@Composable @MapLibreComposable () -> Unit),
) {

//    val context = LocalContext.current
//    val styleBuilder = remember {
//        val styleManager = MapStyleManager(context)
//        val style = when (val result = styleManager.setupStyle()) {
//            is MapStyleManager.StyleSetupResult.Error -> {
//                throw result.exception
//            }
//
//            is MapStyleManager.StyleSetupResult.Success -> result.styleFile
//        }
//        Style.Builder().fromUri(
//            Uri.fromFile(style).toString()
//        )
//    }
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

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }


    val textColor by animateColorAsState(
        targetValue = if (isVisible) Color.Transparent else AppColor.background,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    MapLibre(
        modifier = modifier,
        uiSettings = uiSettings,
        properties = mapProperties,
        styleBuilder = styleBuilder,
        cameraPosition = cameraPosition
    ) {
        content()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(textColor)
    )
}

class MapStyleManager(private val context: Context) {
    sealed class StyleSetupResult {
        data class Success(val styleFile: File) : StyleSetupResult()
        data class Error(val exception: Exception) : StyleSetupResult()
    }

    /**
     * Sets up the style file by copying from assets and replacing the mbtiles URI
     * @return StyleSetupResult indicating success or failure
     */
    fun setupStyle(): StyleSetupResult {
        return try {
            val styleFile = copyAssetToInternal(STYLE_FILENAME)
            val mbtilesFile = copyAssetToInternal(MBTILES_FILENAME)

            updateStyleFileUri(styleFile, mbtilesFile)
            StyleSetupResult.Success(styleFile)
        } catch (e: Exception) {
            StyleSetupResult.Error(e)
        }
    }

    /**
     * Copies an asset file to internal storage
     * @param assetFileName Name of the file in assets
     * @return File object pointing to the copied file
     */
    private fun copyAssetToInternal(assetFileName: String): File {
        context.assets.open(assetFileName).use { input ->
            val outputFile = File(context.filesDir, assetFileName)
            outputFile.outputStream().use { output ->
                input.copyTo(output)
            }
            return outputFile
        }
    }

    /**
     * Updates the style file with the correct mbtiles URI
     * @param styleFile The style file to update
     * @param mbtilesFile The mbtiles file reference to insert
     */
    private fun updateStyleFileUri(styleFile: File, mbtilesFile: File) {
        val styleContent = styleFile.readText()
        val updatedContent = styleContent.replace(
            FILE_URI_PLACEHOLDER,
            "mbtiles:///${mbtilesFile.absolutePath}"
        )
        Log.d("MapStyleManager", "mbtiles:///${mbtilesFile.absolutePath}")
        styleFile.writeText(updatedContent)
    }

    companion object {
        private const val STYLE_FILENAME = "goodStyle.json"
        private const val MBTILES_FILENAME = "gr.mbtiles"
        private const val FILE_URI_PLACEHOLDER = "___FILE_URI___"
    }
}


private fun getLatLngForType(givenType: DirectionsFeatureItemType): LatLng {
    return when (givenType) {
        is DirectionsFeatureItemType.JustMap,
        is DirectionsFeatureItemType.MultipleItems -> LatLng(
            DefaultMapValues.DEFAULT_LAT, DefaultMapValues.DEFAULT_LON
        )

        is DirectionsFeatureItemType.SingleItem -> LatLng(givenType.lat, givenType.lon)
    }
}
