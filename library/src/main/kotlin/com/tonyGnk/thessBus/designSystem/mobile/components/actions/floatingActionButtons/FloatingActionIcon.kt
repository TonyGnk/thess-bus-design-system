package com.tonyGnk.thessBus.designSystem.mobile.components.actions.floatingActionButtons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import androidx.compose.material3.FloatingActionButton as MaterialFloatingActionButton


@Composable
fun FloatingActionIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    containerColor: Color = AppColor.primary,
    iconRes: Int = AppIcon.Search.iconRes,
) {
    var initialExtended by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        initialExtended = true
    }

    AnimatedVisibility(
        visible = initialExtended,
        enter = fadeIn() + scaleIn(
            initialScale = 0.8f,
            transformOrigin = TransformOrigin(1f, 1f)
        ),
    ) {
        MaterialFloatingActionButton(
            onClick = onClick,
            containerColor = containerColor,
            modifier = modifier.size(56.dp),
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
            content = { Icon(iconRes = iconRes) }
        )
    }

}


@AppPreview.Brightness
@Composable
private fun Preview() = ThessBusTheme {
    FloatingActionIcon()
}
