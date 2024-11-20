package com.tonyGnk.thessBus.designSystem.mobile.components.containment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.add
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getExtendedWindowInsets
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getStatusBarWindowInsets

@Composable
fun VanillaLazyColumn(
    modifier: Modifier = Modifier,
    label: String,
    goBack: () -> Unit,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    applyHorizontalPadding: Boolean = true,
    applyNavigationBarInsets: Boolean = true,
    content: LazyListScope.() -> Unit
) {
    val padding = remember { DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp }

    LazyColumn(
        verticalArrangement = verticalArrangement,
        contentPadding = if (applyNavigationBarInsets) {
            getExtendedWindowInsets(padding).add(bottom = padding)
        } else {
            getStatusBarWindowInsets(padding)
        },
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = if (applyHorizontalPadding) padding else 0.dp,
            ),
    ) {
        item(key = "topBar") {
            BasicTopBar(
                applyHorizontalPadding = !applyHorizontalPadding,
                label = label,
                backIcon = TopBarBackIcon(
                    onBack = goBack
                )
            )
        }
        content()
    }

}
