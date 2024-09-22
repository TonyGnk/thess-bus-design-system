package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface TopDestination {
    @Serializable
    data object Landing : TopDestination

    @Serializable
    data object FeaturesGraph : TopDestination

    @Serializable
    data object ComponentsGraph : TopDestination
}

@Serializable
sealed interface FeatureDestination {
    @Serializable
    data object List : FeatureDestination

    @Serializable
    data object LocationsGraph : FeatureDestination
}

@Serializable
sealed interface ComponentDestination {

    @Serializable
    data object Select : ComponentDestination

    @Serializable
    data object NavigationBar : ComponentDestination
}

@Serializable
sealed interface FeatureLocationsDestination {
    @Serializable
    data object Info : FeatureLocationsDestination

    @Serializable
    data object Card : FeatureLocationsDestination

    @Serializable
    data object PickTarget : FeatureLocationsDestination

}
