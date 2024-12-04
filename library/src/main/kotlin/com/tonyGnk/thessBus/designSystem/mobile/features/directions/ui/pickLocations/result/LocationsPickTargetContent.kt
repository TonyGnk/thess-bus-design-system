package com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.IconWithTextRow
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.menu.applyContextMenu
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar.SearchBar
import com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar.SearchBarType
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.SearchAnimatedTransition
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.shared.card.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.favorites.FavoritesWidget
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.recent.RecentList
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.target.LocationsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.add
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getExtendedWindowInsets


@Composable
internal fun LocationPickerContent(
    modifier: Modifier = Modifier,
    items: LocationsPickTargetItems,
    listState: LazyListState,
    focusRequester: FocusRequester,
) {
    val query = items.searchState.textFieldState.text.toString()
    val isQueryEmpty = query.isEmpty()
    val labelStyle = AppTypo.titleMedium.copy(
        color = AppColor.onSurface.copy(alpha = 0.9f)
    )

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = if (items.applySystemBarPadding) {
            getExtendedWindowInsets(topPaddingIfNoStatusBar = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp).add(
                bottom = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
            )
        } else {
            PaddingValues(0.dp)
        },
        modifier = modifier
            .imePadding()
            .applyContextMenu(items.favoritesState.selectedId != null)
            .padding(
                horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
            )
    ) {
        item(key = "searchBar") {
            SearchBar(
                onClear = {
                    items.searchState.clearText()
                },
                type = SearchBarType.TextField(
                    textFieldState = items.searchState.textFieldState,
                    focusRequester = focusRequester,
                    onSearchIme = items.searchState.onSearchIme,
                    onCancel = {
                        items.searchState.clearText()
                        items.onBack()
                    },
                ),
                sharedElements = items.searchSharedElements
            )
        }

        item(key = "pickOnMap") {
            ButtonOrResults(
                isQueryEmpty = isQueryEmpty,
                searchState = items.searchState
            )
        }
        item(key = "favoritesLabel") {
            SearchAnimatedTransition(isQueryEmpty) {
                Text(
                    text = "Favorites", style = labelStyle, modifier = Modifier.padding(top = 14.dp)
                )
            }
        }
        item(key = "favorites") {
            SearchAnimatedTransition(isQueryEmpty) {
                FavoritesWidget(
                    state = items.favoritesState
                )
            }
        }
        item(key = "recentLabel") {
            SearchAnimatedTransition(isQueryEmpty) {
                Text(
                    text = "Recent", style = labelStyle, modifier = Modifier.padding(top = 14.dp)
                )
            }
        }
        item(key = "recent") {
            SearchAnimatedTransition(isQueryEmpty = isQueryEmpty) {
                RecentList(items.recentState)
            }
        }
    }
}


@Composable
private fun ButtonOrResults(
    isQueryEmpty: Boolean,
    searchState: LocationsPickTargetItems.SearchState,
) {
    SearchAnimatedTransition(
        isQueryEmpty = isQueryEmpty,
        whenEmpty = {
            LocationSearchListButton(
                modifier = Modifier.fillMaxWidth(),
                label = "Pick on map",
                onClick = searchState.onPickOnMap
            )
        },
        whenNotEmpty = {
            ResultList(
                onClick = searchState.onClick,
                items = searchState.results
            )
        }
    )
}

@Composable
fun LocationSearchListButton(
    modifier: Modifier = Modifier,
    label: String,
    color: Color = AppColor.primary,
    onClick: () -> Unit = {}
) {
    SurfaceWithShadows(
        shadowElevation = 0,
        modifier = modifier,
        shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
        color = AppColor.surfaceLowest,
        onClick = onClick
    ) {
        IconWithTextRow(
            modifier = Modifier.padding(LocationsProperties.IN_PADDING.dp),
            contentColor = color,
            iconRes = AppIcon.MapMarker.iconRes,
            style = AppTypo.titleMedium.copy(color = color),
            weight = FontWeight.W500,
            arrangement = Arrangement.spacedBy(18.dp),
            text = label,
        )
    }
}
