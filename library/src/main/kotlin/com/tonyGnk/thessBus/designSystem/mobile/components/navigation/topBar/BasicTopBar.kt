package com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize


data class TopBarBackIcon(
    @DrawableRes val iconRes: Int = R.drawable.back,
    @StringRes val contentDescription: Int = R.string.back,
    val modifier: Modifier = Modifier,
    val onBack: () -> Unit = {}
)


@Composable
fun BasicTopBar(
    modifier: Modifier = Modifier,
    label: String = "",
    backIcon: TopBarBackIcon? = null,
    rightContent: TopBarBackIcon? = null,
    applyHorizontalPadding: Boolean = true
) {
    val labelStyle: TextStyle = AppTypo.topBar
    val textForCalculations = "Q"
    val iconHeight = textForCalculations.findScreenSize(labelStyle).height - 1.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
        modifier = if (applyHorizontalPadding) modifier.padding(
            horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
        ) else Modifier
    ) {
        if (backIcon != null) IconButton(
            iconRes = backIcon.iconRes,
            onClick = backIcon.onBack,
            contentDescription = stringResource(backIcon.contentDescription),
            modifier = Modifier.size(iconHeight)
        )
        Text(
            text = label,
            style = labelStyle.copy(color = AppColor.onSurface)
        )
        Spacer(modifier = Modifier.weight(1f))
        when (rightContent) {
            is TopBarBackIcon -> IconButton(
                iconRes = rightContent.iconRes,
                onClick = rightContent.onBack,
                contentDescription = stringResource(rightContent.contentDescription),
                modifier = rightContent.modifier.size(iconHeight)
            )

            null -> {}
        }
    }
}

@Composable
fun CenteredTopBar(
    modifier: Modifier = Modifier,
    leftContent: @Composable () -> Unit = {},
    centerContent: @Composable () -> Unit = {},
    rightContent: @Composable () -> Unit = {}
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
        modifier = modifier.padding(horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp)
    ) {
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.weight(1f)) {
            leftContent()
        }
        centerContent()
        Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.weight(1f)) {
            rightContent()
        }
    }
}

@Composable
@AppPreview.Dark
private fun Preview() = ThessBusTheme {
    Column {
        BasicTopBar(
            label = "Alarms",
            backIcon = TopBarBackIcon()
        )
        BasicTopBar(label = "Alarms")
        BasicTopBar(backIcon = TopBarBackIcon())
        CenteredTopBar(
            centerContent = {
                Text(text = "Alarms", style = AppTypo.topBar)
            },
            leftContent = {
                IconButton(
                    iconRes = AppIcon.Search.iconRes,
                    onClick = {},
                    contentDescription = "Search"
                )
            },
            rightContent = {
                IconButton(
                    iconRes = AppIcon.Search.iconRes,
                    onClick = {},
                    contentDescription = "Search"
                )
            }
        )
    }
}
