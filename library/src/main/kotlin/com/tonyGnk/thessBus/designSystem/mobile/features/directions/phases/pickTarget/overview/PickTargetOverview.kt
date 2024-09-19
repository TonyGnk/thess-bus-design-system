package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.TonalButton
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeFavorites
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeHistory
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.searchMode.PickTargetResult
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme

@Composable
internal fun PickTargetOverview(
    modifier: Modifier = Modifier,
    state: LazyListState,
    onCategoriesClick: () -> Unit,
    favorites: List<DirectionsFeatureItemType.SingleItem> = PickTargetFakeFavorites,
    history: List<DirectionsFeatureItemType.SingleItem> = PickTargetFakeHistory,
    horizontalPadding: PaddingValues,
    onItemClick: (DirectionsFeatureItemType.SingleItem) -> Unit
) {
    LazyColumn(
        state = state,
        modifier = modifier.padding(top = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            QuickActions(
                onCategoriesClick = onCategoriesClick,
                horizontalPadding = horizontalPadding
            )
        }
        item {
            Favorites(
                modifier = Modifier.padding(horizontalPadding),
                label = "Saved Places",
                items = favorites,
                onItemClick = onItemClick
            )
        }
        item {
            Favorites(
                modifier = Modifier.padding(horizontalPadding),
                label = "Recent", items = history,
                onItemClick = onItemClick,
            )
        }
    }
}


@Composable
fun QuickActions(
    modifier: Modifier = Modifier,
    onCategoriesClick: () -> Unit,
    horizontalPadding: PaddingValues
) {
    LazyRow(
        contentPadding = horizontalPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            TonalButton(
                iconRes = AppIcon.mapMarker,
                text = "Επιλέξτε",
                padding = PaddingValues(18.dp),
            )
        }
        item {
            TonalButton(
                iconRes = AppIcon.routes,
                text = "Διαδρομές",
                padding = PaddingValues(18.dp),
            )
        }
        item {
            TonalButton(
                iconRes = AppIcon.sign,
                text = "Στάσεις",
                padding = PaddingValues(18.dp),
            )
        }
        item {
            TonalButton(
                iconRes = AppIcon.category,
                text = "Κατηγορίες",
                onClick = onCategoriesClick,
                padding = PaddingValues(18.dp),
            )
        }
    }
}

@Composable
fun Favorites(
    modifier: Modifier = Modifier,
    label: String,
    items: List<DirectionsFeatureItemType.SingleItem>,
    onItemClick: (DirectionsFeatureItemType.SingleItem) -> Unit = {}
) {

    Column(
        modifier = modifier.padding(bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = AppTypo.titleMedium)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            items.forEach { item ->
                PickTargetResult(
                    result = item, onClick = {
                        onItemClick(item)
                    }
                )
            }
        }
    }
}

@AppPreview.Dark
@Composable
private fun Preview() = ClpTheme {
    PickTargetOverview(
        horizontalPadding = PaddingValues(0.dp),
        state = rememberLazyListState(),
        onItemClick = {},
        onCategoriesClick = {}
    )
}
