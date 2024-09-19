package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface TopDestination {
    @Serializable
    data object Landing : TopDestination

    @Serializable
    data object NavigationItems : TopDestination

    @Serializable
    data object LayoutItems : TopDestination

    @Serializable
    data object DirectionsFeatureList : TopDestination

    @Serializable
    data object ComponentsGraph : TopDestination

}

@Serializable
sealed interface DirectionDestination {
    @Serializable
    data object DirectionsFeature : DirectionDestination

    @Serializable
    data object DirectionsPreviewFeature : DirectionDestination
}

@Serializable
sealed interface ComponentsDestination {
    @Serializable
    data object Select : DirectionDestination

    @Serializable
    data object NavigationBar : DirectionDestination
}
