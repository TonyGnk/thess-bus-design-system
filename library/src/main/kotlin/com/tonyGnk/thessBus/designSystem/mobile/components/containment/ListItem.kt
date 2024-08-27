package com.tonyGnk.thessBus.designSystem.mobile.components.containment


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import androidx.compose.material3.ListItem as MaterialListItem

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    shape: Shape = AppShape.round30,
    onClick: (() -> Unit)? = null,
    tonalElevation: Dp = 0.dp,
    padding: PaddingValues = PaddingValues(10.dp),
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    headlineContent: @Composable () -> Unit = {},
) {
    MaterialListItem(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = containerColor,
                shape = shape
            )
            .clip(shape)

            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            )
            .padding(padding),
        headlineContent = headlineContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        shadowElevation = 0.dp,
        tonalElevation = tonalElevation,
        colors = ListItemDefaults.colors(
            containerColor = AppColor.transparent,
        ),
    )
}

@Composable
fun ListItemSurface(
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    shape: Shape = AppShape.round30,
    onClick: (() -> Unit)? = null,
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    padding: PaddingValues = PaddingValues(10.dp),
    headlineContent: @Composable () -> Unit = {},
) {

    Surface(
        modifier = modifier
            .clip(shape)
            .zIndex(1f)
            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            ),
        content = {
            Box(
                modifier = Modifier.padding(padding)
            ) {
                headlineContent()
            }
        },
        shadowElevation = shadowElevation,
        tonalElevation = tonalElevation,
        color = color,
        shape = shape
    )
}
