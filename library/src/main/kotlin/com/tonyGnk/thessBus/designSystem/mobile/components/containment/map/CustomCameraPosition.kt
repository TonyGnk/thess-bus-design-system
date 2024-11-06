package com.tonyGnk.thessBus.designSystem.mobile.components.containment.map

import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues
import org.maplibre.android.geometry.LatLng

data class CustomCameraPosition(
    val latLng: LatLng,
    val zoom: Double,
    val tilt: Double,
    val bearing: Float,
) {
    companion object {
        val DEFAULT = CustomCameraPosition(
            LatLng(DefaultMapValues.DEFAULT_LAT, DefaultMapValues.DEFAULT_LON),
            DefaultMapValues.DEFAULT_ZOOM,
            DefaultMapValues.DEFAULT_TILT,
            0f
        )
    }
}
