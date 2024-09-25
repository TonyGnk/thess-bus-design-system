package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeFavorites
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeHistory
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetPointsType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.searchMode.PickTargetResult
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme

@Stable
data class LocationsPickTargetOverviewItems(
    val onCategoriesClick: () -> Unit,
    val favorites: List<DirectionsFeatureItemType.SingleItem>,
    val history: List<DirectionsFeatureItemType.SingleItem>,
    val horizontalPadding: PaddingValues,
    val onItemClick: (DirectionsFeatureItemType.SingleItem) -> Unit,
    val itemArrangementDp: Dp,
    val onAddCollectionClick: () -> Unit
) {
    companion object {
        val preview = LocationsPickTargetOverviewItems(
            onCategoriesClick = {},
            favorites = PickTargetFakeFavorites,
            history = PickTargetFakeHistory,
            horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
            onItemClick = {},
            itemArrangementDp = 6.dp,
            onAddCollectionClick = {}
        )
    }
}

@Composable
internal fun LocationsPickTargetOverview(
    modifier: Modifier = Modifier,
    items: LocationsPickTargetOverviewItems = LocationsPickTargetOverviewItems.preview,
) {
    val labelStyle = AppTypo.titleMedium.copy(
        color = AppColor.onSurface.copy(alpha = 0.8f)
    )

    Column(
        modifier = modifier.padding(top = 0.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Favorites(
            modifier = Modifier.fillMaxWidth(),
            outerHorizontalPadding = items.horizontalPadding,
            itemArrangementDp = items.itemArrangementDp,
            labelStyle = labelStyle,
            onAddCollectionClick = items.onAddCollectionClick
        )

        FavoritesOld(
            modifier = Modifier.padding(items.horizontalPadding),
            label = "Recent",
            items = items.history,
            onItemClick = items.onItemClick
        )
    }
}


@Composable
private fun Favorites(
    modifier: Modifier = Modifier,
    outerHorizontalPadding: PaddingValues,
    labelStyle: TextStyle,
    itemArrangementDp: Dp,
    onAddCollectionClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(itemArrangementDp),
        modifier = Modifier.padding(outerHorizontalPadding)
    ) {
        Spacer(Modifier.height(200.dp))
        Text(text = "Collection", style = labelStyle)
        LazyRow(
            modifier = modifier
                .background(
                    color = AppColor.surfaceLowest,
                    shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp)
                )
                .padding(vertical = LocationsProperties.IN_PADDING.dp),
            contentPadding = PaddingValues(horizontal = LocationsProperties.IN_PADDING.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(LocationsProperties.SEARCH_ARRANGEMENT.dp)
        ) {
            items(items = favorites) {
                FavoriteItem(
                    modifier = Modifier.width(70.dp),
                    color = it.color ?: AppColor.primary,
                    iconColor = it.iconColor ?: AppColor.primary,
                    iconRes = it.customIconRes ?: it.item?.iconRes ?: AppIcon.question,
                    firstRowLabel = it.name,
                    isAddButton = false,
                    secondRowLabel = it.item?.subTitle
                )
            }
            item {
                FavoriteItem(
                    modifier = Modifier.width(70.dp),
                    color = Color(0xFF1A1A1A),
                    iconColor = AppColor.primary,
                    firstRowLabel = "Προσθήκη",
                    isAddButton = true,
                    iconRes = AppIcon.add,
                )
            }
        }
        Spacer(Modifier.height(200.dp))
    }

}

data class FavoriteItem(
    val customIconRes: Int? = null,
    val name: String,
    val color: Color? = null,
    val iconColor: Color? = null,
    val item: DirectionsFeatureItemType.SingleItem? = null
)


val item = DirectionsFeatureItemType.SingleItem(
    id = "1",
    iconRes = AppIcon.clockFive,
    title = "Πρόσφατα",
    subTitle = "Πρόσφατα",
    points = PickTargetPointsType.Single(
        lat = 0.0, lon = 0.0
    )
)

val favorites = listOf(
    FavoriteItem(
        customIconRes = AppIcon.house, name = "Σπίτι", color = Color(0xFF005C8F), item = item
    ),
    FavoriteItem(
        customIconRes = AppIcon.routes, name = "Δουλειά", item = item
    ),
    FavoriteItem(
        name = "Μασούτης", color = Color(0xFF5C8F00), item = item
    ),
    FavoriteItem(
        name = "Στάσεις", item = item
    ),
)

@Composable
private fun FavoriteItem(
    modifier: Modifier = Modifier,
    color: Color,
    iconColor: Color,
    iconRes: Int,
    firstRowLabel: String,
    isAddButton: Boolean,
    secondRowLabel: String? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 7.dp)
                .aspectRatio(1f)
                .background(
                    color = color,
                    shape = CircleShape
                )
        ) {
            Icon(
                iconRes = iconRes,
                color = iconColor
            )
        }
        Spacer(modifier = Modifier.height(9.dp))
        if (!isAddButton) {
            Text(
                text = firstRowLabel,
                softWrap = true,
                maxLines = 1,
                style = AppTypo.bodySmall
            )
            Spacer(modifier = Modifier.height(1.dp))
            if (secondRowLabel != null) Text(
                text = secondRowLabel,
                softWrap = true,
                maxLines = 1,
                style = AppTypo.bodySmall.copy(
                    color = AppColor.primary,
                    fontSize = AppTypo.bodySmall.fontSize.div(1.2f)
                )
            )
        } else {
            Text(
                text = firstRowLabel,
                softWrap = true,
                maxLines = 1,
                style = AppTypo.bodySmall
            )
        }

    }
}

@Composable
private fun FavoritesOld(
    modifier: Modifier = Modifier,
    label: String,
    items: List<DirectionsFeatureItemType.SingleItem>,
    onItemClick: (DirectionsFeatureItemType.SingleItem) -> Unit = {}
) {

    Column(
        modifier = modifier.padding(bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = AppTypo.titleMedium)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            items.forEach { item ->
                PickTargetResult(
                    result = item, onClick = {
                        onItemClick(item)
                    }
                )
            }
        }
    }
}

@AppPreview.Light
@Composable
private fun Preview() = ClpTheme {
    LocationsPickTargetOverview()
}
