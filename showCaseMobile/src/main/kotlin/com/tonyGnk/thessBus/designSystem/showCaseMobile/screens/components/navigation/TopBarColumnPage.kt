package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarLazyColumn
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarLazyColumnLeftSide
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarLazyColumnRightSide
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R


@Composable
fun TopBarColumnPage(
    onBack: () -> Unit = {}
) {
    TopBarLazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        leftSide = TopBarLazyColumnLeftSide(
            onClick = onBack,
            iconRes = AppIcon.Back.iconRes,
            contentDescription = "Back"
        ),
        rightSide = TopBarLazyColumnRightSide(
            onClick = {},
            iconRes = AppIcon.Add.iconRes,
            contentDescription = "Nothing"
        ),
        screenName = stringResource(R.string.components_navigation_large_top_bar)
    ) {
        items(20) { item ->
            Box(
                modifier = Modifier.padding(
                    DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
                )
            ) {
                Text("Item $item")
            }
        }
    }

}

@AppPreview.Brightness
@Composable
private fun Preview() = ThessBusTheme {
    TopBarColumnPage()
}
