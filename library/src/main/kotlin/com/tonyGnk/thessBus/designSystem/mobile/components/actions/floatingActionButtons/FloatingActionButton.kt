package com.tonyGnk.thessBus.designSystem.mobile.components.actions.floatingActionButtons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import androidx.compose.material3.FloatingActionButton as MaterialFloatingActionButton

@Composable
fun FloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    forceExtended: Boolean = true,
    containerColor: Color = AppColor.primary,
    iconRes: Int = AppIcon.search,
    text: String = "Floating Button",
) {
    MaterialFloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = containerColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                iconRes = iconRes,
                contentDescription = "",
            )
            AnimatedVisibility(
                visible = forceExtended,
            ) {
                Text(
                    text = text,
                    softWrap = false,
                    color = contentColorFor(containerColor),
                    maxLines = 1,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    val extended = remember { mutableStateOf(true) }
    FloatingActionButton(
        forceExtended = extended.value,
        onClick = { extended.value = !extended.value }
    )
}
