package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues.NORMAL_BEZEL_PADDING
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.DirectionsFeatureItemType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.overview.PickTargetOverview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.searchMode.LazyListOfPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedStatusBarsPadding

@Stable
data class DirectionsPickTargetFunctions(
    val onBack: () -> Unit = {},
    val onSearchIme: () -> Unit = {},
    val onResultClick: (DirectionsFeatureItemType.SingleItem) -> Unit = {},
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
    applySystemBarPadding: Boolean = true,
    textState: TextFieldState,
    onCategoriesClick: () -> Unit = {},
    historyList: List<DirectionsFeatureItemType.SingleItem> = emptyList(),
    results: List<DirectionsFeatureItemType.SingleItem> = PickTargetFakeResults,
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

    val padding = NORMAL_BEZEL_PADDING.dp

    Column(
        modifier = modifier
            .then(
                if (applySystemBarPadding) Modifier
                    .extendedStatusBarsPadding() else Modifier
            )
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = padding),
            onSearchClick = functions.onSearchIme,
            onBackClick = {
                if (isTypingModeMy) textState.clearText() else {
                    functions.onBack()
                    focusManager.clearFocus()
                }
            },
            textState = textState,
            focusRequester = focusRequester
        )
        AnimatedContent(targetState = isTypingModeMy, label = "") {
            when (it) {
                true -> LazyListOfPickTargetItems(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(horizontal = padding),
                    onClick = { item ->
                        focusManager.clearFocus()
                        functions.onResultClick(item)
                    },
                    state = lazyListState,
                    items = results
                )

                false -> PickTargetOverview(
                    state = lazyListState,
                    onCategoriesClick = onCategoriesClick,
                    horizontalPadding = PaddingValues(horizontal = padding),
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
