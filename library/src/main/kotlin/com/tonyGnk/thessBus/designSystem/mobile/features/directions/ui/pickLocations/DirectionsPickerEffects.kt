package com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.target.LocationsPickTargetItems

@Composable
internal fun DirectionsPickerEffects(
    searchState: LocationsPickTargetItems.SearchState,
    listState: LazyListState,
    focusRequester: FocusRequester,
) {
    LaunchedEffect(Unit) {
        listState.scrollToItem(0)
    }

    val canScrollBackward by remember {
        derivedStateOf { listState.canScrollBackward }
    }

    if (searchState.requestFocus) {
        LaunchedEffect(canScrollBackward) {
            if (!listState.canScrollBackward) {
                focusRequester.requestFocus()
            }
        }
    }

    val query = searchState.textFieldState.text.toString()
    if (searchState.requestFocus && query.isNotEmpty()) {
        BackHandler { searchState.clearText() }
    }
}
