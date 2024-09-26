package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties


@Composable
fun OverviewCardLayout(
    modifier: Modifier = Modifier,
    label: String,
    outerHorizontalPadding: PaddingValues,
    labelStyle: TextStyle,
    itemArrangementDp: Dp,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(itemArrangementDp),
        modifier = modifier.padding(outerHorizontalPadding)
    ) {
        Text(text = label, style = labelStyle)
        SurfaceWithShadows(
            shadowElevation = 0,
            color = AppColor.surfaceLowest,
            shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
        ) {
            content()
        }
    }
}
