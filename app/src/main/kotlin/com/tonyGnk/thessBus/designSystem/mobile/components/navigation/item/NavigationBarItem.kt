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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import com.tonyGnk.thessBus.designSystem.mobile.utils.Size
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import androidx.compose.material3.NavigationBarItem as MaterialNavigationBarItem

@Composable
fun RowScope.NavigationBarItem(
    animationResource: Int,
    radius: Dp,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    itemLabel: String = "Label",
    onItemClick: () -> Unit = {}
) {
    val size = itemLabel.findScreenSize(
        textStyle = LocalTextStyle.current.copy(
            fontSize = 13.dp.toSp(),
            lineHeight = 13.dp.toSp(),
            fontWeight = FontWeight.Black
        )
    )

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

    val itemColor =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
    val animatedItemColor by animateColorAsState(itemColor, label = "itemColorAnimation")
    val colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
        animatedItemColor.hashCode(),
        BlendModeCompat.SRC_ATOP
    )

    MaterialNavigationBarItem(
        modifier = modifier.clip(RoundedCornerShape(radius)),
        selected = isSelected,
        onClick = {
            if (!isAnimationPlaying) {
                isAnimationPlaying = true
                shouldRestartAnimation = true
            }
            onItemClick()
        },
        colors = NavigationBarItemColors(
            selectedIndicatorColor = AppColor.transparent,
            selectedIconColor = AppColor.transparent,
            unselectedIconColor = AppColor.transparent,
            unselectedTextColor = AppColor.transparent,
            disabledIconColor = AppColor.transparent,
            disabledTextColor = AppColor.transparent,
            selectedTextColor = AppColor.transparent,
        ),
        icon = {
            IconColumn(
                lottieComposition = lottieComposition,
                animationProgress = animationProgress,
                colorFilter = colorFilter,
                itemLabel = itemLabel,
                isSelected = isSelected,
                size = size,
                animatedItemColor = animatedItemColor
            )
        }
    )
}

private const val WIDTH_OF_HOVER_AREA = 68// 88
private const val STANDARD_NAVIGATION_BAR_HEIGHT = 80
private const val LOTTIE_ANIMATION_SIZE = 36
private const val SPACE_BETWEEN_ICON_AND_LABEL = 2


@Composable
private fun IconColumn(
    lottieComposition: LottieComposition?,
    animationProgress: Float,
    colorFilter: android.graphics.ColorFilter?,
    itemLabel: String,
    isSelected: Boolean,
    size: Size,
    animatedItemColor: Color
) {
    Column(
//        modifier = Modifier.size(WIDTH_OF_HOVER_AREA.dp, STANDARD_NAVIGATION_BAR_HEIGHT.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(STANDARD_NAVIGATION_BAR_HEIGHT.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = lottieComposition,
            progress = animationProgress,
            dynamicProperties = rememberLottieDynamicProperties(
                rememberLottieDynamicProperty(
                    property = LottieProperty.COLOR_FILTER,
                    value = colorFilter,
                    keyPath = arrayOf("**")
                ),
            ),
            modifier = Modifier.size(LOTTIE_ANIMATION_SIZE.dp)
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
