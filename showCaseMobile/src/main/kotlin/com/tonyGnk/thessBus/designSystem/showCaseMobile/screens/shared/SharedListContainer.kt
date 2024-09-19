package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.IconWithTextRow
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Surface
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows


@Composable
fun SharedListContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        color = AppColor.surfaceContainerLowest,
        shadowElevation = 1.dp,
        shape = AppShape.round30,
        modifier = modifier.padding(1.dp)
    ) {
        Column {
            content()
        }
    }
}

@Composable
fun LandingUnknown(
    text: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit
) {
    LandingListItem(
        text = text,
        iconRes = iconRes,
        onClick = onClick,
    )
}

@Composable
private fun LandingListItem(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit
) {
    val paddingValues = DefaultScaffoldValues.NORMAL_BEZEL_PADDING
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
