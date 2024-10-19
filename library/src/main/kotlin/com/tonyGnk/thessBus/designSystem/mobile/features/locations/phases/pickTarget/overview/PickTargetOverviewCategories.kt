package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.IconWithTextRow
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.LocationsPoiCategory
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PickTargetOverviewCategories(
    modifier: Modifier = Modifier,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(
            7.dp
        ),
        verticalArrangement = Arrangement.spacedBy(
            7.dp
        ),
        modifier = Modifier.padding(7.dp),
        maxItemsInEachRow = 2
    ) {
        LocationsPoiCategory.entries.take(8).forEach { category ->
            CategoryItem(
                modifier = Modifier.weight(1f),
                iconRes = category.iconRes,
                label = category.osmLabel,
                onClick = { }
            )
        }
    }
}

@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    label: String,
    onClick: () -> Unit
) {
    SurfaceWithShadows(
        //color = AppColor.surface,
        shadowElevation = 0,
        shape = RoundedCornerShape(
            LocationsProperties.IN_CORNERS.dp
        ),
        contentColor = AppColor.secondary,
//        iconRes = iconRes,
//        text = label,
//        textStyle = AppTypo.bodyLarge,
        modifier = modifier.padding(0.dp),
        onClick = {}
    )
    {
        IconWithTextRow(
            modifier = Modifier.padding(
                LocationsProperties.IN_PADDING.div(1.2).dp
            ),
            style = AppTypo.bodyMedium.copy(color = AppColor.secondary),
            iconRes = iconRes,
            text = label,
            arrangement = Arrangement.spacedBy(
                LocationsProperties.IN_PADDING.div(1.2).dp
            ),
            contentColor = AppColor.secondary,
        )
    }
}

@AppPreview.Zoomed
@Composable
private fun Preview() = ThessBusTheme {
//    CategoryItem(
//        iconRes = AppIcon.mapMarker,
//        label = "Κατηγορία",
//        onClick = { }
//    )
    PickTargetOverviewCategories()
}
