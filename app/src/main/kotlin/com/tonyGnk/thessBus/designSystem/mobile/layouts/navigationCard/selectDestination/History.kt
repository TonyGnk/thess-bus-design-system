package com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.selectDestination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.components.surface.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.utils.TransitionType
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement

@Composable
fun HistoryList(modifier: Modifier = Modifier) {
    SurfaceWithShadows(
        shape = RoundedCornerShape(NavCardProperties.SMALL_CORNERS.dp),
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