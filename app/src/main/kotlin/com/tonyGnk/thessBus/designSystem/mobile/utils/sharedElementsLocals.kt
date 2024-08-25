package com.tonyGnk.thessBus.designSystem.mobile.utils

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
//error("No SharedTransitionScope provided")

val LocalAnimatedContentScope = compositionLocalOf<AnimatedContentScope?> { null }
//error("No AnimatedContentScope provided")

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Modifier.mySharedElement(
    key: String,
    type: TransitionType = TransitionType.Element
): Modifier {
    val modifier = this
    val sharedScope = LocalSharedTransitionScope.current
    val animatedScope = LocalAnimatedContentScope.current

    if (sharedScope != null && animatedScope != null) {
        with(sharedScope) {
            return when (type) {
                TransitionType.Element -> modifier.sharedElement(
                    rememberSharedContentState(key = key),
                    animatedVisibilityScope = animatedScope
                )

                TransitionType.Bound -> modifier.sharedBounds(
                    rememberSharedContentState(key = key),
                    animatedVisibilityScope = animatedScope
                )
            }

        }
    } else {
        return modifier
    }
}

enum class TransitionType { Element, Bound }
