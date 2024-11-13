package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.fragment.compose.AndroidFragment
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.map.MyLibreMap
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.shared.searchContainer.MapsFragment

@Composable
fun DirectionsOverview(
    modifier: Modifier = Modifier,
    startLocationName: String,
    startLat: Double,
    startLon: Double,
    endLocationName: String,
    endLat: Double,
    endLon: Double
) {

    val surfaceLowestAlpha = remember { Animatable(0.0f) }

//    LaunchedEffect(Unit) {
//        surfaceLowestAlpha.animateTo(0.999f, animationSpec = tween(durationMillis = 2000))
//    }

    val radialBrush = Brush.radialGradient(
        surfaceLowestAlpha.value to AppColor.surfaceLowest.copy(alpha = 0f),
        1.0f to AppColor.surfaceLowest
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

    }
    MyLibreMap(pickedItem = DirectionsFeatureItemType.JustMap)
//    Box(modifier = Modifier.fillMaxSize()) {
//        AndroidFragment<MapsFragment>(
//            modifier = Modifier.fillMaxSize()
//        )
//    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

    }


// Setup the search index (only needs to be done once)

//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .extendedWindowInsets()
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(AppColor.surfaceLowest)
//                .clip(AppShape.round30)
//                .padding(16.dp)
//
//        ) {
//            Text(text = "Start")
//            Text(text = "Name: $startLocationName")
//            Text(text = "X: $startLat")
//            Text(text = "Y: $startLon")
//        }
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(AppColor.surfaceLowest)
//                .clip(AppShape.round30)
//                .padding(16.dp)
//
//        ) {
//            Text(text = "Destination")
//            Text(text = "Name: $endLocationName")
//            Text(text = "X: $endLat")
//            Text(text = "Y: $endLon")
//        }
//        FilledButton(
//            text = "Find Route",
//            onClick = {},
//        )
//    }
}
