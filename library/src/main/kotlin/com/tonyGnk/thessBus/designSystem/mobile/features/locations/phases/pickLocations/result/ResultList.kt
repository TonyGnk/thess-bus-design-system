package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType

@Composable
internal fun ResultList(
    modifier: Modifier = Modifier,
    onClick: (DirectionsFeatureItemType.SingleItem) -> Unit,
    items: List<DirectionsFeatureItemType.SingleItem>
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        items.forEach { result ->
            ResultItem(
                title = result.title,
                subTitle = result.subTitle,
                iconRes = result.iconRes,
                onClick = {
                    onClick(result)
                }
            )
        }
    }
}
