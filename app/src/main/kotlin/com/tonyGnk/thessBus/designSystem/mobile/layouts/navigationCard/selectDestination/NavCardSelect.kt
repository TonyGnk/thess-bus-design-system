package com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.selectDestination

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
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.selectDestination.data.NavCardSelectItem
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme

@Composable
fun NavCardSelect(
    onBackClick: () -> Unit = {},
    query: String,
    historyItems: List<NavCardSelectItem> = emptyList(),
    results: List<NavCardSelectItem> = listOf(
        NavCardSelectItem(
            id = 1,
            elName = "Ξηροκρήνη",
            enName = "Xirokrini",
            elArea = "Θεσσαλονίκη",
            enArea = "Thessaloniki",
            x = 40.640063,
            y = 21.935731
        ),

        NavCardSelectItem(
            id = 2,
            elName = "Ωραιοπούλου",
            enName = "Oraiopoulou",
            elArea = "Θεσσαλονίκη",
            enArea = "Thessaloniki"
        ),
        NavCardSelectItem(
            id = 3,
            elName = "Ταβέρνα Άσυλο",
            enName = "Taverna Asilo",
            elArea = "Πανεπσιτημίου, Άγιος Παύλος",
            enArea = "Panepistimiou, Agios Pavlos"
        ),
    ),
    searchEnabled: Boolean,
    onQueryChange: (String) -> Unit = {},
    onItemSelect: (NavCardSelectItem) -> Unit = {},
) {
    val searchStyle = AppTypo.titleMedium.copy(color = AppColor.onSurface)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SearchFieldContainer(
            query = query,
            onBackClick = onBackClick,
            onQueryChange = onQueryChange,
            searchEnabled = searchEnabled,
            searchStyle = searchStyle
        )
        AnimatedContent(targetState = searchEnabled, label = "") {
            when (it) {
                true -> NavCardSelectSearch(results = results)
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
        item { HistoryList2() }
        item { HistoryList() }
    }
}


@AppPreview.Dark
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
            searchEnabled = searchEnabled
        )
    }
}
