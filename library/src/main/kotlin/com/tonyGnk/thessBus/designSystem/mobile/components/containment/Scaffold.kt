package com.tonyGnk.thessBus.designSystem.mobile.components.containment

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import androidx.compose.material3.FabPosition as MaterialFabPosition
import androidx.compose.material3.Scaffold as MaterialScaffold

enum class FabPosition {
    Start, Center, End,
}

@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable (PaddingValues) -> Unit = {},
) {
    MaterialScaffold(
        modifier = modifier,
        containerColor = AppColor.transparent,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = map[floatingActionButtonPosition]!!,
        content = content
    )
}

private val map = mapOf(
    FabPosition.Start to MaterialFabPosition.Start,
    FabPosition.Center to MaterialFabPosition.Center,
    FabPosition.End to MaterialFabPosition.End,
)

@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    Scaffold()
}
