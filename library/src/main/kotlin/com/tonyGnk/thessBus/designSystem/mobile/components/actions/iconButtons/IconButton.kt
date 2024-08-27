package com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons


import androidx.annotation.DrawableRes
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import androidx.compose.material3.IconButton as MaterialIconButton

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int = 0,
    buttonModifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    color: Color = AppColor.transparent,
    contentColor: Color = AppColor.onPrimary,
    contentDescription: String = "",
) {
    if (iconRes != 0) MaterialIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = color,
            contentColor = contentColor
        ),
        modifier = buttonModifier
    ) {
        Icon(
            iconRes = iconRes,
            color = contentColor,
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
        contentColor = AppColor.onPrimary,
    )
}
