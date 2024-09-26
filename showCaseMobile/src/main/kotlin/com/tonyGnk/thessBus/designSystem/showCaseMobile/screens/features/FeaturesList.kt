package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedWindowInsets
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDestination

enum class Feature(
    val subtitle: String,
    val enabled: Boolean,
    val colors: Triple<Color, Color, Color>
) {
    Locations(
        "City database with map interaction",
        true,
        Triple(Color(0xFF68AF7F), Color(0xFF56B97D), Color(0xFF87B028))
    ),
    Directions(
        "Offline and online routing",
        false,
        Triple(Color(0xFFDA9745), Color(0xFFD88672), Color(0xFFE36658))
    ),
    BusTracker(
        "Bus arrival notifications & locations",
        false,
        Triple(Color(0xFF0186D3), Color(0xFF005C8F), Color(0xFF001520))
    ),
    Alarm(
        "Set background alarms",
        false,
        Triple(Color(0xFF000000), Color(0xFF2D2D2D), Color(0xFF646464))
    ),
    Tablet(
        "Optimized for large screens",
        false,
        Triple(Color(0xFF3B4371), Color(0xFF714371), Color(0xFFF3718F))
    ),
    iOS(
        "Cross-platform support",
        false,
        Triple(Color(0xFFFF2779), Color(0xFFFE7963), Color(0xFFD7D267))
    ),
    Watch(
        "Android Wear OS client app",
        false,
        Triple(Color(0xFF08A2A2), Color(0xFF17A985), Color(0xFF1CAF87))
    );
}

private val featureToPreview: Map<Feature, FeatureDestination> = Feature.entries.associate {
    when (it) {
        Feature.Locations -> it to FeatureDestination.LocationsGraph
        Feature.Directions -> it to FeatureDestination.LocationsGraph
        Feature.BusTracker -> it to FeatureDestination.LocationsGraph
        Feature.Alarm -> it to FeatureDestination.LocationsGraph
        Feature.Tablet -> it to FeatureDestination.LocationsGraph
        Feature.iOS -> it to FeatureDestination.LocationsGraph
        Feature.Watch -> it to FeatureDestination.LocationsGraph
    }
}

@Composable
fun FeaturesList(
    onBack: () -> Unit = {},
    goTo: (FeatureDestination) -> Unit = {}
) {
    LazyColumn(
        contentPadding = extendedWindowInsets,
        verticalArrangement = Arrangement.spacedBy(
            DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
        ),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            BasicTopBar(
                label = stringResource(R.string.landing_destinations_features),
                backIcon = TopBarBackIcon(
                    onBack = onBack
                )
            )
        }

        items(Feature.entries) { feature ->
            CoolGradientBox(
                feature = feature,
                modifier = Modifier
                    .padding(
                        horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
                    )
                    .then(
                        if (feature == Feature.entries.last()) Modifier.padding(
                            bottom = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
                        ) else Modifier
                    )
                    .fillMaxWidth(),
                navigateTo = goTo,
            )
        }
    }
}

@Composable
private fun CoolGradientBox(
    feature: Feature,
    modifier: Modifier = Modifier,
    navigateTo: (FeatureDestination) -> Unit = {},
) {
    val gradientBrush = remember {
        Brush.linearGradient(
            colors = listOf(
                feature.colors.first.copy(alpha = 0.9f),
                feature.colors.second.copy(alpha = 0.9f),
                feature.colors.third.copy(alpha = 0.9f)
            )
        )
    }

    val contentColor = remember {
        if (feature.enabled) {
            Color.White
        } else {
            Color.White.copy(red = 0.6f, green = 0.6f, blue = 0.6f)
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .clip(
                AppShape.round20
            )
            .background(gradientBrush)

            .then(
                if (feature.enabled) Modifier.clickable {
                    navigateTo(featureToPreview[feature]!!)
                } else Modifier.background(
                    Color.Black.copy(
                        alpha = 0.5f
                    )
                )
            )
            .padding(20.dp)
    ) {
        Text(
            text = feature.name,
            style = AppTypo.headlineMedium.copy(
                color = contentColor
            ),
            weight = FontWeight.Black,
        )
        Text(
            text = feature.subtitle,
            style = AppTypo.bodyMedium.copy(
                color = contentColor
            ),
            weight = FontWeight.Black,
        )
    }
}

@AppPreview.Brightness
@Composable
private fun CoolGradientBoxPreview() = ClpTheme {
    CoolGradientBox(
        modifier = Modifier.fillMaxWidth(),
        feature = Feature.Watch
    )
}

@AppPreview.Brightness
@Composable
private fun Preview() = ClpTheme {
    FeaturesList()
}
