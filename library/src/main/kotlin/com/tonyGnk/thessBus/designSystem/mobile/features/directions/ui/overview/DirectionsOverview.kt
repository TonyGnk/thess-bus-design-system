package com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.overview

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
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
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.extendedWindowInsets
import org.maplibre.android.geometry.LatLng

@Stable
data class DirectionsOverviewItems(
    val pickedItem: DirectionsFeatureItemType,
    val clearPickedItem: () -> Unit,
) {
    companion object {
        val preview = DirectionsOverviewItems(
            pickedItem = DirectionsFeatureItemType.JustMap,
            clearPickedItem = {}
        )
    }
}

@Composable
fun DirectionsOverview(
    goToPickTarget: () -> Unit = {},
    placeHolder: String = "",
    items: DirectionsOverviewItems = DirectionsOverviewItems.preview,
) {
    if (items.pickedItem !is DirectionsFeatureItemType.JustMap) {
        BackHandler { items.clearPickedItem() }
    }

    Column(
        modifier = Modifier.extendedWindowInsets(
            topPaddingIfNoStatusBar = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
        )
    ) {
        SearchBar(
            modifier = Modifier.padding(
                horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
            ),
            onClear = items.clearPickedItem,
            type = SearchBarType.Static(
                text = placeHolder,
                alternativeText = "Search here",
                onTextClick = goToPickTarget
            ),
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .background(AppColor.background)
                .padding(DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp)
        ) {
            when (items.pickedItem) {
                is DirectionsFeatureItemType.JustMap -> {}
                is DirectionsFeatureItemType.MultipleItems -> {}
                is DirectionsFeatureItemType.Point -> {
                    PoiCard(
                        onClose = items.clearPickedItem,
                        onNavigate = {},
                        poiTitle = items.pickedItem.title,
                        poiCategory = items.pickedItem.subTitle
                    )
                }
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
        shadowElevation = 1,
        shape = RoundedCornerShape(
            22.dp
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
