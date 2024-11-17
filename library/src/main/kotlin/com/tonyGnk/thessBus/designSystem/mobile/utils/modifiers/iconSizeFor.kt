package com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.iconSizeFor(size: Dp) = this.then(size(size - 3.dp))
