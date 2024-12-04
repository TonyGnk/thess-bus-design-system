package com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.shared.card.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.target.LocationsPickTargetItems


@Composable
fun FavoritesWidget(
    modifier: Modifier = Modifier,
    state: LocationsPickTargetItems.FavoritesState,
) {
    val lazyRowState = rememberLazyListState()
    val itemWidth = remember { 80.dp }

    SurfaceWithShadows(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 0,
        color = AppColor.surfaceLowest,
        shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
    ) {
        LazyRow(
            modifier = modifier.padding(vertical = LocationsProperties.IN_PADDING.div(2).dp),
            state = lazyRowState,
            contentPadding = PaddingValues(
                horizontal = LocationsProperties.IN_PADDING.div(2).dp
            ),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(
                items = state.items, key = { it.id }
            ) {
                val isTheSelected = state.selectedId == it.id

                FavoriteItemColumn(
                    itemWidth = itemWidth,
                    modifier = Modifier.animateItem(),
                    item = it,

                    onClick = {
                        when (it.type) {
                            is FavoriteItemType.Configured -> {
                                state.onClick(it.toSingleItem())
                            }

                            is FavoriteItemType.NotConfigured -> {
                                state.onNotConfigured()
                            }
                        }
                    },
                    isTheSelected = isTheSelected,
                    onContextMenuDismiss = {
                        state.updateSelectedFavoriteItemId(null)
                    },
                    onLongPressClick = {
                        state.updateSelectedFavoriteItemId(it.id)
                    },
                    onEdit = {
                        state.onEditItem(it.id)
                    },
                    onDelete = {
                        state.onDeleteItem(it.id)

                    },
                    onUnpin = {
                        state.onUnpinItem(it.id)
                    }
                )
            }
            item {
                AddFavoriteColumn(
                    itemWidth = itemWidth,
                    onClick = state.onAdd,
                )
            }
        }
    }
}
