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
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.menu.applyContextMenu
import com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar.SearchBar
import com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar.SearchBarType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeFavorites
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeHistory
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview.PickTargetOverview
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.searchMode.LazyListOfPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.extendedWindowInsets


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
    val sharedElementPlaceHolder: String,
    val sharedElementText: String,
    val sharedElementSearchBar: String,
    val sharedElementMagnifier: String,
    val selectedFavoriteItemId: Int?,
    val updateSelectedFavoriteItemId: (Int?) -> Unit,
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
            selectedFavoriteItemId = null,
            onFavoriteNotConfiguredClick = {},
            textState = TextFieldState(),
            results = PickTargetFakeResults,
            sharedElementPlaceHolder = "",
            sharedElementMagnifier = "",
            sharedElementSearchBar = "",
            favorites = PickTargetFakeFavorites,
            history = PickTargetFakeHistory,
            horizontalPadding = PaddingValues(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
            onPickItem = {},
            updateSelectedFavoriteItemId = {},
            sharedElementText = "",
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
        modifier = modifier.applyContextMenu(items.selectedFavoriteItemId != null)
    ) {
        item {
            SearchBar(
                modifier = Modifier.padding(horizontal = padding),
                onSearchClick = items.onSearchIme,
                onBackClick = {
                    when (emptyQuery) {
                        true -> items.onBack()
                        false -> items.clearText()
                    }
                },
                type = SearchBarType.TextField(
                    textFieldState = items.textState, focusRequester = focusRequester
                ),
                sharedElementPlaceHolderTag = items.sharedElementPlaceHolder,
                sharedElementIconTag = items.sharedElementMagnifier,
                sharedElementTextTag = items.sharedElementText,
                sharedElementBox = items.sharedElementSearchBar
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
