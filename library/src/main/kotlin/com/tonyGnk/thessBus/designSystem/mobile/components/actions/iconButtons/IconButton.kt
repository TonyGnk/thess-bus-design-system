package com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons


import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.material3.IconButton as MaterialIconButton

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int = 0,
    onClick: () -> Unit = {},
    color: Color = AppColor.transparent,
    contentColor: Color = AppColor.onSurface,
    contentDescription: String = "",
) {
//    if (iconRes != 0) MaterialIconButton(
//        onClick = onClick,
//        colors = IconButtonDefaults.iconButtonColors(
//            containerColor = color,
//            contentColor = contentColor
//        ),
//       // modifier = buttonModifier.padding(8.dp)
//    ) {
//        Box( buttonModifier.padding(0.dp)) {
//            Icon(
//                iconRes = iconRes,
//                color = contentColor,
//                contentDescription = contentDescription,
//                modifier = modifier
//            )
//        }
//    }

    if (iconRes != 0) Box(
        modifier =Modifier.clip(AppShape.round20).selectable(
            selected = false,
            onClick = onClick
        )
            .padding(12.dp)
    ){
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
