package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components.actions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.ButtonType
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.FilledButton
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.TextButton
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.TonalButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.add
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getExtendedWindowInsets
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R


private const val ARRANGEMENT = 11

@Composable
fun ComponentsActionsButtonPage(onBack: () -> Unit = {}) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(ARRANGEMENT.dp),
        contentPadding = getExtendedWindowInsets().add(bottom = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
    ) {
        item {
            BasicTopBar(
                applyHorizontalPadding = false,
                label = stringResource(R.string.components_actions_buttons),
                backIcon = TopBarBackIcon(
                    onBack = onBack
                )
            )
        }
        item {
            FilledButtonsFlowRow(ButtonType.Filled)
        }
        item {
            FilledButtonsFlowRow(ButtonType.Text)
        }
        item {
            FilledButtonsFlowRow(ButtonType.Tonal)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilledButtonsFlowRow(type: ButtonType) {
    val buttonsHorizontalPadding = remember { 16.dp }
    val buttonsVerticalPadding = remember { 16.dp }
    val averageWordSize = "Filled Button"
    val maxWidth =
        averageWordSize.findScreenSize(AppTypo.labelLarge).width + buttonsHorizontalPadding * 2

    FlowRow(
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.spacedBy(ARRANGEMENT.dp),
        verticalArrangement = Arrangement.spacedBy(ARRANGEMENT.dp),
    ) {
        val buttonModifier = Modifier
            .widthIn(min = maxWidth)
            .weight(1f, true)
        val textIcon = "Icon"
        val textDisabled = "Disabled"
        val buttonsPadding = PaddingValues(
            horizontal = buttonsHorizontalPadding,
            vertical = buttonsVerticalPadding
        )

        when (type) {
            ButtonType.Filled -> {
                FilledButton(
                    text = "Filled Button",
                    padding = buttonsPadding,
                    modifier = buttonModifier,
                )
                FilledButton(
                    text = textIcon,
                    padding = buttonsPadding,
                    iconRes = com.tonyGnk.thessBus.designSystem.mobile.R.drawable.add,
                    modifier = buttonModifier,
                )
                FilledButton(
                    text = textDisabled,
                    enabled = false,
                    padding = buttonsPadding,
                    modifier = buttonModifier,
                )
            }

            ButtonType.Text -> {
                TextButton(
                    text = "Text Button",
                    padding = buttonsPadding,
                    modifier = buttonModifier,
                )
                TextButton(
                    text = textIcon,
                    padding = buttonsPadding,
                    iconRes = com.tonyGnk.thessBus.designSystem.mobile.R.drawable.add,
                    modifier = buttonModifier,
                )
                TextButton(
                    text = textDisabled,
                    enabled = false,
                    padding = buttonsPadding,
                    modifier = buttonModifier,
                )
            }

            ButtonType.Tonal -> {
                TonalButton(
                    text = "Tonal Button",
                    padding = buttonsPadding,
                    modifier = buttonModifier,
                )
                TonalButton(
                    text = textIcon,
                    padding = buttonsPadding,
                    iconRes = com.tonyGnk.thessBus.designSystem.mobile.R.drawable.add,
                    modifier = buttonModifier,
                )
                TonalButton(
                    text = textDisabled,
                    enabled = false,
                    padding = buttonsPadding,
                    modifier = buttonModifier,
                )
            }
        }
    }
}
