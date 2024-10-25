package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickStart

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.data.LocationsPickTargetItems


@Composable
fun LocationsPickStart(
    modifier: Modifier = Modifier,
    items: LocationsPickTargetItems = LocationsPickTargetItems.preview
) {
    val listState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    // Handle scroll and focus effects

}
