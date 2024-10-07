package com.tonyGnk.thessBus.designSystem.mobile.components.containment.map

import com.google.android.gms.maps.model.LatLng
import com.tonyGnk.thessBus.designSystem.mobile.utils.map.DefaultMapValues

data class CustomCameraPosition(
    val latLng: LatLng,
    val zoom: Float,
    val tilt: Float,
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
