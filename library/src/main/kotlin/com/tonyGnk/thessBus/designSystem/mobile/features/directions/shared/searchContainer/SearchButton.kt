package com.tonyGnk.thessBus.designSystem.mobile.features.directions.shared.searchContainer


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement

@Composable
fun SearchButton(
    searchLabel: String,
    onClick: () -> Unit,
    color: Color,
    rippleColor: Color,
    modifier: Modifier = Modifier
) {
    val searchStyle = NavCardProperties.searchTextStyle
    val sizeInScreen = searchLabel.findScreenSize(searchStyle)

    SurfaceWithShadows(
        shadowElevation = 2,
        shape = RoundedCornerShape(NavCardProperties.IN_CORNERS.dp),
        color = color,
        modifier = modifier
            .mySharedElement("SearchContainer")
            .clip(RoundedCornerShape(NavCardProperties.IN_CORNERS.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = rippleColor),
                onClick = onClick
            )
    ) {
        Row(
            modifier = modifier.padding(NavCardProperties.IN_PADDING.dp)
        ) {
            Text(
                text = searchLabel,
                style = searchStyle,
                modifier = Modifier
                    .weight(1f)
                    .mySharedElement("SearchContainerText")
            )
            Icon(
                iconRes = AppIcon.search,
                color = AppColor.onSurface,
                modifier = Modifier
                    .size(sizeInScreen.height)
                    .mySharedElement("SearchContainerMagnifier")
            )
        }
    }
}
