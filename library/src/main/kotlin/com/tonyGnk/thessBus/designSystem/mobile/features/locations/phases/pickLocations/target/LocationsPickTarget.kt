package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.target

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickLocations.LocationPickerContent

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
        items = items.copy(
            searchState = items.searchState.copy(
                onClick = { item ->
                    focusManager.clearFocus()
                    items.searchState.onClick(item)
                }
            )
        ),
        listState = listState,
        focusRequester = focusRequester,
    )
}
