package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.utils.add
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedWindowInsets
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R

private const val ARRANGEMENT = 12

@Composable
fun ColorsGridPage(onBack: () -> Unit = {}) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(ARRANGEMENT.dp),
        contentPadding = extendedWindowInsets.add(bottom = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
    ) {
        item {
            BasicTopBar(
                applyHorizontalPadding = false,
                label = stringResource(R.string.landing_destinations_colors),
                backIcon = TopBarBackIcon(
                    onBack = onBack
                )
            )
        }
        item {
            FlowRowOfPrimaryColors()
        }
        item {
            FlowRowOfBackgroundColors()
        }
        item {
            FlowRowOfOnBackgroundColors()
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowOfPrimaryColors() {
    val averageWordSize = "On Secondary Cont"
    val maxWidth = averageWordSize.findScreenSize(AppTypo.labelLarge).width
    FlowRow(
        maxItemsInEachRow = 4,
        horizontalArrangement = Arrangement.spacedBy(ARRANGEMENT.dp),
        verticalArrangement = Arrangement.spacedBy(ARRANGEMENT.dp),
    ) {
        //Primary
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.primary to "Primary",
            onColor = AppColor.onPrimary,
            onColorLabel = "On Primary"
        )

        //Secondary
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.secondary to "Secondary",
            onColor = AppColor.onSecondary,
            onColorLabel = "On Secondary"
        )
        //Primary Container
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.primaryContainer to "Primary Container",
            onColor = AppColor.onPrimaryContainer,
            onColorLabel = "On Primary Container"
        )
        //Secondary Container
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.secondaryContainer to "Secondary Container",
            onColor = AppColor.onSecondaryContainer,
            onColorLabel = "On Secondary Container"
        )
        //Tertiary
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.tertiary to "Tertiary",
            onColor = AppColor.onTertiary,
            onColorLabel = "On Tertiary"
        )
        //Error
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.error to "Error",
            onColor = AppColor.onError,
            onColorLabel = "On Error"
        )
        //Tertiary Container
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.tertiaryContainer to "Tertiary Container",
            onColor = AppColor.onTertiaryContainer,
            onColorLabel = "On Tertiary Container"
        )
        //Error Container
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.errorContainer to "Error Container",
            onColor = AppColor.onErrorContainer,
            onColorLabel = "On Error Container"
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowOfBackgroundColors() {
    val averageWordSize = "Surface Lowest"
    val maxWidth = averageWordSize.findScreenSize(AppTypo.labelLarge).width

    FlowRow(
        maxItemsInEachRow = 5,
        horizontalArrangement = Arrangement.spacedBy(ARRANGEMENT.dp),
        verticalArrangement = Arrangement.spacedBy(ARRANGEMENT.dp),
    ) {
        //Surface Lowest
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.surfaceLowest to "Lowest Surface",
            onColor = AppColor.onSurface,
        )

        //Surface Low
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.surfaceLow to "Low Surface",
            onColor = AppColor.onSurface,
        )

        //Surface Low
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.surface to "Surface",
            onColor = AppColor.onSurface,
        )

        //Background
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.background to "Background",
            onColor = AppColor.onSurface,
        )

        //On Surface
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.onSurface to "On Surface",
            onColor = AppColor.surfaceLowest,
        )

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowOfOnBackgroundColors() {
    val averageWordSize = "Orange"
    val maxWidth = averageWordSize.findScreenSize(AppTypo.labelLarge).width

    FlowRow(
        maxItemsInEachRow = 4,
        horizontalArrangement = Arrangement.spacedBy(ARRANGEMENT.dp),
        verticalArrangement = Arrangement.spacedBy(ARRANGEMENT.dp),
    ) {
        //Red
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.red to "Red",
            onColor = Color.White,
        )

        //Orange
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.orange to "Orange",
            onColor = Color.White,
        )

        //Green
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.green to "Green",
            onColor = Color.White,
        )

        //Blue
        ColorContainer(
            modifier = Modifier
                .widthIn(min = maxWidth)
                .weight(1f, true),
            colorPair = AppColor.blue to "Blue",
            onColor = Color.White,
        )
    }
}


@Composable
private fun ColorContainer(
    modifier: Modifier,
    colorPair: Pair<Color, String>,
    onColor: Color,
    onColorLabel: String? = null
) {
    SurfaceWithShadows(
        onClick = {},
        contentColor = AppColor.onSurface,
        modifier = modifier,
        shape = AppShape.round20,
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorPair.first)
                    .padding(
                        vertical = (2 * ARRANGEMENT).dp,
                        horizontal = ARRANGEMENT.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = colorPair.second,
                    textAlign = TextAlign.Center,
                    softWrap = false,
                    style = AppTypo.labelLarge.copy(
                        color = onColor
                    )
                )
            }
            if (onColorLabel != null) Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = onColor)
                    .padding(ARRANGEMENT.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = onColorLabel,
                    textAlign = TextAlign.Center,
                    softWrap = false,
                    style = AppTypo.labelLarge.copy(
                        color = colorPair.first
                    )
                )
            }
        }
    }
}
