package com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.DefaultButtonValues.PADDING
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize

@Composable
fun SharedButtonContent(
    text: String,
    @DrawableRes iconRes: Int,
    contentColor: Color,
    modifier: Modifier = Modifier,
    style: TextStyle = AppTypo.labelLarge.copy(color = contentColor)
) {
    when (iconRes) {
        0 -> Text(text = text, style = style, modifier = modifier)

        else -> {
            val size = text.findScreenSize(style)
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(PADDING.dp),
            ) {
                Icon(
                    iconRes = iconRes, color = contentColor, modifier = Modifier.size(size.height)
                )
                Text(text = text, style = style)
            }
        }
    }
}

@Composable
fun IconWithTextRow(
    text: String,
    @DrawableRes iconRes: Int,
    contentColor: Color,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Horizontal,
    style: TextStyle = AppTypo.labelLarge.copy(color = contentColor),
    weight: FontWeight = FontWeight.Normal
) {
    when (iconRes) {
        0 -> Text(text = text, style = style, weight = weight, modifier = modifier)

        else -> {
            val size = text.findScreenSize(style)
            Row(
                modifier = modifier,
                horizontalArrangement = arrangement,
            ) {
                Icon(
                    iconRes = iconRes,
                    color = contentColor,
                    modifier = Modifier.size(size.height + 1.dp)
                )
                Text(text = text, style = style, weight = weight)
            }
        }
    }
}
