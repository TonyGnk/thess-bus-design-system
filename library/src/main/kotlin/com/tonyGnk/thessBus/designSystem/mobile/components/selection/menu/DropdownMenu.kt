package com.tonyGnk.thessBus.designSystem.mobile.components.selection.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.FilledButton
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.extendedWindowInsets
import androidx.compose.material3.DropdownMenu as MaterialDropdownMenu


@Composable
fun DropdownMenu(
    expanded: Boolean = true,
    onDismissRequest: () -> Unit = {},
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    properties: PopupProperties = PopupProperties(focusable = true),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    scrollState: ScrollState = rememberScrollState(0),
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = AppShape.round20
    val containerColor = AppColor.surfaceLowest.copy(alpha = 0.8f)

    MaterialDropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier.clip(shape),
        offset = offset,
        scrollState = scrollState,
        properties = properties,
        shape = shape,
        containerColor = containerColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        border = border,
        content = content
    )
}


@Composable
@AppPreview.Dark
private fun Preview() = ClpTheme {
    val isMenuOpen = remember { mutableStateOf(false) }

    Column(
        Modifier
            .extendedWindowInsets()
            .fillMaxSize()
    ) {
        Box {
            FilledButton(
                onClick = { isMenuOpen.value = !isMenuOpen.value },
            )
            DropdownMenu(
                expanded = isMenuOpen.value,
                onDismissRequest = { isMenuOpen.value = false }
            ) {
                DropdownMenuItem(
                    onClick = {
                        // Handle item 1 click

                    },
                    text = { Text("Item 1") },
                    modifier = Modifier,
                    leadingIcon = {},
                    trailingIcon = {},
                    enabled = true,
                    // colors = TODO(),
                    //   contentPadding = TODO()
                )
            }
        }
    }
}
