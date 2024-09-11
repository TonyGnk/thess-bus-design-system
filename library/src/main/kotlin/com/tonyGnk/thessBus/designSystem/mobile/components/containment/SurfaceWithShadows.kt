package com.tonyGnk.thessBus.designSystem.mobile.components.containment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme

@Composable
fun SurfaceWithShadows(
    modifier: Modifier = Modifier,
    shape: Shape = AppShape.round30,
    color: Color = Color.Transparent,
    contentColor: Color = contentColorFor(color),
    shadowElevation: Int = 1,
    tonalElevation: Dp = 0.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Surface(
        shape = shape,
        color = color,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation.dp,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .then(
                    if (onClick != null) Modifier.clickable {
                        onClick()
                    } else Modifier
                )
                .background(color = color)
        ) {
            content()
        }
    }
}

@Composable
@AppPreview.Brightness
private fun Preview() = ClpTheme {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.background(AppColor.surfaceContainerLowest)
    ) {
        SurfaceWithShadows(
            shape = AppShape.round10,
            shadowElevation = 11,
            color = AppColor.surfaceContainerLowest,
            onClick = {},
        ) {
            Text("Hello", modifier = Modifier.padding(4.dp))
        }
    }
}
