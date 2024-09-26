package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.IconWithTextRow
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.LocationsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.searchMode.PickTargetResult
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme


@Composable
internal fun PickTargetOverview(
    modifier: Modifier = Modifier,
    items: LocationsPickTargetItems = LocationsPickTargetItems.preview,
) {
    val labelStyle = AppTypo.titleMedium.copy(
        color = AppColor.onSurface.copy(alpha = 0.9f)
    )
    val arrangement = remember {
        LocationsProperties.ARRANGEMENT
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(arrangement.div(0.5).dp)
    ) {
        SurfaceWithShadows(
            shadowElevation = 0,
            modifier = Modifier
                .fillMaxWidth()
                .padding(items.horizontalPadding),
            shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
            color = AppColor.surfaceLowest,
            onClick = {}
        ) {
            IconWithTextRow(
                modifier = Modifier.padding(LocationsProperties.IN_PADDING.dp),
                contentColor = AppColor.primary,
                iconRes = AppIcon.mapMarker,
                style = AppTypo.titleMedium.copy(color = AppColor.primary),
                weight = FontWeight.W500,
                arrangement = Arrangement.spacedBy(18.dp),
                text = "Pick on map",
            )
        }
        OverviewCardLayout(
            modifier = Modifier.fillMaxWidth(),
            outerHorizontalPadding = items.horizontalPadding,
            itemArrangementDp = arrangement.dp,
            labelStyle = labelStyle,
            label = "Collections"
        ) {
            PickTargetOverviewFavorites(
                onAddCollectionClick = items.onAddCollectionClick
            )
        }

        OverviewCardLayout(
            modifier = Modifier.fillMaxWidth(),
            outerHorizontalPadding = items.horizontalPadding,
            itemArrangementDp = arrangement.dp,
            labelStyle = labelStyle,
            label = "Categories"
        ) {
            PickTargetOverviewCategories(
            )
        }

        FavoritesOld(
            modifier = Modifier.padding(items.horizontalPadding),
            label = "Recent",
            items = items.history,
            onItemClick = items.onItemClick
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

@AppPreview.Light
@Composable
private fun Preview() = ClpTheme {
    PickTargetOverview()
}
