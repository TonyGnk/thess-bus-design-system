package com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import androidx.compose.material3.Button as MaterialFilledButton

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    text: String = "Filled Button",
    onClick: () -> Unit = { Log.d("Design System", text) },
    @DrawableRes iconRes: Int = 0,
    color: Color = AppColor.primary,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(DefaultButtonValues.CORNER_RADIUS.dp),
    padding: Int = DefaultButtonValues.PADDING,
) {

    val contentColor = contentColorFor(color)

    MaterialFilledButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = contentColor,
        ),
        border = BorderStroke(
            width = 2.dp,
            color = Color.Black.copy(alpha = 0.1f)
        ),
        shape = shape,
        contentPadding = PaddingValues(padding.dp),
        content = {
            SharedButtonContent(text = text, iconRes = iconRes, contentColor = contentColor)
        },
    )
}

@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    FilledButton(iconRes = AppIcon.search)
}
