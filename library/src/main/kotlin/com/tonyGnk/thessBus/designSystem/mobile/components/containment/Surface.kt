package com.tonyGnk.thessBus.designSystem.mobile.components.containment


import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import androidx.compose.material3.Surface as MaterialSurface


@Composable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = AppShape.rectangle,
    color: Color = Color.Transparent,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    content: @Composable () -> Unit,
) {
    MaterialSurface(
        shape = shape,
        color = color,
        contentColor = contentColorFor(color),
        tonalElevation = 0.dp,
        shadowElevation = shadowElevation,
        border = border,
        modifier = modifier,
        content = content
    )
}
