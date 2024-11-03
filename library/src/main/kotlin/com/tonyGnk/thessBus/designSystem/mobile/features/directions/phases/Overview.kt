package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.compose.AndroidFragment
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
    AndroidFragment<MapsFragment>(
        modifier = Modifier.fillMaxSize()
    )


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
