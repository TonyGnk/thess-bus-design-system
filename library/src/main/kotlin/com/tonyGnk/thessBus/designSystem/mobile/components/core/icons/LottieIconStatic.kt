package com.tonyGnk.thessBus.designSystem.mobile.components.core.icons

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme


@Composable
fun LottieIconStatic(
    lottieComposition: LottieComposition?,
    @FloatRange(from = 0.0, to = 1.0) progress: Float,
    size: Int = 160,
    animatedColor: Color = Color.Black
) {
    val colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
        animatedColor.hashCode(),
        BlendModeCompat.SRC_ATOP
    )

    LottieAnimation(
        composition = lottieComposition,
        progress = progress,
        dynamicProperties = rememberLottieDynamicProperties(
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR_FILTER,
                value = colorFilter,
                keyPath = arrayOf("**")
            ),
        ),
        modifier = Modifier.size(size.dp)
    )
}

@AppPreview.Brightness
@Composable
fun LottieIconStaticPreview() = ClpTheme {

    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.location_pin)
    )

    val progress by animateLottieCompositionAsState(
        composition = lottieComposition,
        isPlaying = true,
        restartOnPlay = true,
    )

    LottieIconStatic(
        lottieComposition = lottieComposition,
        progress = progress,
    )
}
