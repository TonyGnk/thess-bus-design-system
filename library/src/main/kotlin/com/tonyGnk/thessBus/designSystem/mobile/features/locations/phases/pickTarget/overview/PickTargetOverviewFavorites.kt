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
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetPointsType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties


@Composable
fun PickTargetOverviewFavorites(
    modifier: Modifier = Modifier,
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
                iconColor = it.color,
                iconRes = it.iconRes,
                firstRowLabel = it.label,
                type = if (it.name != null) FavoriteItemColumnItem.isConfigured(
                    secondRow = it.name,
                    onClick = { }
                ) else FavoriteItemColumnItem.isNotConfigured(
                    onConfigClick = { }
                )
            )
        }
        item {
            FavoriteItemColumn(
                firstRowLabel = "Προσθήκη",
                iconRes = AppIcon.add,
                type = FavoriteItemColumnItem.Add(
                    onAddCollectionClick = onAddCollectionClick
                )
            )
        }
    }
}

enum class ColorToPick {
    Primary, Secondary, Green, Blue, Orange
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
        }
    }

data class FavoriteItem(
    val id: Int,
    @DrawableRes val iconRes: Int,
    val label: String,
    val name: String? = null,
    val color: ColorToPick? = null,
    val lat: Double? = null,
    val lon: Double? = null,
)

fun FavoriteItem.toSingleItem() = DirectionsFeatureItemType.SingleItem(
    id = "f$id",
    iconRes = iconRes,
    title = label,
    subTitle = name ?: "",
    points = PickTargetPointsType.Single(
        lat = lat ?: 0.0, lon = lon ?: 0.0
    )
)

val favorites = listOf(
    FavoriteItem(
        id = 1,
        color = ColorToPick.Primary, iconRes = AppIcon.house, label = "Σπίτι"
    ),
    FavoriteItem(
        id = 2,
        color = ColorToPick.Secondary, iconRes = AppIcon.catBank, label = "Δουλειά"
    ),
    FavoriteItem(
        id = 3,
        iconRes = AppIcon.catBurger,
        label = "Goody's",
        name = "Ερμού 23",
        lat = 40.64063,
        lon = 22.94338
    ),
    FavoriteItem(
        id = 6,
        label = "Πανεπιστήμιο",
        iconRes = AppIcon.graduation_cap,
        name = "Εγνατία 59",
        lat = 40.64003,
        lon = 22.94337
    ),
)

private sealed interface FavoriteItemColumnItem {
    data class isConfigured(
        val secondRow: String,
        val onClick: () -> Unit
    ) : FavoriteItemColumnItem

    data class isNotConfigured(
        val onConfigClick: () -> Unit
    ) : FavoriteItemColumnItem

    data class Add(
        val onAddCollectionClick: () -> Unit
    ) : FavoriteItemColumnItem
}

@Composable
private fun FavoriteItemColumn(
    modifier: Modifier = Modifier,
    iconColor: ColorToPick? = null,
    iconRes: Int,
    firstRowLabel: String,
    type: FavoriteItemColumnItem
) {
    val color: Color? = colorsMapSaves[iconColor]
    val iconSize = 33.dp
    val width = iconSize + 24.dp + 15.dp
    val textModifier = remember {
        Modifier.width(width)
    }

    SurfaceWithShadows(
        shape = AppShape.round15,
        color = AppColor.surfaceLowest,
        shadowElevation = 0,
        onClick = {
            when (type) {
                is FavoriteItemColumnItem.Add -> type.onAddCollectionClick()
                is FavoriteItemColumnItem.isConfigured -> type.onClick()
                is FavoriteItemColumnItem.isNotConfigured -> type.onConfigClick()
            }
        },
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
                    iconRes = iconRes,
                    color = AppColor.surface,
                    contentColor = AppColor.primary,
                    modifier = Modifier.size(iconSize)
                )
            } else SurfaceWithShadows(
                shape = AppShape.round20,
                shadowElevation = 10
            ) {
                IconButton(
                    iconRes = iconRes,
                    color = color,
                    contentColor = AppColor.surfaceLowest,
                    modifier = Modifier.size(iconSize)
                )
            }
            Spacer(modifier = Modifier.height(9.dp))
            when (type) {
                is FavoriteItemColumnItem.Add -> TextLabelLarge(
                    text = firstRowLabel,
                    textTargetWidth = width,
                    modifier = textModifier
                )

                is FavoriteItemColumnItem.isConfigured -> {
                    TextLabelLarge(
                        text = firstRowLabel,
                        textTargetWidth = width,
                        modifier = textModifier
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    TextLabelSmall(
                        text = type.secondRow,
                        modifier = textModifier,
                        textTargetWidth = width,
                    )
                }

                is FavoriteItemColumnItem.isNotConfigured -> {
                    TextLabelLarge(
                        text = firstRowLabel,
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
