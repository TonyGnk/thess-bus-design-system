package com.tonyGnk.thessBus.designSystem.mobile.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Stable
inline val Dp.sp
    @Composable
    get() = with(LocalDensity.current) { toSp() }
