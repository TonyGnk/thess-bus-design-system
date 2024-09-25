package com.tonyGnk.thessBus.designSystem.mobile.components.navigation.navigationBar

import android.content.Context
import android.os.Build
import android.view.RoundedCorner
import android.view.WindowManager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.navigationBar.item.NavigationBarItem
import androidx.compose.material3.NavigationBar as MaterialNavigationBar

@Composable
fun NavigationBar(enabledItems: Triple<Boolean, Boolean, Boolean>) {
    val selected = remember { mutableIntStateOf(0) }
    val topCorners = findTheRightCorners(LocalView.current.context)

    MaterialNavigationBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = topCorners, topEnd = topCorners)),
        containerColor = AppColor.surfaceLowest,
    ) {
        if (enabledItems.first) NavigationBarItem(
            itemLabel = stringResource(R.string.destination_home),
            isSelected = selected.intValue == 0,
            onItemClick = { selected.intValue = 0 },
            animationResource = R.raw.location_pin,
            modifier = Modifier.weight(1f),
            radius = topCorners
        )
        if (enabledItems.second) NavigationBarItem(
            itemLabel = stringResource(R.string.destination_lines),
            isSelected = selected.intValue == 1,
            onItemClick = { selected.intValue = 1 },
            animationResource = R.raw.lines,
            modifier = Modifier.weight(1f),
            radius = topCorners
        )

        if (enabledItems.third) NavigationBarItem(
            itemLabel = stringResource(R.string.destination_alarms),
            isSelected = selected.intValue == 2,
            onItemClick = { selected.intValue = 2 },
            animationResource = R.raw.alarm_clock_second,
            modifier = Modifier.weight(1f),
            radius = topCorners
        )
    }
}

private const val STANDARD_CORNER_RADIUS_FOR_NAVIGATION_BAR = 38

@Composable
private fun findTheRightCorners(context: Context): Dp {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val insets = windowManager.currentWindowMetrics.windowInsets
        val bottomLeft = insets.getRoundedCorner(RoundedCorner.POSITION_BOTTOM_LEFT)

        if (bottomLeft != null) return getCornerSizeFromRadius(bottomLeft.radius)
    }
    return STANDARD_CORNER_RADIUS_FOR_NAVIGATION_BAR.dp
}

@Composable
private fun pxToDp(px: Int): Dp {
    val density = LocalDensity.current.density
    return (px / density).dp
}

@Composable
private fun getCornerSizeFromRadius(radiusInPx: Int): Dp {
    val radiusInDp = pxToDp(radiusInPx)
    return radiusInDp
}
