package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R

enum class LandingDestination(
    @StringRes val labelRes: Int, @DrawableRes val iconRes: Int
) {
    Features(R.string.landing_destinations_features, R.drawable.objects_column),
    Components(R.string.landing_destinations_components, R.drawable.add_circle),
}

enum class LayoutDestination(
    @StringRes val labelRes: Int
) {
    NavCard(R.string.Layouts_directions),
}

enum class Components(
    @StringRes val labelRes: Int
) {
    NavigationBar(R.string.components_navigation_bar),
}
