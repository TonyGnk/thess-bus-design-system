package com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    selectionModifier: Modifier = Modifier,
    @DrawableRes iconRes: Int = 0,
    onClick: () -> Unit = {},
    color: Color = AppColor.transparent,
    contentDescription: String = "",
) {
    if (iconRes != 0) Box(
        modifier = selectionModifier
            .clip(AppShape.round20)
            .selectable(
                selected = false,
                onClick = onClick
            )
            .background(color)
            .padding(12.dp)
    ) {
        Icon(
            iconRes = iconRes,
            color = contentColorFor(color),
            contentDescription = contentDescription,
            modifier = modifier
        )
    }
}

@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    IconButton(
        iconRes = AppIcon.search,
        color = AppColor.primary,
    )
}
