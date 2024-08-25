package com.tonyGnk.thessBus.designSystem.mobile.components.surface

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape

@Composable
fun SurfaceWithShadows(
    modifier: Modifier = Modifier,
    shape: Shape = AppShape.round30,
    color: Color = Color.Transparent,
    content: @Composable () -> Unit,
) {
    Surface(
        shape = shape,
        color = color,
        contentColor = contentColorFor(color),
        tonalElevation = 0.dp,
        shadowElevation = 1.dp,
        modifier = modifier
            .zIndex(1f)
            .padding(1.dp),
        content = content
    )
}
