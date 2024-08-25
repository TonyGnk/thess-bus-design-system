package com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard

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
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.selectDestination.NavCardSelect
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.start.NavCardStart
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalAnimatedContentScope
import com.tonyGnk.thessBus.designSystem.mobile.utils.LocalSharedTransitionScope

private enum class NavCardPreviewType {
    START, SELECT_DESTINATION, DESTINATION_OVERVIEW, SELECT_FROM, REVIEW_ROUTE
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun <S> SharedTransitionWithAnimatedContentWrapper(
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
fun NavigationCardPreview() {
    val selected = remember { mutableStateOf(NavCardPreviewType.START) }
    val navigateToStart = { selected.value = NavCardPreviewType.START }
    val navigateToSelectDestination = { selected.value = NavCardPreviewType.SELECT_DESTINATION }
    val navigateToDestinationOverview = { selected.value = NavCardPreviewType.DESTINATION_OVERVIEW }

    SharedTransitionWithAnimatedContentWrapper(selected.value) {
        when (it) {
            NavCardPreviewType.START -> NavCardStart(
                modifier = Modifier.padding(top = 80.dp),
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
                    onBackClick = { selected.value = NavCardPreviewType.START },
                    onQueryChange = onQueryChange,
                    query = query.value,
                    searchEnabled = searchEnabled,
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
    NavigationCardPreview()
}
