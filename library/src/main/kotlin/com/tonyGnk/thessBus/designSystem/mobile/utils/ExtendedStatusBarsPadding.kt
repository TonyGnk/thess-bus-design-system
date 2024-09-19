package com.tonyGnk.thessBus.designSystem.mobile.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.extendedStatusBarsPadding() = composed {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()
    val navigationBarPadding = WindowInsets.navigationBars.asPaddingValues()

    this.padding(
        top = statusBarPadding.calculateTopPadding()
                + if (statusBarPadding.calculateTopPadding() > 0.dp) 3.dp else 0.dp,
        bottom = navigationBarPadding.calculateBottomPadding()
    )
}

@Composable
fun extendedStatusBarsPadding(): Dp {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()
    return statusBarPadding.calculateTopPadding() + if (
        statusBarPadding.calculateTopPadding() > 0.dp
    ) 3.dp else 0.dp

}
