package com.tonyGnk.thessBus.designSystem.mobile.features.directions

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.lookTarget.DirectionsLookTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTarget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTargetFunctions
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
) {
    val phase = remember { mutableStateOf(DirectionPhases.LOOK_TARGET) }
    val goToStart = { phase.value = DirectionPhases.START }
    val goToPickTarget = { phase.value = DirectionPhases.PICK_TARGET }
    val goToLookTarget = { phase.value = DirectionPhases.LOOK_TARGET }


    val query = remember { mutableStateOf("") }
    val onQueryChange: (String) -> Unit = { newValue ->
        query.value = newValue
    }

    SharedTransitionWrapper(phase.value) {
        when (it) {
            DirectionPhases.START -> DirectionsStart(
                modifier = modifier.extendedStatusBarsPadding(),
                onSearchClick = goToPickTarget
            )

            DirectionPhases.PICK_TARGET -> {
                val functions = remember {
                    DirectionsPickTargetFunctions(
                        onBack = goToStart,
                        onSearch = goToLookTarget,
                        onResult = { _, _ -> goToLookTarget() },
                        onQueryChange = onQueryChange
                    )
                }

                DirectionsPickTarget(
                    modifier = modifier,
                    query = query.value,
                    requestFocus = true,
                    functions = functions
                )
            }

            DirectionPhases.LOOK_TARGET -> DirectionsLookTarget(
                modifier = modifier.extendedStatusBarsPadding(),
                query = query.value,
                onBack = goToPickTarget,
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
    NavigationCardPreview()
}
