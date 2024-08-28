package com.tonyGnk.thessBus.designSystem.mobile.components.actions.floatingActionButtons

import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import androidx.compose.material3.ExtendedFloatingActionButton as MaterialExtendedFloatingActionButton

@Composable
fun FloatingActionButtonText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    containerColor: Color = AppColor.primary,
    text: String = "Floating Button",
) {
    MaterialExtendedFloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        content = { Text(text = text, color = contentColorFor(containerColor)) },
    )
}


@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    FloatingActionButtonText()
}
