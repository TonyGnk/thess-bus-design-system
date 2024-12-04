package com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.favorites

import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.ColorOptions
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.data.DirectionsFeatureItemType


data class FavoriteItem(
    val id: Int,
    val appIcon: AppIcon,
    val colorOptions: ColorOptions? = null,
    val type: FavoriteItemType,
)

sealed interface FavoriteItemType {
    data class Configured(
        val title: String,
        val subTitle: String,
        val lat: Double,
        val lon: Double,
    ) : FavoriteItemType

    data class NotConfigured(
        val label: String,
    ) : FavoriteItemType
}

fun FavoriteItem.toSingleItem(): DirectionsFeatureItemType.Point? {
    return if (type is FavoriteItemType.Configured) {
        DirectionsFeatureItemType.Point(
            id = "f$id",
            iconRes = appIcon.iconRes,
            title = type.title,
            subTitle = type.subTitle,
            lat = type.lat,
            lon = type.lon
        )
    } else null
}

fun deleteFakeFavorite(id: Int) {
    FakeFavoritesItems.remove(FakeFavoritesItems.find { it.id == id })
}

val FakeFavoritesItems = mutableListOf(
    FavoriteItem(
        id = 1,
        colorOptions = ColorOptions.Primary,
        appIcon = AppIcon.House,
        type = FavoriteItemType.NotConfigured(
            label = "Σπίτι"
        )
    ),
    FavoriteItem(
        id = 2,
        colorOptions = ColorOptions.Secondary,
        appIcon = AppIcon.Work,
        type = FavoriteItemType.NotConfigured(
            label = "Δουλειά"
        )
    ),
    FavoriteItem(
        id = 3,
        appIcon = AppIcon.CatBurger,
        type = FavoriteItemType.Configured(
            title = "Καφετέρια",
            subTitle = "Αριστοτέλους 1",
            lat = 40.64003,
            lon = 22.94337
        )
    ),
    FavoriteItem(
        id = 4,
        appIcon = AppIcon.GraduationCap,
        type = FavoriteItemType.Configured(
            title = "Πανεπιστήμιο",
            subTitle = "Αριστοτέλους 2",
            lat = 40.69003,
            lon = 22.91337
        )
    ),
    FavoriteItem(
        id = 5,
        appIcon = AppIcon.SmallCross,
        type = FavoriteItemType.Configured(
            title = "Νοσοκομείο",
            subTitle = "Αριστοτέλους 3",
            lat = 40.64003,
            lon = 22.94337
        )
    ),
    FavoriteItem(
        id = 6,
        appIcon = AppIcon.CatBank,
        type = FavoriteItemType.Configured(
            title = "Τράπεζα",
            subTitle = "Αριστοτέλους 4",
            lat = 40.64003,
            lon = 22.94337
        )
    ),
)
