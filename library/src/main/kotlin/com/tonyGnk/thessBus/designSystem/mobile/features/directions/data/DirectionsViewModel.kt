package com.tonyGnk.thessBus.designSystem.mobile.features.directions.data

import org.maplibre.android.geometry.LatLng

interface DirectionsViewModel {

    fun onMapLongClick(latLng: LatLng)

    fun clearPickedItem()
}
