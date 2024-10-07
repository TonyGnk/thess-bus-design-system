package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.TextFade
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties


@Composable
fun PickTargetOverviewCollection(
    modifier: Modifier = Modifier,
    onFavoriteNotConfiguredClick: () -> Unit,
    onFavoriteClick: (DirectionsFeatureItemType.SingleItem?) -> Unit,
    onAddCollectionClick: () -> Unit
) {
    LazyRow(
        modifier = modifier.padding(vertical = LocationsProperties.IN_PADDING.div(2).dp),
        contentPadding = PaddingValues(horizontal = LocationsProperties.IN_PADDING.div(2).dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(
            LocationsProperties.ARRANGEMENT.div(4).dp
        )
    ) {
        items(
            items = favorites, key = { it.id }
        ) {
            FavoriteItemColumn(
                item = it,
                onClick = {
                    when (it.type) {
                        is FavoriteItemType.Configured -> {
                            onFavoriteClick(it.toSingleItem())
                        }

                        is FavoriteItemType.NotConfigured -> {
                            onFavoriteNotConfiguredClick()
                        }

                        is FavoriteItemType.Add -> {
                            onAddCollectionClick()
                        }
                    }
                },
            )
        }
        item {
            FavoriteItemColumn(
                item = FavoriteItem(
                    id = 0,
                    iconRes = AppIcon.add,
                    type = FavoriteItemType.Add
                ),
                onClick = onAddCollectionClick
            )
        }
    }
}

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
        val lon: Double
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


val favorites = listOf(
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
)


@Composable
private fun FavoriteItemColumn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    item: FavoriteItem
) {
    val color: Color? = colorsMapSaves[item.color]
    val iconSize = 33.dp
    val width = iconSize + 24.dp + 15.dp
    val textModifier = remember {
        Modifier.width(width)
    }

    SurfaceWithShadows(
        shape = AppShape.round15,
        color = AppColor.surfaceLowest,
        shadowElevation = 0,
        onClick = onClick
    ) {
        Column(
            modifier = modifier.padding(LocationsProperties.IN_PADDING.div(2).dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (color == null) SurfaceWithShadows(
                shape = AppShape.round20,
                shadowElevation = 0
            ) {
                IconButton(
                    iconRes = item.iconRes,
                    color = AppColor.surface,
                    contentColor = AppColor.primary,
                    modifier = Modifier.size(iconSize)
                )
            } else SurfaceWithShadows(
                shape = AppShape.round20,
                shadowElevation = 10
            ) {
                IconButton(
                    iconRes = item.iconRes,
                    color = color,
                    contentColor = AppColor.surfaceLowest,
                    modifier = Modifier.size(iconSize)
                )
            }
            Spacer(modifier = Modifier.height(9.dp))
            when (item.type) {
                is FavoriteItemType.Add -> TextLabelLarge(
                    text = "Προσθήκη",
                    textTargetWidth = width,
                    modifier = textModifier
                )

                is FavoriteItemType.Configured -> {
                    TextLabelLarge(
                        text = item.type.title,
                        textTargetWidth = width,
                        modifier = textModifier
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    TextLabelSmall(
                        text = item.type.subTitle,
                        modifier = textModifier,
                        textTargetWidth = width,
                    )
                }

                is FavoriteItemType.NotConfigured -> {
                    TextLabelLarge(
                        text = item.type.label,
                        textTargetWidth = width,
                        modifier = textModifier
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    TextLabelSmall(
                        text = "Προσθήκη",
                        modifier = textModifier,
                        textTargetWidth = width,
                    )
                }
            }
        }
    }
}

@Composable
private fun TextLabelLarge(
    modifier: Modifier = Modifier,
    textTargetWidth: Dp,
    text: String
) {
    TextFade(
        text = text,
        textAlign = TextAlign.Center,
        style = AppTypo.bodySmall,
        modifier = modifier,
        textTargetWidth = textTargetWidth,
        backgroundColor = AppColor.surfaceLowest
    )
}

@Composable
private fun TextLabelSmall(
    modifier: Modifier = Modifier,
    textTargetWidth: Dp,
    text: String
) {
    TextFade(
        text = text,
        textAlign = TextAlign.Center,
        style = AppTypo.bodySmall.copy(
            color = AppColor.primary,
            fontSize = AppTypo.bodySmall.fontSize.div(1.25f)
        ),
        modifier = modifier,
        textTargetWidth = textTargetWidth,
        backgroundColor = AppColor.surfaceLowest
    )
}
