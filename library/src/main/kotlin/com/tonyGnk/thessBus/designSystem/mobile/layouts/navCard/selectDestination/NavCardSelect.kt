package com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.data.NavCardSelectItem
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.data.NavCardSelectItemFakeData
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.overview.NavCardSelectQuickOptions
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.searchMode.NavCardSelectMode
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme

@Composable
fun NavCardSelect(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    query: String,
    isFocused: Boolean,
    historyItems: List<NavCardSelectItem> = emptyList(),
    isDetailedResults: Boolean,
    results: List<NavCardSelectItem> = NavCardSelectItemFakeData,
    searchEnabled: Boolean,
    onQueryChange: (String) -> Unit = {},
    onItemSelect: (NavCardSelectItem) -> Unit = {},
) {
    val searchStyle = AppTypo.titleMedium.copy(color = AppColor.onSurface)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SearchFieldContainer(
            query = query,
            onBackClick = onBackClick,
            onQueryChange = onQueryChange,
            searchEnabled = searchEnabled,
            searchStyle = searchStyle,
            isFocused = isFocused
        )
        AnimatedContent(targetState = searchEnabled, label = "") {
            when (it) {
                true -> NavCardSelectMode(results = results, detailedView = isDetailedResults)
                false -> NavCardSelectOverview(
                    historyItems = historyItems
                )
            }
        }
    }
}

@Composable
fun NavCardSelectOverview(
    historyItems: List<NavCardSelectItem>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(NavCardProperties.SMALL_CORNERS.dp)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { NavCardSelectQuickOptions() }
        //item { HistoryList() }
    }
}


@AppPreview.ScaleAndFont
@Composable
private fun Preview() = ClpTheme {
    val query = remember { mutableStateOf("") }
    val searchEnabled by remember {
        derivedStateOf { query.value.isNotBlank() }
    }

    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        NavCardSelect(
            query = query.value,
            onQueryChange = { query.value = it },
            searchEnabled = searchEnabled,
            isFocused = false,
            isDetailedResults = false
        )
    }
}
