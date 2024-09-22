package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.LocationsProperties

@Composable
fun HistoryList(modifier: Modifier = Modifier) {
    SurfaceWithShadows(
        shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
        color = AppColor.surfaceContainerLowest,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "History")
        }
    }
}
