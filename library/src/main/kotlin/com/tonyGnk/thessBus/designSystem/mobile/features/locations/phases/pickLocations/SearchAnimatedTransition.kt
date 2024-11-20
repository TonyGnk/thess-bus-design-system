package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable

@Composable
internal inline fun SearchAnimatedTransition(
    isQueryEmpty: Boolean,
    crossinline whenNotEmpty: @Composable () -> Unit = {},
    crossinline whenEmpty: @Composable () -> Unit = {},
) {
    AnimatedContent(targetState = isQueryEmpty, label = "") {
        when (it) {
            true -> whenEmpty()

            false -> whenNotEmpty()
        }
    }
}
