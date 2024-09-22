package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues.NORMAL_BEZEL_PADDING
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview.DirectionsPickTargetOverview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview.DirectionsPickTargetOverviewItems
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.searchMode.LazyListOfPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedWindowInsets


@Stable
data class DirectionsPickTargetItems(
    val onBack: () -> Unit,
    val onSearchIme: () -> Unit,
    val onResultClick: (DirectionsFeatureItemType.SingleItem) -> Unit,
    val clearText: () -> Unit,
    val onCategoriesClick: () -> Unit,
    val requestFocus: Boolean,
    val applySystemBarPadding: Boolean,
    val textState: TextFieldState,
    val results: List<DirectionsFeatureItemType.SingleItem>,
) {
    companion object {
        val preview = DirectionsPickTargetItems(
            onBack = {},
            onSearchIme = {},
            onResultClick = {},
            clearText = {},
            onCategoriesClick = {},
            requestFocus = false,
            applySystemBarPadding = true,
            textState = TextFieldState(),
            results = PickTargetFakeResults,
        )
    }
}


@Composable
fun DirectionsPickTarget(
    modifier: Modifier = Modifier,
    items: DirectionsPickTargetItems = DirectionsPickTargetItems.preview,
) {
    val lazyListState = rememberLazyListState()
    val canScrollBackward by remember {
        derivedStateOf { lazyListState.canScrollBackward }
    }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    if (items.requestFocus) LaunchedEffect(
        key1 = canScrollBackward,
    ) {
        if (lazyListState.canScrollBackward) {
            // focusManager.clearFocus()
        } else {
            focusRequester.requestFocus()
        }
    }

    val padding = NORMAL_BEZEL_PADDING.dp

    val query = items.textState.text.toString()
    val emptyQuery = query.isEmpty()

    if (items.requestFocus && !emptyQuery) ClearTextOnBackPress(items.clearText)

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = if (items.applySystemBarPadding) extendedWindowInsets else PaddingValues(
            0.dp
        ),
        modifier = modifier
    ) {
        item {
            SearchBar(
                modifier = Modifier.padding(horizontal = padding),
                onSearchClick = items.onSearchIme,
                onBackClick = {
                    focusManager.clearFocus()
                    when (emptyQuery) {
                        true -> items.onBack()
                        false -> items.clearText()
                    }
                },
                textState = items.textState,
                focusRequester = focusRequester
            )
        }
        item {
            AnimatedColumnPart(
                emptyQuery = emptyQuery,
                onCategoriesClick = items.onCategoriesClick,
                padding = padding,
                onResultClick = { item ->
                    focusManager.clearFocus()
                    items.onResultClick(item)
                },
                results = items.results
            )
        }
    }
}

@Composable
private fun AnimatedColumnPart(
    emptyQuery: Boolean,
    onCategoriesClick: () -> Unit,
    padding: Dp,
    onResultClick: (DirectionsFeatureItemType.SingleItem) -> Unit,
    results: List<DirectionsFeatureItemType.SingleItem>,
) {
    AnimatedContent(targetState = emptyQuery, label = "") {
        when (it) {
            true ->
                DirectionsPickTargetOverview(
                    items = DirectionsPickTargetOverviewItems(
                        onCategoriesClick = onCategoriesClick,
                        favorites = PickTargetFakeResults,
                        history = PickTargetFakeResults,
                        horizontalPadding = PaddingValues(horizontal = padding),
                        onItemClick = onResultClick
                    )
                )


            false -> LazyListOfPickTargetItems(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = padding),
                onClick = onResultClick,
                items = results
            )
        }
    }
}

@Composable
private fun ClearTextOnBackPress(clearText: () -> Unit) {
    BackHandler { clearText() }
}

@AppPreview.Dark
@Composable
private fun Preview() = ClpTheme {
    Box(modifier = Modifier.padding(8.dp)) {
        DirectionsPickTarget()
    }
}
