package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTargetFunctions
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.TransitionType
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement


@Composable
fun DirectionsPreviewFeatureScreen(
    onBack: () -> Unit = {},
) {
    CompositionLocalProvider(
        LocalDensity provides LocalDensity.providesDefault(Density(density = 2.9f)).value
    ) {
        DirectionsPickTarget(
            modifier = Modifier.mySharedElement("zoom", type = TransitionType.Bound),
            horizontalPadding = PaddingValues(
                horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
            ),
            verticalPadding = PaddingValues(
                top = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
            ),
            functions = DirectionsPickTargetFunctions(
                onResultClick = { },
                onSearchIme = { },
                onBack = { }
            ),
            textState = rememberTextFieldState()
        )
    }

}


@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    DirectionsPreviewFeatureScreen()
}
