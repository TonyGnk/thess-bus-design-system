package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.IconWithTextRow
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.HorizontalDivider
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.add
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getExtendedWindowInsets
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.Components
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.ComponentsType

@Composable
fun ComponentsList(
    onBack: () -> Unit = {},
    onComponentPick: (Components) -> Unit = {}
) {
    val insidePadding = DefaultScaffoldValues.NORMAL_BEZEL_PADDING

    LazyColumn(
        contentPadding = getExtendedWindowInsets().add(bottom = insidePadding.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            BasicTopBar(
                label = stringResource(R.string.landing_destinations_components),
                modifier = Modifier.padding(bottom = insidePadding.dp),
                backIcon = TopBarBackIcon(
                    onBack = onBack
                )
            )
        }

        items(
            items = ComponentsType.entries,
            key = { it.ordinal }
        ) { type ->
            val items = Components.entries.filter { item ->
                item.componentsType == type
            }
            if (items.isNotEmpty()) SharedListContainer(
                label = stringResource(type.labelRes),
                paddingValues = insidePadding
            ) {
                items.forEachIndexed { index, destination ->
                    ListItem(
                        text = stringResource(id = destination.labelRes),
                        iconRes = 0,
                        onClick = { onComponentPick(destination) },
                        paddingValues = insidePadding
                    )
                    if (index != items.size - 1) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
private fun SharedListContainer(
    label: String,
    modifier: Modifier = Modifier,
    paddingValues: Int = DefaultScaffoldValues.NORMAL_BEZEL_PADDING,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.padding(bottom = (paddingValues * 1.5).dp),
        verticalArrangement = Arrangement.spacedBy(paddingValues.div(1.5).dp),
    ) {
        Text(
            text = label,
            style = AppTypo.labelLarge,
            modifier = Modifier.padding(horizontal = paddingValues.dp)
        )
        SurfaceWithShadows(
            color = AppColor.surfaceLowest,
            shadowElevation = 1,
            shape = AppShape.round30,
            modifier = Modifier.padding(0.dp)
        ) {
            Column { content() }
        }
    }
}

@Composable
private fun ListItem(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    paddingValues: Int = DefaultScaffoldValues.NORMAL_BEZEL_PADDING
) {
    SurfaceWithShadows(
        modifier = modifier.fillMaxWidth(),
        shape = AppShape.rectangle,
        shadowElevation = 0,
        onClick = onClick,
    ) {
        IconWithTextRow(
            text = text,
            iconRes = iconRes,
            contentColor = AppColor.onSurface,
            style = AppTypo.bodyLarge,
            arrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.padding(paddingValues.dp)
        )
    }
}
