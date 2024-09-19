package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation

import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionPhases
import kotlinx.serialization.Serializable

@Serializable
sealed interface TopDestination {
    @Serializable
    data object Landing : TopDestination

    @Serializable
    data object FeatureList : TopDestination

    @Serializable
    data object DirectionsFeatureGraph : TopDestination

    @Serializable
    data object ComponentsGraph : TopDestination
}

@Serializable
sealed interface DirectionDestination {
    @Serializable
    data object Pager : DirectionDestination

    @Serializable
    data object Preview : DirectionDestination
}

@Serializable
sealed interface ComponentsDestination {
    @Serializable
    data object Select : DirectionDestination

    @Serializable
    data object NavigationBar : DirectionDestination
}
