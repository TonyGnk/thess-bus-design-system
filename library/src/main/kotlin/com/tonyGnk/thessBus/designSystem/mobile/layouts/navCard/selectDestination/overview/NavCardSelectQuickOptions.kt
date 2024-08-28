package com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize

@Composable
internal fun NavCardSelectQuickOptions(
    modifier: Modifier = Modifier
) {
    val text = "Select in the map"
    val style = AppTypo.titleMedium
    val size = text.findScreenSize(style).height - 1.dp
    val shape = RoundedCornerShape(NavCardProperties.SMALL_CORNERS.dp)

    SurfaceWithShadows(
        shape = shape,
        color = AppColor.surfaceContainerLowest,
        modifier = modifier
            .fillMaxWidth()
            .padding(1.dp)
            .zIndex(1f)
            .clip(shape)
            .clickable { },
    ) {
        Row(
            modifier = Modifier.padding(NavCardProperties.SEARCH_PADDING.dp),
            horizontalArrangement = Arrangement.spacedBy(NavCardProperties.SEARCH_PADDING.dp),
        ) {
            Icon(
                iconRes = R.drawable.map_marker,
                color = AppColor.onSurface,
                modifier = Modifier.size(size)
            )
            Text(
                text = text,
                style = style,
                color = AppColor.onSurface,
            )
        }

    }
}
