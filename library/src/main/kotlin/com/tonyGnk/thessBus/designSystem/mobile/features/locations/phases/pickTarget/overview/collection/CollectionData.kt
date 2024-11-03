package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview.collection

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType

enum class ColorToPick {
    Primary, Secondary, Green, Blue, Orange, Red
}

val colorsMapSaves: Map<ColorToPick, Color>
    @Composable
    get() = ColorToPick.entries.associateWith {
        when (it) {
            ColorToPick.Primary -> AppColor.primary
            ColorToPick.Secondary -> AppColor.secondary
            ColorToPick.Green -> AppColor.green
            ColorToPick.Blue -> AppColor.blue
            ColorToPick.Orange -> AppColor.orange
            ColorToPick.Red -> AppColor.red
        }
    }

data class FavoriteItem(
    val id: Int,
    @DrawableRes val iconRes: Int,
    val color: ColorToPick? = null,
    val type: FavoriteItemType,
)

sealed interface FavoriteItemType {
    data class Configured(
        val title: String,
        val subTitle: String,
        val lat: Double,
        val lon: Double,
        val isTheSelected: Boolean = false,
        val updateSelectedFavoriteItemId: (Int?) -> Unit = {},
    ) : FavoriteItemType

    data class NotConfigured(
        val label: String,
    ) : FavoriteItemType

    data object Add : FavoriteItemType
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
        color = ColorToPick.Primary,
        iconRes = AppIcon.house,
        type = FavoriteItemType.NotConfigured(
            label = "Σπίτι"
        )
    ),
    FavoriteItem(
        id = 2,
        color = ColorToPick.Secondary,
        iconRes = AppIcon.work,
        type = FavoriteItemType.NotConfigured(
            label = "Δουλειά"
        )
    ),
    FavoriteItem(
        id = 3,
        iconRes = AppIcon.catBurger,
        type = FavoriteItemType.Configured(
            title = "Καφετέρια",
            subTitle = "Αριστοτέλους 1",
            lat = 40.64003,
            lon = 22.94337
        )
    ),
    FavoriteItem(
        id = 4,
        iconRes = AppIcon.graduation_cap,
        type = FavoriteItemType.Configured(
            title = "Πανεπιστήμιο",
            subTitle = "Αριστοτέλους 2",
            lat = 40.69003,
            lon = 22.91337
        )
    ),
    FavoriteItem(
        id = 5,
        iconRes = AppIcon.smallCross,
        type = FavoriteItemType.Configured(
            title = "Νοσοκομείο",
            subTitle = "Αριστοτέλους 3",
            lat = 40.64003,
            lon = 22.94337
        )
    ),
    FavoriteItem(
        id = 6,
        iconRes = AppIcon.catBank,
        type = FavoriteItemType.Configured(
            title = "Τράπεζα",
            subTitle = "Αριστοτέλους 4",
            lat = 40.64003,
            lon = 22.94337
        )
    ),
)
