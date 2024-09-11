package com.tonyGnk.thessBus.designSystem.mobile.features.directions

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget.DirectionsLookTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.selectTarget.DirectionsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.selectTarget.DirectionsPickTargetFunctions
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.DirectionsStart
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalAnimatedContentScope
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalSharedTransitionScope

enum class DirectionsModes {
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
    isDetailedResultView: Boolean
) {
    val currentPhase = remember { mutableStateOf(DirectionsModes.PICK_TARGET) }
    val navigateToStart = { currentPhase.value = DirectionsModes.START }
    val navigateToSelectDestination = { currentPhase.value = DirectionsModes.PICK_TARGET }

    val query = remember { mutableStateOf("") }
    val onQueryChange: (String) -> Unit = { newValue ->
        query.value = newValue
    }

    SharedTransitionWrapper(currentPhase.value) {
        when (it) {
            DirectionsModes.START -> DirectionsStart(
                modifier = modifier.statusBarsPadding(),
                onSearchClick = navigateToSelectDestination
            )

            DirectionsModes.PICK_TARGET -> {
                val functions = remember {
                    DirectionsPickTargetFunctions(
                        onBack = { currentPhase.value = DirectionsModes.START },
                        onSearch = { currentPhase.value = DirectionsModes.LOOK_TARGET },
                        onResult = { _, _ -> },
                        onQueryChange = onQueryChange
                    )
                }

                DirectionsPickTarget(
                    modifier = modifier,
                    searchBarModifier = Modifier.statusBarsPadding(),
                    query = query.value,
                    requestFocus = true,
                    functions = functions
                )
            }

            DirectionsModes.LOOK_TARGET -> DirectionsLookTarget(
                modifier = Modifier.statusBarsPadding(),
                query = query.value,
                onBack = navigateToSelectDestination,
                poiTitle = "Nova Store",
                poiCategory = "Εταιρεία Τηλεπικοινωνιών"
            )
        }
    }
}

@Composable
@AppPreview.Dark
private
fun Preview() = ClpTheme {
    NavigationCardPreview(isDetailedResultView = false)
}
