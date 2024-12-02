package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties

@Composable
fun AddFavoriteColumn(
    modifier: Modifier = Modifier,
    itemWidth: Dp = 80.dp,
    onClick: () -> Unit = {},
) {

    CollectionColumnContent(
        modifier = modifier.width(itemWidth),
        onClick = onClick,
    )
}

@Composable
private fun CollectionColumnContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    SurfaceWithShadows(
        modifier = modifier,
        shape = AppShape.round15,
        color = AppColor.surfaceLowest,
        shadowElevation = 0,
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier.padding(LocationsProperties.IN_PADDING.div(2).dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SurfaceWithShadows(
                modifier = Modifier.padding(horizontal = 5.dp),
                shape = AppShape.round20,
                shadowElevation = 0
            ) {
                IconButton(
                    iconRes = AppIcon.Add.iconRes,
                    color = AppColor.surface,
                    contentColor = AppColor.primary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(9.dp))
            FavoritesLargeLabel(
                text = "Προσθήκη",
            )
        }
    }
}
