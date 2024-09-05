package com.tonyGnk.thessBus.designSystem.mobile.components.containment

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    shadowElevation: Int = 1,
    padding: PaddingValues = PaddingValues(1.dp),
    content: @Composable () -> Unit,
) {
    Surface(
        shape = shape,
        color = color,
        contentColor = contentColorFor(color),
        tonalElevation = 0.dp,
        shadowElevation = shadowElevation.dp,
        modifier = modifier
            .zIndex(1f)
            .padding(padding),
        content = content
    )
}
