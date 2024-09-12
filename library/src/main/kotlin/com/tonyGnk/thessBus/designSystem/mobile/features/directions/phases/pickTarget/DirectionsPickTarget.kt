package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.SelectTargetItem
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.SelectTargetItemFakeData
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview.MyBox
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview.NavCardSelectQuickOptions
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.searchMode.ResultLayout
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedStatusBarsPadding

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
    val lazyListState = rememberLazyListState()
    val canScrollBackward by remember {
        derivedStateOf { lazyListState.canScrollBackward }
    }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    if (requestFocus) LaunchedEffect(
        key1 = canScrollBackward,
    ) {
        if (lazyListState.canScrollBackward) {
            focusManager.clearFocus()
        } else {
            focusRequester.requestFocus()
        }
    }

    LazyColumn(
        state = lazyListState,
        modifier = modifier,
        contentPadding = PaddingValues(top = extendedStatusBarsPadding()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            SearchBar(
                onSearchClick = functions.onSearch,
                query = query,
                onBackClick = functions.onBack,
                onQueryChange = functions.onQueryChange,
                isTypingMode = isTypingModeMy,
                focusRequester = focusRequester
            )
        }
//        item {
//            AnimatedContent(targetState = isTypingModeMy, label = "") {
//                when (it) {
//                    true -> PickTargetTypingMode(
//                        results = results,
//                        onResultClick = functions.onResult
//                    )
//
//                    false -> PickTargetOverview(
//                        lazyListState = lazyListState,
//                        historyItems = historyList
//                    )
//                }
//            }
//        }

//        if (isTypingModeMy) {
//            // Show search results when typing mode is active
//            items(
//                items = results, key = { it.id }
//            ) { result ->
//                ResultLayout(
//                    modifier = Modifier.animateItem(),
//                    result = result,
//                    onClick = {
//                        functions.onResult(result.id, result.isSinglePoint)
//                    }
//                )
//            }
//        } else {
//            // Show favorites when not in typing mode
//            items(results, key = { -it.id }) { favorite ->
//                MyBox(modifier = Modifier.animateItem())
//            }
//        }

        items(
            items = results + results,
            //key = { it.id }
        ) { item ->
            AnimatedContent(
                targetState = isTypingModeMy,
                label = "item_transition"
            ) { isSearching ->
                if (isSearching && item in results) {
                    ResultLayout(
                        result = item,
                        onClick = { functions.onResult(item.id, item.isSinglePoint) }
                    )
                } else if (!isSearching && item in results) {
                    MyBox(
                    )
                }
            }
        }


//        items(items = results, key = { it.id }) { result ->
//            AnimatedContent(targetState = isTypingModeMy, label = "") {
//                when (it) {
//                    true -> ResultLayout(
//                        result = result,
//                        onClick = {
//                            functions.onResult(result.id, result.isSinglePoint)
//                        }
//                    )
//
//                    false -> {}
//                }
//            }
//        }
//        items(items = results, key = { -it.id }) { result ->
//            AnimatedContent(targetState = isTypingModeMy, label = "") {
//                when (it) {
//                    true -> {}
//
//                    false -> MyBox()
//                }
//            }
//        }
    }
}

@Composable
fun PickTargetOverview(
    lazyListState: LazyListState,
    historyItems: List<SelectTargetItem>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NavCardSelectQuickOptions(
            lazyListState = lazyListState
        )
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
