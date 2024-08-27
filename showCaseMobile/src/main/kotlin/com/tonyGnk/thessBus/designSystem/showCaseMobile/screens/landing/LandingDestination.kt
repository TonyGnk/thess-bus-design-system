package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R

enum class LandingDestination(
    @StringRes val labelRes: Int, @DrawableRes val iconRes: Int
) {
    Layouts(R.string.landing_destinations_layout, R.drawable.layout_fluid),
    Actions(R.string.landing_destinations_actions, R.drawable.subscription_alt),
    Navigation(R.string.landing_destinations_navigation, R.drawable.navigation),
}
