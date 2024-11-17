package com.tonyGnk.thessBus.designSystem.mobile.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme

@Composable
fun ClickableWithoutRipple(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    color: Color,
    onClick: () -> Unit,
    content: @Composable (Color) -> Unit
) {
    var isHovered by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        if (isHovered) 0.3f else 1f, label = "hover"
    )

    Box(
        contentAlignment = contentAlignment,
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isHovered = true
                        if (tryAwaitRelease()) {
                            isHovered = false
                        }
                        if (!tryAwaitRelease()) {
                            isHovered = false
                        }
                    },
                    onTap = {
                        onClick()
                    }
                )
            }

    ) {
        content(color.copy(alpha = alpha))
    }
}

@Composable
@AppPreview.Dark
private fun Preview() = ThessBusTheme {
    ClickableWithoutRipple(
        color = Color.Red,
        onClick = {},
    ) {
        Text(text = "Hello", style = AppTypo.titleMedium.copy(it))
    }
}
