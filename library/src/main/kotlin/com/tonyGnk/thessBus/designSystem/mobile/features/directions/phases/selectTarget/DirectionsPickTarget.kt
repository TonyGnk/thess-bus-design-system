package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.selectTarget

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.SelectTargetItem
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.SelectTargetItemFakeData
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.selectTarget.overview.NavCardSelectQuickOptions
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.selectTarget.searchMode.PickTargetTypingMode
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement

@Stable
data class DirectionsPickTargetFunctions(
    val onBack: () -> Unit,
    val onSearch: () -> Unit,
    val onResult: (Int, Boolean) -> Unit,
    val onQueryChange: (String) -> Unit
) {
    companion object {
        val Empty = DirectionsPickTargetFunctions(
            onBack = {},
            onSearch = {},
            onResult = { _, _ -> },
            onQueryChange = {}
        )
    }
}

@Composable
fun DirectionsPickTarget(
    modifier: Modifier = Modifier,
    functions: DirectionsPickTargetFunctions,
    query: String,
    requestFocus: Boolean,
    historyList: List<SelectTargetItem> = emptyList(),
    results: List<SelectTargetItem> = SelectTargetItemFakeData,
) {

    val isTypingModeMy = query.isNotBlank()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SearchBar(
            modifier = Modifier,
            onSearchClick = functions.onSearch,
            query = query,
            onBackClick = functions.onBack,
            onQueryChange = functions.onQueryChange,
            isTypingMode = isTypingModeMy,
            requestFocus = requestFocus
        )
        AnimatedContent(targetState = isTypingModeMy, label = "") {
            when (it) {
                true -> PickTargetTypingMode(
                    results = results,
                    onResultClick = functions.onResult
                )

                false -> PickTargetOverview(
                    historyItems = historyList
                )
            }
        }
    }
}

@Composable
fun PickTargetOverview(
    historyItems: List<SelectTargetItem>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(NavCardProperties.IN_CORNERS.dp)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { NavCardSelectQuickOptions() }
        //item { HistoryList() }
    }
}


@AppPreview.Dark
@Composable
private fun Preview() = ClpTheme {
    val query = remember { mutableStateOf("") }

    val functions = remember {
        DirectionsPickTargetFunctions(
            onBack = {},
            onSearch = {},
            onResult = { _, _ -> },
            onQueryChange = { query.value = it }
        )
    }

    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        DirectionsPickTarget(
            query = query.value,
            functions = functions,
            requestFocus = false,
        )
    }
}
