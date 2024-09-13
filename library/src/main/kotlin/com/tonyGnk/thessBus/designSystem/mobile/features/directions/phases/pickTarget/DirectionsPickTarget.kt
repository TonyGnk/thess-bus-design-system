package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetItem
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview.PickTargetOverview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.searchMode.LazyListOfPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedStatusBarsPadding

@Stable
data class DirectionsPickTargetFunctions(
    val onBack: () -> Unit,
    val onSearchIme: () -> Unit,
    val onResultClick: (PickTargetItem) -> Unit,
) {
    companion object {
        val Empty = DirectionsPickTargetFunctions(
            onBack = {},
            onSearchIme = {},
            onResultClick = { _ -> },
        )
    }
}

@Composable
fun DirectionsPickTarget(
    modifier: Modifier = Modifier,
    functions: DirectionsPickTargetFunctions = DirectionsPickTargetFunctions.Empty,
    requestFocus: Boolean = false,
    horizontalPadding: Int = 0,
    textState: TextFieldState,
    historyList: List<PickTargetItem> = emptyList(),
    results: List<PickTargetItem> = PickTargetFakeResults,
) {
    BackHandler(enabled = textState.text.isNotEmpty()) {
        textState.clearText()
    }
    val isTypingModeMy = textState.text.isNotEmpty()

    val lazyListState = rememberLazyListState()
    val canScrollBackward by remember {
        derivedStateOf { lazyListState.canScrollBackward }
    }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    if (requestFocus) LaunchedEffect(
        key1 = canScrollBackward,
    ) {
        if (lazyListState.canScrollBackward) {
            focusManager.clearFocus()
        } else {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = modifier.extendedStatusBarsPadding(),
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = horizontalPadding.dp),
            onSearchClick = functions.onSearchIme,
            onBackClick = {
                if (isTypingModeMy) textState.clearText() else functions.onBack()
            },
            textState = textState,
            focusRequester = focusRequester
        )
        AnimatedContent(targetState = isTypingModeMy, label = "") {
            when (it) {
                true -> LazyListOfPickTargetItems(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = functions.onResultClick,
                    horizontalPadding = horizontalPadding,
                    state = lazyListState,
                    items = results
                )

                false -> PickTargetOverview(
                    state = lazyListState,
                    horizontalPadding = horizontalPadding,
                    onItemClick = functions.onResultClick,
                )
            }
        }
    }
}

@AppPreview.Dark
@Composable
private fun Preview() = ClpTheme {
    val textState = rememberTextFieldState(
        initialText = "", initialSelection = TextRange("".length),
    )

    val functions = remember {
        DirectionsPickTargetFunctions(
            onBack = {},
            onSearchIme = {},
            onResultClick = { _ -> },
        )
    }

    Box(modifier = Modifier.padding(8.dp)) {
        DirectionsPickTarget(
            functions = functions,
            textState = textState
        )
    }
}
