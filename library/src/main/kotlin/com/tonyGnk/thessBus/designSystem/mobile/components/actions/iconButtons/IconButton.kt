package com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIconWithEnum
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    selectionModifier: Modifier = Modifier,
    @DrawableRes iconRes: Int = 0,
    onClick: (() -> Unit)? = null,
    color: Color = AppColor.background,
    contentColor: Color = AppColor.onSurface,
    shape: Shape = AppShape.round20,
    contentDescription: String = "",
) {
    if (iconRes != 0) Box(
        modifier = selectionModifier
            .clip(shape)
            .background(color)
            .then(
                if (onClick != null) Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(color = contentColor),
                    onClick = onClick
                ) else Modifier
            )
            .padding(12.dp)
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
private fun Preview() = ThessBusTheme {
    IconButton(
        iconRes = AppIconWithEnum.SEARCH.iconRes,
        color = AppColor.primary,
        contentColor = AppColor.onPrimary
    )
}
