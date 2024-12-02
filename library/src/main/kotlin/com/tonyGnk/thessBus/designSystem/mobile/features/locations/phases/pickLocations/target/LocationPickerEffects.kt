package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.target

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester

@Composable
internal fun LocationPickerEffects(
    searchState: LocationsPickTargetItems.SearchState,
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
                //  focusManager.clearFocus()
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
