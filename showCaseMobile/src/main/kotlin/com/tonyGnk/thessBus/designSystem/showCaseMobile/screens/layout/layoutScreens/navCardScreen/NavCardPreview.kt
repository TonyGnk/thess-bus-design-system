package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.layoutScreens.navCardScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.floatingActionButtons.FloatingActionButtonAnimated
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.ListItem
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Surface
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.HorizontalDivider
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.NavigationCardPreview
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.NavCardSelect
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.start.NavCardStart
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LandingDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.LayoutDestination
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
                labelRes = R.string.landing_destinations_layout,
                backIcon = TopBarBackIcon(
                    onBack = onBack
                )
            )

            NavigationCardPreview(modifier = Modifier.weight(1f))
        }
    }
}


@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    NavCardPreviewPage()
}
