package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.lookTarget

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
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.extendedWindowInsets


@Composable
fun DestinationOverviewUiLayer(
    modifier: Modifier = Modifier,
    items: LocationsLookTargetItems
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(items.paddingValues)
            .then(
                if (items.applySystemBarPadding) Modifier
                    .extendedWindowInsets() else Modifier
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
//        Box(
//            modifier = modifier.padding(
//                horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
//            )
//        ) {
//            SearchButton(
//                searchLabel = items.query,
//                onClick = {
//                    items.goToPickTargetResults()
//                },
//                color = AppColor.surfaceLowest,
//                rippleColor = AppColor.onSurface,
//                sharedElementTag = "",
//                sharedElementTextTag = items.sharedElementText,
//            )
//        }
        SearchBar(
            modifier = Modifier.padding(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
            onSearchIme = {},
            onBackIconClick = {
                items.clearTextField()
                items.goToPickTargetResults()
            },
            type = SearchBarType.Static(
                text = items.query,
                alternativeText = "Search here",
                onTextClick = items.goToPickTargetResults
            ),
        )
        Spacer(Modifier.weight(1f))
       // ItemDetails(items = items)
    }
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
            is DirectionsFeatureItemType.SingleItem -> {
                PoiCard(
                    onClose = { items.onPickItem(DirectionsFeatureItemType.JustMap) },
                    poiTitle = it.title,
                    onNavigate = { items.onNavigate(it) },
                    poiCategory = it.subTitle
                )
            }
        }
    }
}

@Composable
private fun PoiCard(
    onClose: () -> Unit,
    onNavigate: () -> Unit = {},
    poiTitle: String = "Nova Store",
    poiCategory: String = "Εταιρεία Τηλεπικοινωνιών"
) {
    val context = LocalContext.current

    SurfaceWithShadows(
        modifier = Modifier.padding(10.dp),
        shadowElevation = 20,
        shape = RoundedCornerShape(
            33.dp
            //topStart = 33.dp, topEnd = 33.dp
        ),
        color = AppColor.surfaceLowest,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp
                )
        ) {
            PoiTextLabels(
                poiTitle = poiTitle,
                poiCategory = poiCategory,
                onClose = onClose
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    FilledButton(
                        onClick = onNavigate,
                        iconRes = AppIcon.Navigate.iconRes,
                        text = "Πλοήγηση",
                        padding = PaddingValues(17.dp),
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
                item {
                    TonalButton(
                        iconRes = R.drawable.bookmark,
                        text = "Αποθήκευση",
                        padding = PaddingValues(17.dp),
                        modifier = Modifier
                    )
                }
                item {
                    TonalButton(
                        iconRes = AppIcon.Share.iconRes,
                        text = "Κοινοποίηση",
                        onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "Δες το $poiTitle στο ThessBus!")
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        },
                        padding = PaddingValues(17.dp),
                        modifier = Modifier.padding(end = 20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun PoiTextLabels(
    poiTitle: String,
    onClose: () -> Unit,
    poiCategory: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = poiTitle,
                style = AppTypo.titleLarge,
                weight = FontWeight.Black
            )
            Text(
                text = poiCategory,
                style = AppTypo.bodyMedium,
            )
        }
        IconButton(
            onClick = onClose,
            color = AppColor.background,
            iconRes = AppIcon.Cross.iconRes,
            modifier = Modifier.size(14.dp)
        )
    }
}

@Composable
@AppPreview.Dark
private fun Preview() = ThessBusTheme {
    DestinationOverviewUiLayer(items = LocationsLookTargetItems.preview)
}
