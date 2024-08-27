package com.tonyGnk.thessBus.designSystem.mobile.components.navigation.item

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme

private const val NAVIGATION_ITEM_FIXED_LABEL_SIZE = 13

@Composable
internal fun NavigationBarItemLabel(
    modifier: Modifier = Modifier,
    label: String = "Label",
    color: Color = AppColor.onBackground,
    selected: Boolean,
) {
    val textStyle = if (selected) {
        LocalTextStyle.current.copy(
            fontSize = NAVIGATION_ITEM_FIXED_LABEL_SIZE.dp.toSp(),
            lineHeight = NAVIGATION_ITEM_FIXED_LABEL_SIZE.dp.toSp(),
            fontWeight = FontWeight.Black
        )
    } else {
        LocalTextStyle.current.copy(
            fontSize = NAVIGATION_ITEM_FIXED_LABEL_SIZE.dp.toSp(),
            lineHeight = NAVIGATION_ITEM_FIXED_LABEL_SIZE.dp.toSp(),
            fontWeight = FontWeight.Thin
        )
    }

    val animatedTextStyle by animateTextStyleAsState(
        targetValue = textStyle,
        spring(stiffness = Spring.StiffnessLow)
    )

    Text(
        text = label,
        size = TextUnit.Unspecified, // This prevents scaling based on system settings
        style = animatedTextStyle,
        color = color,
        modifier = modifier
    )
}


@Composable
private fun animateTextStyleAsState(
    targetValue: TextStyle,
    animationSpec: AnimationSpec<Float> = spring(),
    finishedListener: ((TextStyle) -> Unit)? = null
): State<TextStyle> {

    val animation = remember { Animatable(0f) }
    var previousTextStyle by remember { mutableStateOf(targetValue) }
    var nextTextStyle by remember { mutableStateOf(targetValue) }

    val textStyleState = remember(animation.value) {
        derivedStateOf {
            lerp(previousTextStyle, nextTextStyle, animation.value)
        }
    }

    LaunchedEffect(targetValue, animationSpec) {
        previousTextStyle = textStyleState.value
        nextTextStyle = targetValue
        animation.snapTo(0f)
        animation.animateTo(1f, animationSpec)
        finishedListener?.invoke(textStyleState.value)
    }

    return textStyleState
}

@Composable
fun Dp.toSp() = with(LocalDensity.current) { toSp() }

@Composable
@Preview
private fun Preview() = ClpTheme {
    NavigationBarItemLabel(selected = false)
}
