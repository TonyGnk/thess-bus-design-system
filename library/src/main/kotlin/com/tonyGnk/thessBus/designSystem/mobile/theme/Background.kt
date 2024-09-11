package com.tonyGnk.thessBus.designSystem.mobile.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor


@Composable
fun ClsBackground(
    modifier: Modifier = Modifier,
    //fillMaxSize: Boolean = true,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            //  .then(if (fillMaxSize) Modifier.fillMaxSize() else Modifier)

            .background(AppColor.surfaceContainer)
    ) {
        content()
    }
}
