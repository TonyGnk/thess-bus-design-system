package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.VanillaLazyColumn
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.navigationBar.NavigationBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.add
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getExtendedWindowInsets
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getStatusBarWindowInsets
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R

@Composable
fun NavigationBarPage(
    goBack: () -> Unit = {}
) {
    val selectedItem = remember { mutableIntStateOf(0) }

    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize(),
        contentPadding = getStatusBarWindowInsets(
            DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
        ),
    ) {
        item(key = "topBar") {
            BasicTopBar(
                modifier = Modifier.padding(
                    horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
                ),
                applyHorizontalPadding = false,
                label = stringResource(R.string.components_navigation_navigation_bar),
                backIcon = TopBarBackIcon(
                    onBack = goBack
                )
            )
        }
        item {
            NavigationBar(
                selected = selectedItem.intValue,
                onSelectChange = { newItem ->
                    selectedItem.intValue = newItem
                },
                enabledItems = Triple(true, true, true)
            )
        }
    }
}

@AppPreview.Brightness
@Composable
private fun Preview() = ThessBusTheme {
    NavigationBarPage()
}
