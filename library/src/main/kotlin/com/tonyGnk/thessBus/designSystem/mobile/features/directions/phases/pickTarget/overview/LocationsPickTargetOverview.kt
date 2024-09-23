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
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.TonalButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeFavorites
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeHistory
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.searchMode.PickTargetResult
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.LocationsProperties

@Stable
data class LocationsPickTargetOverviewItems(
    val onCategoriesClick: () -> Unit,
    val favorites: List<DirectionsFeatureItemType.SingleItem>,
    val history: List<DirectionsFeatureItemType.SingleItem>,
    val horizontalPadding: PaddingValues,
    val onItemClick: (DirectionsFeatureItemType.SingleItem) -> Unit
) {
    companion object {
        val preview = LocationsPickTargetOverviewItems(
            onCategoriesClick = {},
            favorites = PickTargetFakeFavorites,
            history = PickTargetFakeHistory,
            horizontalPadding = PaddingValues(0.dp),
            onItemClick = {}
        )
    }
}

@Composable
internal fun LocationsPickTargetOverview(
    modifier: Modifier = Modifier,
    items: LocationsPickTargetOverviewItems = LocationsPickTargetOverviewItems.preview,
) {
    Column(
        modifier = modifier.padding(top = 0.dp),//14
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
//        QuickActions(
//            onCategoriesClick = items.onCategoriesClick,
//            horizontalPadding = items.horizontalPadding
//        )

        Favorites()

//        FavoritesOld(
//            modifier = Modifier.padding(items.horizontalPadding),
//            label = "Saved Places",
//            items = items.favorites,
//            onItemClick = items.onItemClick
//        )


        FavoritesOld(
            modifier = Modifier.padding(items.horizontalPadding),
            label = "Recent",
            items = items.history,
            onItemClick = items.onItemClick
        )
    }
}

val favorites = listOf(
    FavoriteItem(iconRes = AppIcon.house, name = "Σπίτι", color = Color(0xFF005C8F)),
    FavoriteItem(iconRes = AppIcon.routes, name = "Δουλειά", color = Color(0xFF8F005C)),
    FavoriteItem(iconRes = AppIcon.sign, name = "Μασούτης", color = Color(0xFF5C8F00)),
    //Προσθήκη
    FavoriteItem(iconRes = AppIcon.add, name = "Προσθήκη", color = Color(0xFF2F2F2F)),
)


@Composable
private fun QuickActions(
    modifier: Modifier = Modifier,
    onCategoriesClick: () -> Unit,
    horizontalPadding: PaddingValues
) {
    LazyRow(
        contentPadding = horizontalPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            TonalButton(
                iconRes = AppIcon.mapMarker,
                text = "Επιλέξτε",
                padding = PaddingValues(18.dp),
            )
        }
        item {
            TonalButton(
                iconRes = AppIcon.routes,
                text = "Διαδρομές",
                padding = PaddingValues(18.dp),
            )
        }
        item {
            TonalButton(
                iconRes = AppIcon.sign,
                text = "Στάσεις",
                padding = PaddingValues(18.dp),
            )
        }
        item {
            TonalButton(
                iconRes = AppIcon.category,
                text = "Κατηγορίες",
                onClick = onCategoriesClick,
                padding = PaddingValues(18.dp),
            )
        }
    }
}

@Composable
@AppPreview.Dark
private fun Favorites(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp)
            .background(
                color = AppColor.surfaceContainerLowest,
                shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp)
            )
            .padding(
                14.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        items(items = favorites) {
            FavoriteItem(
                modifier = Modifier.width(70.dp),
                item = it
            )
        }
        items(items = favorites) {
            FavoriteItem(
                modifier = Modifier
                    .width(70.dp),
                item = it
            )
        }
    }

}

data class FavoriteItem(
    val iconRes: Int,
    val name: String,
    val color: Color,
)

@Composable
@AppPreview.Dark
private fun FavoriteItem(
    modifier: Modifier = Modifier,
    item: FavoriteItem = FavoriteItem(
        iconRes = AppIcon.mapMarker, name = "Σπίτι", color = Color(
            0xFF005C8F
        )
    )
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 7.dp)
                .aspectRatio(1f)
                .background(
                    color = item.color,
                    shape = CircleShape
                )
        ) {
            Icon(
                iconRes = item.iconRes,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = item.name,
            softWrap = true,
            maxLines = 1,
            style = AppTypo.bodySmall
        )
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = "Προσθήκη",
            softWrap = true,
            maxLines = 1,
            color = AppColor.primary,
            style = AppTypo.bodySmall.copy(
                fontSize = AppTypo.bodySmall.fontSize.div(1.2f)
            )
        )
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

//@AppPreview.Dark
//@Composable
//private fun Preview() = ClpTheme {
//    DirectionsPickTargetOverview()
//}
