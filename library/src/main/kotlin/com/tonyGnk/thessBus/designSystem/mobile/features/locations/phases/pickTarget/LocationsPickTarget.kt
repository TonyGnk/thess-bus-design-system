package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeFavorites
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeHistory
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview.PickTargetOverview
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.searchMode.LazyListOfPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedWindowInsets


@Stable
data class LocationsPickTargetItems(
    val onBack: () -> Unit,
    val onSearchIme: () -> Unit,
    val clearText: () -> Unit,
    val onCategoriesClick: () -> Unit,
    val onFavoriteNotConfiguredClick: () -> Unit,
    val requestFocus: Boolean,
    val applySystemBarPadding: Boolean,
    val textState: TextFieldState,
    val results: List<DirectionsFeatureItemType.SingleItem>,
    val sharedElementText: String,
    val sharedElementCard: String,
    val sharedElementMagnifier: String,
    val favorites: List<DirectionsFeatureItemType.SingleItem>,
    val history: List<DirectionsFeatureItemType.SingleItem>,
    val horizontalPadding: PaddingValues,
    val onPickItem: (DirectionsFeatureItemType.SingleItem?) -> Unit,
    val onAddCollectionClick: () -> Unit
) {
    companion object {
        val preview = LocationsPickTargetItems(
            onBack = {},
            onSearchIme = {},
            clearText = {},
            onCategoriesClick = {},
            requestFocus = false,
            applySystemBarPadding = true,
            onFavoriteNotConfiguredClick = {},
            textState = TextFieldState(),
            results = PickTargetFakeResults,
            sharedElementText = "",
            sharedElementMagnifier = "",
            sharedElementCard = "",
            favorites = PickTargetFakeFavorites,
            history = PickTargetFakeHistory,
            horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
            onPickItem = {},
            onAddCollectionClick = {}
        )
    }
}


@Composable
fun LocationsPickTarget(
    modifier: Modifier = Modifier,
    items: LocationsPickTargetItems = LocationsPickTargetItems.preview,
) {
    val lazyListState = rememberLazyListState()
    LaunchedEffect(true) {
        lazyListState.scrollToItem(0)
    }
    val canScrollBackward by remember {
        derivedStateOf { lazyListState.canScrollBackward }
    }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    if (items.requestFocus) LaunchedEffect(
        key1 = canScrollBackward,
    ) {
        if (lazyListState.canScrollBackward) {
            focusManager.clearFocus()
        } else {
            focusRequester.requestFocus()
        }
    }

    val query = items.textState.text.toString()
    val padding = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
    val emptyQuery = query.isEmpty()

    if (items.requestFocus && !emptyQuery) ClearTextOnBackPress(items.clearText)

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
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
                    focusManager.clearFocus() //TODO: Maybe remove this
                    when (emptyQuery) {
                        true -> items.onBack()
                        false -> items.clearText()
                    }
                },
                textState = items.textState,
                focusRequester = focusRequester,
                sharedElementTextTag = items.sharedElementText,
                sharedElementIconTag = items.sharedElementMagnifier,
                sharedElementCard = items.sharedElementCard
            )
        }
        item {
            AnimatedContent(targetState = emptyQuery, label = "") {
                when (it) {
                    true -> PickTargetOverview(
                        items = items.copy(
                            onPickItem = { item ->
                                focusManager.clearFocus()
                                items.onPickItem(item)
                            },
                        ),
                    )

                    false -> LazyListOfPickTargetItems(
                        modifier = Modifier.padding(horizontal = padding),
                        onClick = { item ->
                            focusManager.clearFocus()
                            items.onPickItem(item)
                        },
                        items = items.results
                    )
                }
            }
        }
    }
}


@Composable
private fun ClearTextOnBackPress(clearText: () -> Unit) {
    BackHandler { clearText() }
}

@AppPreview.Light
@Composable
private fun Preview() = ClpTheme {
    Box(modifier = Modifier.padding(8.dp)) {
        LocationsPickTarget()
    }
}
