package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.menu.applyContextMenu
import com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar.SearchBar
import com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar.SearchBarType
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.data.LocationsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.data.LocationsPickersSearchState
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview.PickTargetOverview
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.searchMode.ResultList
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.extendedWindowInsets


@Composable
fun LocationsPickTarget(
    modifier: Modifier = Modifier,
    items: LocationsPickTargetItems = LocationsPickTargetItems.preview
) {
    val listState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    // Handle scroll and focus effects
    LocationPickerEffects(
        searchState = items.searchState,
        listState = listState,
        focusManager = focusManager,
        focusRequester = focusRequester,
    )

    LocationPickerContent(
        modifier = modifier,
        items = items,
        listState = listState,
        focusRequester = focusRequester,
        focusManager = focusManager,
    )
}

@Composable
internal fun LocationPickerEffects(
    searchState: LocationsPickersSearchState,
    listState: LazyListState,
    focusManager: FocusManager,
    focusRequester: FocusRequester,
) {
    // Initial scroll
    LaunchedEffect(Unit) {
        listState.scrollToItem(0)
    }

    // Focus handling
    val canScrollBackward by remember {
        derivedStateOf { listState.canScrollBackward }
    }

    if (searchState.requestFocus) {
        LaunchedEffect(canScrollBackward) {
            if (listState.canScrollBackward) {
                focusManager.clearFocus()
            } else {
                focusRequester.requestFocus()
            }
        }
    }

    // Back press handler
    val query = searchState.textFieldState.text.toString()
    if (searchState.requestFocus && query.isNotEmpty()) {
        BackHandler { searchState.clearText() }
    }
}

@Composable
internal fun LocationPickerContent(
    modifier: Modifier,
    items: LocationsPickTargetItems,
    listState: LazyListState,
    focusRequester: FocusRequester,
    focusManager: FocusManager,
) {
    val query = items.searchState.textFieldState.text.toString()
    val padding = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
    val emptyQuery = query.isEmpty()

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = if (items.applySystemBarPadding) {
            extendedWindowInsets
        } else {
            PaddingValues(0.dp)
        },
        modifier = modifier.applyContextMenu(items.collectionsState.selectedId != null)
    ) {
        item {
            SearchBar(
                modifier = Modifier.padding(horizontal = padding),
                onSearchIme = items.searchState.onSearchIme,
                onBackIconClick = {
                    when {
                        emptyQuery -> items.onBack()
                        else -> items.searchState.clearText()
                    }
                },
                type = SearchBarType.TextField(
                    textFieldState = items.searchState.textFieldState,
                    focusRequester = focusRequester
                ),
                sharedElementPlaceHolderTag = items.sharedElementIds.placeHolder,
                sharedElementIconTag = items.sharedElementIds.magnifier,
                sharedElementTextTag = items.sharedElementIds.text,
                sharedElementBox = items.sharedElementIds.searchBar,
            )
        }

        item {
            LocationPickerResult(
                emptyQuery = emptyQuery,
                padding = padding,
                focusManager = focusManager,
                collectionsState = items.collectionsState,
                searchState = items.searchState
            )
        }
    }

    // Bottom sheet
//    if (items.bottomSheetType == CollectionBottomSheetType.Overview) {
//        CollectionBottomSheet(
//            type = state.bottomSheetType,
//            onDismissRequest = {
//                onEvent(LocationPickerEvent.OnBottomSheetTypeChange(CollectionBottomSheetType.Hidden))
//            }
//        )
//    }
}

@Composable
private fun LocationPickerResult(
    emptyQuery: Boolean,
    focusManager: FocusManager,
    padding: Dp,
    collectionsState: LocationsPickTargetItems.FavoritesState,
    searchState: LocationsPickersSearchState
) {
    AnimatedContent(targetState = emptyQuery, label = "") { showOverview ->
        when (showOverview) {
            true -> PickTargetOverview(
                horizontalPadding = PaddingValues(horizontal = padding),
                favoritesState = collectionsState.copy(
                    onClick = { item ->
                        focusManager.clearFocus()
                        collectionsState.onClick(item)
                    }
                )
            )

            false -> ResultList(
                modifier = Modifier.padding(horizontal = padding),
                onClick = { item ->
                    focusManager.clearFocus()
                    searchState.onResultClick(item)
                },
                items = searchState.results
            )
        }
    }
}

//@Composable
//fun LocationsPickTarget(
//    modifier: Modifier = Modifier,
//    items: LocationsPickTargetItems = LocationsPickTargetItems.preview,
//) {
//    val lazyListState = rememberLazyListState()
//    LaunchedEffect(true) {
//        lazyListState.scrollToItem(0)
//    }
//    val canScrollBackward by remember {
//        derivedStateOf { lazyListState.canScrollBackward }
//    }
//    val focusRequester = remember { FocusRequester() }
//    val focusManager = LocalFocusManager.current
//
//    if (items.searchState.requestFocus) LaunchedEffect(
//        key1 = canScrollBackward,
//    ) {
//        if (lazyListState.canScrollBackward) {
//            focusManager.clearFocus()
//        } else {
//            focusRequester.requestFocus()
//        }
//    }
//
//    val query = items.searchState.textFieldState.text.toString()
//    val padding = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
//    val emptyQuery = query.isEmpty()
//
//    if (items.searchState.requestFocus && !emptyQuery) ClearTextOnBackPress(items.clearText)
//
//    LazyColumn(
//        state = lazyListState,
//        verticalArrangement = Arrangement.spacedBy(12.dp),
//        contentPadding = if (items.applySystemBarPadding) extendedWindowInsets else PaddingValues(
//            0.dp
//        ),
//        modifier = modifier.applyContextMenu(items.collectionsState.selectedId != null)
//    ) {
//        item {
//            SearchBar(
//                modifier = Modifier.padding(horizontal = padding),
//                onSearchIme = items.onSearchIme,
//                onBackIconClick = {
//                    when (emptyQuery) {
//                        true -> items.onBack()
//                        false -> items.clearText()
//                    }
//                },
//                type = SearchBarType.TextField(
//                    textFieldState = items.searchState.textFieldState,
//                    focusRequester = focusRequester
//                ),
//                sharedElementPlaceHolderTag = items.sharedElementIds.placeHolder,
//                sharedElementIconTag = items.sharedElementIds.magnifier,
//                sharedElementTextTag = items.sharedElementIds.text,
//                sharedElementBox = items.sharedElementIds.searchBar,
//            )
//        }
//        item {
//            AnimatedContent(targetState = emptyQuery, label = "") {
//                when (it) {
//                    true -> PickTargetOverview(
//                        horizontalPadding = items.horizontalPadding,
//                        collectionState = items.collectionsState.copy(
//                            onClick = { item ->
//                                focusManager.clearFocus()
//                                items.collectionsState.onClick(item)
//                            }
//                        )
//                    )
//
//                    false -> LazyListOfPickTargetItems(
//                        modifier = Modifier.padding(horizontal = padding),
//                        onClick = { item ->
//                            focusManager.clearFocus()
//                            items.onResultClick(item)
//                        },
//                        items = items.searchState.results
//                    )
//                }
//            }
//        }
//    }
//
//    if (items.collectionsBottomSheetType == CollectionBottomSheetType.Overview) CollectionBottomSheet(
//        type = items.collectionsBottomSheetType,
//        onDismissRequest = {
//            items.setBottomSheetType(CollectionBottomSheetType.Hidden)
//        }
//    )
//}


@AppPreview.Light
@Composable
private fun Preview() = ThessBusTheme {
    Box(modifier = Modifier.padding(8.dp)) {
        //  LocationsPickTarget()
    }
}
