package com.tonyGnk.thessBus.designSystem.mobile.components.containment

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SurfaceWithShadows(
    modifier: Modifier = Modifier,
    shape: Shape = AppShape.round30,
    color: Color = Color.Transparent,
    contentColor: Color = contentColorFor(color),
    shadowElevation: Int = 1,
    tonalElevation: Int = 0,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val hasClicked = remember { mutableStateOf(false) }

    Surface(
        shape = shape,
        color = color,
        contentColor = contentColor,
        tonalElevation = tonalElevation.dp,
        shadowElevation = shadowElevation.dp,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .then(
                    if (onClick != null || onLongClick != null)

                        Modifier.combinedClickable(
                            onClick = {
                                if (!hasClicked.value) onClick?.invoke()
                                hasClicked.value = true
                            },
                            onLongClick = onLongClick,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(color = contentColor)
                        )
//                            .Modifier.clickable(
//                            onClick = {
//                                if (!hasClicked.value) onClick()
//                                hasClicked.value = true
//                            },
//                            interactionSource = remember { MutableInteractionSource() },
//                            indication = ripple(color = contentColor)
//                        )
                    else Modifier
                )
                .background(color = color)
        ) {
            content()
        }
    }
}

@Composable
@AppPreview.Brightness
private fun Preview() = ThessBusTheme {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.background(AppColor.surfaceLowest)
    ) {
        SurfaceWithShadows(
            shape = AppShape.round10,
            shadowElevation = 11,
            color = AppColor.surfaceLowest,
            onClick = {},
        ) {
            Text("Hello", modifier = Modifier.padding(4.dp))
        }
    }
}
