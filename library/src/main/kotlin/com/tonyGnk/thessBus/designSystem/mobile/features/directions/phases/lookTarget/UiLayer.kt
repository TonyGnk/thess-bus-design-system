package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.shared.searchContainer.SearchButton
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme


@Composable
fun DestinationOverviewUiLayer(
    onBack: () -> Unit,
    query: String,
    poiTitle: String,
    poiCategory: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.padding(horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp)) {
            SearchButton(
                searchLabel = query,
                onClick = onBack,
                color = AppColor.surfaceContainerLowest,
                rippleColor = AppColor.onSurface,
            )

        }
        Spacer(Modifier.weight(1f))
        PoiCard(
            poiTitle = poiTitle,
            poiCategory = poiCategory
        )
    }
}

@Composable
private fun PoiCard(
    poiTitle: String = "Nova Store",
    poiCategory: String = "Εταιρεία Τηλεπικοινωνιών"
) {
    SurfaceWithShadows(
        shadowElevation = 20,
        shape = RoundedCornerShape(topStart = 33.dp, topEnd = 33.dp),
        color = AppColor.surfaceContainerLowest,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(vertical = 20.dp)
        ) {
            PoiTextLabels(
                poiTitle = poiTitle,
                poiCategory = poiCategory
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    FilledButton(
                        iconRes = AppIcon.navigate,
                        text = "Πλοήγηση",
                        padding = PaddingValues(18.dp),
                        modifier = Modifier.padding(start = 18.dp)
                    )
                }
                item {
                    TonalButton(
                        iconRes = R.drawable.bookmark,
                        text = "Αποθήκευση",
                        padding = PaddingValues(18.dp),
                        modifier = Modifier
                    )
                }
                item {
                    TonalButton(
                        iconRes = AppIcon.share,
                        text = "Κοινοποίηση",
                        padding = PaddingValues(18.dp),
                        modifier = Modifier.padding(end = 18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun PoiTextLabels(
    poiTitle: String,
    poiCategory: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = poiTitle,
                style = AppTypo.headlineSmall,
            )
            Text(
                text = poiCategory,
                style = AppTypo.bodyLarge,
            )
        }
        IconButton(
            color = AppColor.surfaceContainer,
            iconRes = AppIcon.cross,
            modifier = Modifier.size(13.dp)
        )
    }
}

@Composable
@AppPreview.Dark
private fun Preview() = ClpTheme {
    PoiCard()
}
