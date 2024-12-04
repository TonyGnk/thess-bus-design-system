package com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.target

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.DirectionsPickerEffects
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.result.LocationPickerContent

@Composable
fun DirectionsPickTarget(
    modifier: Modifier = Modifier,
    items: LocationsPickTargetItems = LocationsPickTargetItems.preview
) {
    val listState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    // Handle scroll and focus effects
    DirectionsPickerEffects(
        searchState = items.searchState,
        listState = listState,
        focusRequester = focusRequester,
    )

    LocationPickerContent(
        modifier = modifier.background(AppColor.background),
        items = items.copy(
            onBack = {
                focusManager.clearFocus()
                items.onBack()
            },
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
