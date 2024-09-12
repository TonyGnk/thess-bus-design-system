package com.tonyGnk.thessBus.designSystem.mobile.features.directions.shared.searchContainer


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
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
    val sizeInScreen = searchLabel.findScreenSize(searchStyle).height - 1.dp

    SurfaceWithShadows(
        shadowElevation = 2,
        shape = RoundedCornerShape(NavCardProperties.IN_CORNERS.dp),
        color = color,
        contentColor = rippleColor,
        onClick = onClick,
        modifier = modifier.mySharedElement("SearchContainer")
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
            modifier = modifier.padding(
                start = NavCardProperties.IN_PADDING.dp,
                end = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp,
            )
        ) {
            Text(
                text = searchLabel,
                style = searchStyle,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = NavCardProperties.IN_PADDING.dp)
                    .mySharedElement("SearchContainerText")
            )
//            Icon(
//                iconRes = AppIcon.search,
//                color = AppColor.onSurface,
//                modifier = Modifier
//                    .size(sizeInScreen)
//                    .mySharedElement("SearchContainerMagnifier")
//            )
            IconButton(
                iconRes = AppIcon.search,
                color = AppColor.transparent,
                modifier = Modifier
                    .size(sizeInScreen)
                    .mySharedElement("SearchContainerMagnifier")
            )
        }
    }
}
