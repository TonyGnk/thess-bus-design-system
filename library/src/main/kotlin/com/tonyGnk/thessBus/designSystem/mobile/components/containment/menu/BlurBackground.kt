package com.tonyGnk.thessBus.designSystem.mobile.components.containment.menu

import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.applyContextMenu(enable: Boolean): Modifier {
    val transition = updateTransition(targetState = enable, label = "ContextMenuTransition")

    val alpha by transition.animateFloat(
        label = "AlphaAnimation",
        transitionSpec = {
            tween(durationMillis = 200, easing = EaseOut)
        }
    ) { if (it) 0.4f else 0f }

    val blur by transition.animateDp(
        label = "BlurAnimation",
        transitionSpec = {
            tween(durationMillis = 200, easing = EaseOut)
        }
    ) { if (it) 8.dp else 0.dp }

    return this.then(
        Modifier
            .drawWithContent {
                drawContent()
                drawRect(color = Color.Black.copy(alpha = alpha))
            }
            .blur(blur)
    )
}
