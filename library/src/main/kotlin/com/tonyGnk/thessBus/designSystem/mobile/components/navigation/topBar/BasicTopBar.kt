package com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize

data class TopBarBackIcon(
    @DrawableRes val iconRes: Int = R.drawable.back,
    @StringRes val contentDescription: Int = R.string.back,
    val onBack: () -> Unit = {}
)


@Composable
fun BasicTopBar(
    modifier: Modifier = Modifier,
    @StringRes labelRes: Int = 0,
    backIcon: TopBarBackIcon? = null,
) {
    val labelStyle: TextStyle = AppTypo.topBar
    val textForCalculations = "Q"
    val iconHeight = textForCalculations.findScreenSize(labelStyle).height - 1.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
        modifier = modifier.padding(horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp)
    ) {
        if (backIcon != null) IconButton(
            iconRes = backIcon.iconRes,
            onClick = backIcon.onBack,
            modifier = Modifier.size(iconHeight),
            contentDescription = stringResource(backIcon.contentDescription)
        )
        if (labelRes != 0) Text(
            text = stringResource(labelRes),
            style = labelStyle,
            color = AppColor.onSurface
        )
    }
}

@Composable
@AppPreview.Dark
private fun Preview() = ClpTheme {
    Column {
        BasicTopBar(
            labelRes = R.string.destination_alarms,
            backIcon = TopBarBackIcon()
        )
        BasicTopBar(labelRes = R.string.destination_alarms)
        BasicTopBar(backIcon = TopBarBackIcon())
    }
}
