package com.tonyGnk.thessBus.designSystem.mobile.components.navigation.item

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.LottieIconStatic


@Composable
fun RowScope.NavigationBarItem(
    animationResource: Int,
    radius: Dp,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    itemLabel: String = "Label",
    onItemClick: () -> Unit = {}
) {
    var isAnimationPlaying by remember { mutableStateOf(false) }
    var shouldRestartAnimation by remember { mutableStateOf(false) }

    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(animationResource)
    )

    val animationProgress by animateLottieCompositionAsState(
        composition = lottieComposition,
        isPlaying = isAnimationPlaying,
        restartOnPlay = shouldRestartAnimation,
    )

    LaunchedEffect(animationProgress) {
        if (animationProgress == 1f) {
            isAnimationPlaying = false
            shouldRestartAnimation = false
        }
    }

    val itemColor = if (isSelected) AppColor.primary else AppColor.outline
    val animatedItemColor by animateColorAsState(itemColor, label = "itemColorAnimation")

    NavigationBarItem(
        modifier = modifier,
        shape = RoundedCornerShape(radius),
        isSelected = isSelected,
        onClick = {
            if (!isAnimationPlaying) {
                isAnimationPlaying = true
                shouldRestartAnimation = true
            }
            onItemClick()
        },
    ) {
        IconColumn(
            lottieComposition = lottieComposition,
            animationProgress = animationProgress,
            itemLabel = itemLabel,
            isSelected = isSelected,
            animatedItemColor = animatedItemColor
        )
    }
}

private const val STANDARD_NAVIGATION_BAR_HEIGHT = 80
private const val LOTTIE_ANIMATION_SIZE = 36
private const val SPACE_BETWEEN_ICON_AND_LABEL = 2


@Composable
private fun IconColumn(
    lottieComposition: LottieComposition?,
    animationProgress: Float,
    itemLabel: String,
    isSelected: Boolean,
    animatedItemColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(STANDARD_NAVIGATION_BAR_HEIGHT.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        LottieAnimation(
//            composition = lottieComposition,
//            progress = animationProgress,
//            dynamicProperties = rememberLottieDynamicProperties(
//                rememberLottieDynamicProperty(
//                    property = LottieProperty.COLOR_FILTER,
//                    value = colorFilter,
//                    keyPath = arrayOf("**")
//                ),
//            ),
//            modifier = Modifier.size(LOTTIE_ANIMATION_SIZE.dp)
//        )
        LottieIconStatic(
            lottieComposition = lottieComposition,
            progress = animationProgress,
            animatedColor = animatedItemColor,
            size = LOTTIE_ANIMATION_SIZE
        )
        Spacer(modifier = Modifier.height(SPACE_BETWEEN_ICON_AND_LABEL.dp))
        NavigationBarItemLabel(
            label = itemLabel,
            selected = isSelected,
            color = animatedItemColor,
            modifier = Modifier
        )
    }
}
