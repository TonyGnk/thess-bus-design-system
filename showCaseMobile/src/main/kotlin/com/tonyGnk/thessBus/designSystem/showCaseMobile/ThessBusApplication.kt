package com.tonyGnk.thessBus.designSystem.showCaseMobile

import android.app.Application
import org.maplibre.android.MapLibre


class ThessBusApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MapLibre.getInstance(this)
    }
}
