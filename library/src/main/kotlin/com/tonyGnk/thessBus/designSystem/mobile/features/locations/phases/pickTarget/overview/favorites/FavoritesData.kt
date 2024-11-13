package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview.favorites

import androidx.annotation.DrawableRes
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.ColorOptions
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType


data class FavoriteItem(
    val id: Int,
    @DrawableRes val iconRes: Int,
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

fun FavoriteItem.toSingleItem(): DirectionsFeatureItemType.SingleItem? {
    return if (type is FavoriteItemType.Configured) {
        DirectionsFeatureItemType.SingleItem(
            id = "f$id",
            iconRes = iconRes,
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
        iconRes = AppIcon.House.iconRes,
        type = FavoriteItemType.NotConfigured(
            label = "Σπίτι"
        )
    ),
    FavoriteItem(
        id = 2,
        colorOptions = ColorOptions.Secondary,
        iconRes = AppIcon.Work.iconRes,
        type = FavoriteItemType.NotConfigured(
            label = "Δουλειά"
        )
    ),
    FavoriteItem(
        id = 3,
        iconRes = AppIcon.CatBurger.iconRes,
        type = FavoriteItemType.Configured(
            title = "Καφετέρια",
            subTitle = "Αριστοτέλους 1",
            lat = 40.64003,
            lon = 22.94337
        )
    ),
    FavoriteItem(
        id = 4,
        iconRes = AppIcon.GraduationCap.iconRes,
        type = FavoriteItemType.Configured(
            title = "Πανεπιστήμιο",
            subTitle = "Αριστοτέλους 2",
            lat = 40.69003,
            lon = 22.91337
        )
    ),
    FavoriteItem(
        id = 5,
        iconRes = AppIcon.SmallCross.iconRes,
        type = FavoriteItemType.Configured(
            title = "Νοσοκομείο",
            subTitle = "Αριστοτέλους 3",
            lat = 40.64003,
            lon = 22.94337
        )
    ),
    FavoriteItem(
        id = 6,
        iconRes = AppIcon.CatBank.iconRes,
        type = FavoriteItemType.Configured(
            title = "Τράπεζα",
            subTitle = "Αριστοτέλους 4",
            lat = 40.64003,
            lon = 22.94337
        )
    ),
)
