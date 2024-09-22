package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.LocationsCard
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.LocationsCardItems
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedWindowInsets
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.preview.DirectionsPreviewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DirectionsPreStartWrapper(
    modifier: Modifier = Modifier,
    goToPickTarget: () -> Unit,
) {
    val items = LocationsCardItems(
        sharedElementCard = "a",
        sharedElementText = "b",
        sharedElementMagnifier = "c",
        onSearchClick = goToPickTarget,
    )
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = extendedWindowInsets
    ) {

        item {
            LocationsCard(
                modifier = Modifier
                    .padding(
                        horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
                    ),
                items = items
            )
        }
    }
}

@Composable
fun DirectionsPrePickWrapper(
    modifier: Modifier = Modifier,
    model: DirectionsPreviewModel,
    onBack: () -> Unit,
    goToCategories: () -> Unit,
) {
    val state by model.state.collectAsStateWithLifecycle()

    val items = DirectionsPickTargetItems(
        onBack = onBack,
        requestFocus = true,
        applySystemBarPadding = true,
        textState = state.textState,
        onCategoriesClick = goToCategories,
        onResultClick = {},
        onSearchIme = {},
        clearText = model::clearText,
        results = emptyList(),
    )

    DirectionsPickTarget(
        modifier = modifier.fillMaxSize(),
        items = items
    )
}

@Composable
fun Float.toDp() = with(LocalDensity.current) { this@toDp.toDp() }
