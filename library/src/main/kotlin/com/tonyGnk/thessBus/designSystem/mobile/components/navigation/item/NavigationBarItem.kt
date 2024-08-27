package com.tonyGnk.thessBus.designSystem.mobile.components.navigation.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import androidx.compose.material3.NavigationBarItem as MaterialNavigationBarItem


@Composable
internal fun RowScope.NavigationBarItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    shape: Shape = AppShape.rectangle,
    content: @Composable () -> Unit = {}
) {
    MaterialNavigationBarItem(
        modifier = modifier.clip(shape),
        selected = isSelected,
        onClick = onClick,
        colors = NavigationBarItemColors(
            selectedIndicatorColor = AppColor.transparent,
            selectedIconColor = AppColor.transparent,
            unselectedIconColor = AppColor.transparent,
            unselectedTextColor = AppColor.transparent,
            disabledIconColor = AppColor.transparent,
            disabledTextColor = AppColor.transparent,
            selectedTextColor = AppColor.transparent,
        ),
        icon = content
    )
}

@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    Row {
        NavigationBarItem()
    }
}
