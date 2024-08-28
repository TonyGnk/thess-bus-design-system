package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.layoutScreens.navCardScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.NavigationCardPreview
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.R
import kotlinx.serialization.Serializable

@Serializable
data object NavCardPreviewPageRoute

@Composable
fun NavCardPreviewPage(
    onBack: () -> Unit = {},
) {
    Scaffold {
        Column(
            verticalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.NORMAL_MARGIN.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            BasicTopBar(
                modifier = Modifier.padding(it),
                backIcon = TopBarBackIcon(
                    iconRes = R.drawable.circle_xmark,
                    onBack = onBack
                )
            )

            NavigationCardPreview(
                modifier = Modifier
                    .weight(1f)
                    .padding(DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),

                isDetailedResultView = false
            )
        }
    }
}


@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    NavCardPreviewPage()
}
