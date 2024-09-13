package com.tonyGnk.thessBus.designSystem.mobile.features.directions

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget.DirectionsLookTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTargetFunctions
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.DirectionsStart
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalAnimatedContentScope
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalSharedTransitionScope
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedStatusBarsPadding

enum class DirectionPhases {
    START, PICK_TARGET, LOOK_TARGET
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun <S> SharedTransitionWrapper(
    targetState: S, content: @Composable (S) -> Unit
) {
    SharedTransitionLayout {
        CompositionLocalProvider(value = LocalSharedTransitionScope provides this) {
            AnimatedContent(
                targetState = targetState,
                label = "shared_transition",
            ) {
                CompositionLocalProvider(
                    value = LocalAnimatedContentScope provides this
                ) {
                    content(it)
                }
            }
        }
    }
}

@Composable
fun NavigationCardPreview(
    modifier: Modifier = Modifier,
    horizontalPadding: Int = 0,
) {
    val selectedItem =
        remember { mutableStateOf<DirectionsLookTargetType>(DirectionsLookTargetType.JustMap) }

    val phase = remember { mutableStateOf(DirectionPhases.PICK_TARGET) }
    val goToStart = { phase.value = DirectionPhases.START }
    val goToPickTarget = { phase.value = DirectionPhases.PICK_TARGET }
    val goToLookTargetJustMap: (List<PickTargetItem>) -> Unit = { items ->
        selectedItem.value = DirectionsLookTargetType.MultipleItems(items)
        phase.value = DirectionPhases.LOOK_TARGET
    }
    val goToLookTarget: (PickTargetItem) -> Unit = { item ->
        selectedItem.value = item
        phase.value = DirectionPhases.LOOK_TARGET
    }

    val searchResults: (String) -> Unit = { _ -> }

    val textState = rememberTextFieldState(
        initialText = "", initialSelection = TextRange("".length),
    )

    LaunchedEffect(textState.text) {
        searchResults(textState.text.toString())
    }

    SharedTransitionWrapper(phase.value) {
        when (it) {
            DirectionPhases.START -> DirectionsStart(
                modifier = modifier.extendedStatusBarsPadding(),
                horizontalPadding = horizontalPadding,
                onSearchClick = goToPickTarget
            )

            DirectionPhases.PICK_TARGET -> {
                val functions = remember {
                    DirectionsPickTargetFunctions(
                        onBack = goToStart,
                        onSearchIme = { goToLookTargetJustMap(emptyList()) },
                        onResultClick = { item -> goToLookTarget(item) },
                    )
                }

                DirectionsPickTarget(
                    modifier = modifier,
                    requestFocus = true,
                    horizontalPadding = horizontalPadding,
                    functions = functions,
                    textState = textState
                )
            }

            DirectionPhases.LOOK_TARGET -> DirectionsLookTarget(
                givenType = selectedItem.value,
                horizontalPadding = horizontalPadding,
                modifier = modifier.extendedStatusBarsPadding(),
                query = textState.text.toString(),
                onBack = goToPickTarget,
            )
        }
    }
}

@Composable
@AppPreview.Dark
private
fun Preview() = ClpTheme {
    NavigationCardPreview()
}
