package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout.layoutScreens.navCardScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.NavigationCardPreview
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme


@Composable
fun DirectionsPreviewFeatureScreen(
    onBack: () -> Unit = {},
) {
    Scaffold {
        NavigationCardPreview(
            horizontalPadding = DefaultScaffoldValues.NORMAL_BEZEL_PADDING,
        )
    }
}


@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    DirectionsPreviewFeatureScreen()
}
