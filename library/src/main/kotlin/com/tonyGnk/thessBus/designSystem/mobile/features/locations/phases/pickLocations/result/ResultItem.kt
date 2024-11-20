package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize

@Composable
internal fun ResultItem(
    title: String,
    subTitle: String,
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val titleStyle = AppTypo.bodyLarge
    val subTitleStyle = AppTypo.labelMedium

    val totalHeight =
        title.findScreenSize(titleStyle).height + subTitle.findScreenSize(subTitleStyle).height

    // Trying to make exactly the same layout as the search bar
    val paddingOfTheBackButtonInSearch = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp + 12.dp
    val paddingForTheIcon = 7.dp
    val padding = paddingOfTheBackButtonInSearch - paddingForTheIcon

    SurfaceWithShadows(
        color = AppColor.surfaceLowest,
        shadowElevation = 0,
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(padding),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(totalHeight)
                    .padding(7.dp)
            ) {
                Icon(
                    iconRes = iconRes,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    style = titleStyle.copy(
                        color = AppColor.onSurface
                    )
                )
                if (subTitle.isNotBlank()) Text(
                    text = subTitle,
                    style = subTitleStyle.copy(
                        color = AppColor.onSurface.copy(alpha = 0.7f)
                    )
                )
            }
        }
    }
}
