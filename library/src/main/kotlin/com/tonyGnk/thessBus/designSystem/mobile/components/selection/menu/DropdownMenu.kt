package com.tonyGnk.thessBus.designSystem.mobile.components.selection.menu

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.window.PopupProperties
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.IconWithTextRow
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import androidx.compose.material3.DropdownMenuItem as MaterialDropdownMenuItem

private const val H_PADDING = 13
private const val TEXT_ICON_SPACING = 26

@Composable
fun DropdownMenu(
    expanded: Boolean = true,
    sizeOfTheMenuItem: Dp,
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
    val shape = AppShape.round15
    val containerColor = AppColor.surfaceLowest.copy(alpha = 0.7f)

    DropdownMenuNoPaddingVertical(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        offset = offset,
//        .copy(
//            x = max(if (offset.x > 50.dp) offset.x - 50.dp else offset.x, (-15).dp),
//            y = offset.y
//        ),
        properties = properties,
        shape = shape,
        content = content,
    )
}

@Composable
fun DropdownMenuItem(
    text: String = "Unnamed Option",
    onClick: () -> Unit,
    makeItRed: Boolean = false,
    modifier: Modifier = Modifier,
    sizeOfTheMenuItem: Dp,
    trailingIconRes: Int? = null,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    interactionSource: MutableInteractionSource? = null
) {
    val contentColor = if (makeItRed) AppColor.error else AppColor.onSurface

    MaterialDropdownMenuItem(
        text = {
            IconWithTextRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = H_PADDING.dp),
                text = text,
                iconLeft = false,
                iconRes = trailingIconRes ?: 0,
                contentColor = contentColor,
                style = AppTypo.bodyMedium.copy(color = contentColor),
                arrangement = Arrangement.SpaceBetween,
            )
        },
        onClick = onClick,
        modifier = modifier.width(sizeOfTheMenuItem),
        enabled = enabled,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    )
}

@Composable
fun findSizeOfMenuItem(listOfOptions: List<String>): Dp {
    val maxWord = listOfOptions.maxOf { it }
    val maxWordSize = maxWord.findScreenSize(AppTypo.bodySmall)
    val spacing = TEXT_ICON_SPACING * 2
    val hPadding = H_PADDING.dp
    val iconSize = maxWordSize.height
    return hPadding + maxWordSize.width + spacing.dp + iconSize + hPadding
}

@Composable
@AppPreview.Dark
private fun Preview() = ThessBusTheme {
    val isMenuOpen = remember { mutableStateOf(false) }

//    Column(
//        Modifier.fillMaxSize()
//    ) {
//        Box {
//            FilledButton(
//                onClick = { isMenuOpen.value = !isMenuOpen.value },
//            )
//         if(isMenuOpen.value)   DropdownMenu(
//                expanded = isMenuOpen.value,
//                onDismissRequest = { isMenuOpen.value = false }
//            ) {
    DropdownMenuItem(
        onClick = {
            // Handle item 1 click

        },
        text = "Ξεκαρφίτσωμα",
        enabled = true,
        sizeOfTheMenuItem = 200.dp
    )
    //    }
//        }
//    }
}
