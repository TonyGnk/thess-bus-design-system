package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview.collection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties


@Composable
fun CollectionWidget(
    modifier: Modifier = Modifier,
    onFavoriteNotConfiguredClick: () -> Unit,
    onFavoriteClick: (DirectionsFeatureItemType.SingleItem?) -> Unit,
    onUnpinItem: (Int) -> Unit,
    favorites: List<FavoriteItem>,
    onEditItem: (Int) -> Unit,
    onDeleteItem: (Int) -> Unit,
    selectedFavoriteItemId: Int?,
    updateSelectedFavoriteItemId: (Int?) -> Unit,
    onAddCollectionClick: () -> Unit
) {
    val lazyRowState = rememberLazyListState()

    LazyRow(
        modifier = modifier.padding(vertical = LocationsProperties.IN_PADDING.div(2).dp),
        state = lazyRowState,
        contentPadding = PaddingValues(horizontal = LocationsProperties.IN_PADDING.div(2).dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(
            LocationsProperties.ARRANGEMENT.div(4).dp
        )
    ) {
        items(
            items = favorites, key = { it.id }
        ) {
            val isTheSelected = selectedFavoriteItemId == it.id

            FavoriteItemColumn(
                modifier = Modifier.animateItem(),
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
                isTheSelected = isTheSelected,
                onContextMenuDismiss = {
                    updateSelectedFavoriteItemId(null)
                },
                onLongPressClick = {
                    if (it.type != FavoriteItemType.Add) {
                        updateSelectedFavoriteItemId(it.id)
                    }
                },
                onEdit = {
                    if (it.type != FavoriteItemType.Add) onEditItem(it.id)
                },
                onDelete = {
                    if (it.type != FavoriteItemType.Add) onDeleteItem(it.id)

                },
                onUnpin = {
                    if (it.type != FavoriteItemType.Add) onUnpinItem(it.id)
                }
            )
        }
        item {
            FavoriteItemColumn(
                item = FavoriteItem(
                    id = 0,
                    iconRes = AppIcon.add,
                    type = FavoriteItemType.Add
                ),
                onClick = onAddCollectionClick,
                isTheSelected = false
            )
        }
    }
}