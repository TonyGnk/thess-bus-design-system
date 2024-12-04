package com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.overview

import android.content.Intent
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.FilledButton
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.TonalButton
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar.SearchBar
import com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar.SearchBarType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.data.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.map.LocationsLookTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.extendedWindowInsets


@Composable
fun DestinationOverviewUiLayer(
    modifier: Modifier = Modifier,
    items: LocationsLookTargetItems
) {
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .padding(items.paddingValues)
//            .then(
//                if (items.applySystemBarPadding) Modifier
//                    .extendedWindowInsets() else Modifier
//            ),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
////        Box(
////            modifier = modifier.padding(
////                horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
////            )
////        ) {
////            SearchButton(
////                searchLabel = items.query,
////                onClick = {
////                    items.goToPickTargetResults()
////                },
////                color = AppColor.surfaceLowest,
////                rippleColor = AppColor.onSurface,
////                sharedElementTag = "",
////                sharedElementTextTag = items.sharedElementText,
////            )
////        }
////        SearchBar(
////            modifier = Modifier.padding(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
////            type = SearchBarType.Static(
////                text = items.query,
////                alternativeText = "Search here",
////                onTextClick = items.goToPickTargetResults
////            ),
////        )
//        Spacer(Modifier.weight(1f))
//        // ItemDetails(items = items)
//    }
}


@Composable
private fun ItemDetails(items: LocationsLookTargetItems) {
    AnimatedContent(
        items.pickedItem, label = "",
    ) {
        when (it) {
            is DirectionsFeatureItemType.JustMap -> Box(
                Modifier
                    .fillMaxSize()
                    .height(100.dp)
            )

            is DirectionsFeatureItemType.MultipleItems -> {}
            is DirectionsFeatureItemType.Point -> {
//                PoiCard(
//                    onClose = { items.onPickItem(DirectionsFeatureItemType.JustMap) },
//                    poiTitle = it.title,
//                    onNavigate = { items.onNavigate(it) },
//                    poiCategory = it.subTitle
//                )
            }
        }
    }
}


@Composable
@AppPreview.Dark
private fun Preview() = ThessBusTheme {
    DestinationOverviewUiLayer(items = LocationsLookTargetItems.preview)
}
