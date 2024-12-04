package com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.recent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.result.ResultItem
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.target.LocationsPickTargetItems

private const val RecentLimit = 10

@Composable
internal fun RecentList(state: LocationsPickTargetItems.RecentState) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        state.items.take(RecentLimit).forEach { item ->
            ResultItem(
                title = item.label,
                subTitle = "",
                iconRes = AppIcon.ClockFive.iconRes,
                onClick = {
                    state.onClick(item)
                }
            )
        }

//        LocationSearchListButton(
//            modifier = Modifier.fillMaxWidth(),
//            label = "Load More",
//            color = AppColor.primary
//        )
    }
}
