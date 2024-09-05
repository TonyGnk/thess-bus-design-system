package com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import androidx.compose.material3.TextButton as MaterialTextButton

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String = "Text Button",
    onClick: () -> Unit = { Log.d("Design System", text) },
    @DrawableRes iconRes: Int = 0,
    contentColor: Color = AppColor.primary,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(DefaultButtonValues.CORNER_RADIUS.dp),
    padding: PaddingValues = PaddingValues(DefaultButtonValues.PADDING.dp),
) {
    MaterialTextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        contentPadding = padding,
        content = {
            SharedButtonContent(
                text = text, iconRes = iconRes, contentColor = contentColor, padding = DefaultButtonValues.PADDING
            )
        },
    )
}

@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    TextButton()
}
