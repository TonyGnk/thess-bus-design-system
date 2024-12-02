package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.favorites

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.ColorOptions
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.color
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.selection.menu.DropdownMenu
import com.tonyGnk.thessBus.designSystem.mobile.components.selection.menu.DropdownMenuItem
import com.tonyGnk.thessBus.designSystem.mobile.components.selection.menu.findSizeOfMenuItem
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties

@Composable
fun FavoriteItemColumn(
    modifier: Modifier = Modifier,
    itemWidth: Dp = 80.dp,
    onClick: () -> Unit = {},
    onDelete: () -> Unit = {},
    onUnpin: () -> Unit = {},
    onEdit: () -> Unit = {},
    isTheSelected: Boolean,
    onLongPressClick: () -> Unit = {},
    onContextMenuDismiss: () -> Unit = {},
    item: FavoriteItem
) {

    val listOfOptions = remember { listOf("Επεξεργασία", "Ξεκαρφίτσωμα", "Διαγραφή") }
    val sizeOfTheMenuItem = findSizeOfMenuItem(listOfOptions)

    Box(modifier) {
        CollectionColumnContent(
            modifier = Modifier.width(itemWidth),
            colorOptions = item.colorOptions,
            iconRes = item.appIcon.iconRes,
            itemType = item.type,
            onClick = onClick,
            onLongPressClick = onLongPressClick
        )

        AnimatedVisibility(
            visible = isTheSelected,
        ) {
            Popup(
                onDismissRequest = onContextMenuDismiss,
            ) {
                CollectionColumnContent(
                    modifier = Modifier.width(itemWidth),
                    colorOptions = item.colorOptions,
                    iconRes = item.appIcon.iconRes,
                    itemType = item.type,
                    onClick = onClick,
                    onLongPressClick = onLongPressClick
                )
            }
        }

        if (isTheSelected) DropdownMenu(
            sizeOfTheMenuItem = sizeOfTheMenuItem,
            expanded = true,
            offset = DpOffset(0.dp, 20.dp),
            onDismissRequest = onContextMenuDismiss,
            modifier = Modifier
        ) {
            DropdownMenuItem(
                onClick = {
                    onContextMenuDismiss()
                    onEdit()
                },
                sizeOfTheMenuItem = sizeOfTheMenuItem,
                trailingIconRes = AppIcon.Edit.iconRes,
                text = listOfOptions[0],
            )
            DropdownMenuItem(
                onClick = {
                    onContextMenuDismiss()
                    onUnpin()
                },
                trailingIconRes = AppIcon.Pin.iconRes,
                sizeOfTheMenuItem = sizeOfTheMenuItem,
                text = listOfOptions[1],
            )
            DropdownMenuItem(
                onClick = {
                    onContextMenuDismiss()
                    onDelete()
                },
                text = listOfOptions[2],
                makeItRed = true,
                bottomBorder = false,
                sizeOfTheMenuItem = sizeOfTheMenuItem,
                trailingIconRes = AppIcon.Trash.iconRes,
            )
        }
    }
}

@Composable
private fun CollectionColumnContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongPressClick: () -> Unit,
    colorOptions: ColorOptions?,
    iconRes: Int,
    itemType: FavoriteItemType,
) {
    SurfaceWithShadows(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        shape = AppShape.round15,
        color = AppColor.surfaceLowest,
        shadowElevation = 0,
        onClick = onClick,
        onLongClick = {
            onLongPressClick()
        },
    ) {
        Column(
            modifier = Modifier.padding(LocationsProperties.IN_PADDING.div(2).dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (colorOptions == null) SurfaceWithShadows(
                modifier = Modifier.padding(horizontal = 5.dp),
                shape = AppShape.round20,
                shadowElevation = 0
            ) {
                IconButton(
                    iconRes = iconRes,
                    color = AppColor.surface,
                    contentColor = AppColor.primary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxSize()//.size(iconSize)
                )
            } else SurfaceWithShadows(
                modifier = Modifier.padding(horizontal = 5.dp),
                shape = AppShape.round20,
                shadowElevation = 6
            ) {
                IconButton(
                    iconRes = iconRes,
                    color = colorOptions.color(),
                    contentColor = AppColor.surfaceLowest,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(9.dp))
            when (itemType) {
                is FavoriteItemType.Configured -> {
                    FavoritesLargeLabel(
                        text = itemType.title,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    FavoritesSmallLabel(
                        text = itemType.subTitle,
                    )
                }

                is FavoriteItemType.NotConfigured -> {
                    FavoritesLargeLabel(
                        text = itemType.label,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    FavoritesSmallLabel(
                        text = "Προσθήκη",
                    )
                }
            }
        }
    }
}
