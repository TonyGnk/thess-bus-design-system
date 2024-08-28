package com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.NavCardSelect
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.start.NavCardStart
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalAnimatedContentScope
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalSharedTransitionScope

private enum class NavCardPreviewType {
    START, SELECT_DESTINATION, DESTINATION_OVERVIEW, SELECT_FROM, REVIEW_ROUTE
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
    val currentPhase = remember { mutableStateOf(NavCardPreviewType.START) }
    val navigateToStart = { currentPhase.value = NavCardPreviewType.START }
    val navigateToSelectDestination = { currentPhase.value = NavCardPreviewType.SELECT_DESTINATION }
    val navigateToDestinationOverview =
        { currentPhase.value = NavCardPreviewType.DESTINATION_OVERVIEW }

    SharedTransitionWrapper(currentPhase.value) {
        when (it) {
            NavCardPreviewType.START -> NavCardStart(
                modifier = modifier,
                onSearchClick = navigateToSelectDestination
            )

            NavCardPreviewType.SELECT_DESTINATION -> {

                val query = remember { mutableStateOf("") }
                val onQueryChange: (String) -> Unit = { newValue ->
                    query.value = newValue
                }
                val searchEnabled by remember {
                    derivedStateOf { query.value.isNotBlank() }
                }

                NavCardSelect(
                    modifier = modifier,
                    onBackClick = { currentPhase.value = NavCardPreviewType.START },
                    onQueryChange = onQueryChange,
                    query = query.value,
                    searchEnabled = searchEnabled,
                    isFocused = true,
                    isDetailedResults = isDetailedResultView
                )
            }

            NavCardPreviewType.DESTINATION_OVERVIEW -> TODO()
            NavCardPreviewType.SELECT_FROM -> TODO()
            NavCardPreviewType.REVIEW_ROUTE -> TODO()
        }
    }
}

@Composable
@AppPreview.Dark
private
fun Preview() = ClpTheme {
    NavigationCardPreview(isDetailedResultView = false)
}
