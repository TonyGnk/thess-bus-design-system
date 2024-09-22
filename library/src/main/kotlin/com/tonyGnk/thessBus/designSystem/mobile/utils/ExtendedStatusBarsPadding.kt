package com.tonyGnk.thessBus.designSystem.mobile.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

fun Modifier.extendedWindowInsets() = composed {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()
    val navigationBarPadding = WindowInsets.navigationBars.asPaddingValues()

    this.padding(
        top = statusBarPadding.calculateTopPadding()
                + if (statusBarPadding.calculateTopPadding() > 0.dp) 3.dp else 0.dp,
        bottom = navigationBarPadding.calculateBottomPadding()
    )
}

val extendedWindowInsets: PaddingValues
    @Composable
    get() {
        val statusBarPadding = WindowInsets.systemBars.asPaddingValues()
        val top = statusBarPadding.calculateTopPadding()
        val start = statusBarPadding.calculateStartPadding(
            layoutDirection = LayoutDirection.Ltr
        )
        val end = statusBarPadding.calculateStartPadding(
            layoutDirection = LayoutDirection.Rtl
        )
        val bottom = statusBarPadding.calculateBottomPadding()

        return PaddingValues(
            top = top + if (top > 0.dp) 3.dp else 0.dp,
            start = start,
            end = end,
            bottom = bottom
        )
    }
