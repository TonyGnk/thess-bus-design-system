package com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.shared.card.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.ClickableWithoutRipple
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement

sealed interface SearchBarType {
    data class TextField(
        val textFieldState: TextFieldState,
        val focusRequester: FocusRequester,
        val onSearchIme: () -> Unit = {},
        val onCancel: () -> Unit = {},
    ) : SearchBarType

    data class Static(
        val text: String,
        val alternativeText: String = text,
        val onTextClick: () -> Unit
    ) : SearchBarType
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onClear: () -> Unit = {},
    type: SearchBarType,
    sharedElements: SearchBarSharedElementIds = SearchBarSharedElementIds(),
) {
    val searchLabel = LocationsProperties.SEARCH_LABEL
    val searchStyle = LocationsProperties.searchTextStyle

    val sizeInScreen = searchLabel.findScreenSize(searchStyle).height - 1.dp

    Row(
        horizontalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.SEMI_BEZEL_PADDING.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        SearchBarContainer(
            modifier = Modifier.weight(1f),
            shadowElevation = when (type) {
                is SearchBarType.TextField -> 0
                is SearchBarType.Static -> 2
            },
            sharedElementCard = sharedElements.searchBar
        ) {
            val modifierOfTheCenteredItem = Modifier
                .padding(vertical = LocationsProperties.IN_PADDING.dp)
                .fillMaxWidth()
                .weight(1f)
                .mySharedElement(sharedElements.placeHolder)
            Icon(
                iconRes = AppIcon.Search.iconRes,
                color = AppColor.onSurface,
                modifier = Modifier
                    .padding(12.dp)
                    .size(sizeInScreen)
            )
            when (type) {
                is SearchBarType.TextField -> SearchField(
                    searchStyle = searchStyle,
                    modifier = modifierOfTheCenteredItem,
                    searchLabel = searchLabel,
                    onSearchClick = type.onSearchIme,
                    textState = type.textFieldState,
                    sharedElementTextTag = sharedElements.text,
                    focusRequester = type.focusRequester
                )

                is SearchBarType.Static -> Text(
                    text = type.text.ifBlank { type.alternativeText },
                    style = searchStyle,
                    modifier = modifierOfTheCenteredItem.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        type.onTextClick()
                    }
                )
            }
            AnimatedContent(
                targetState =
                type is SearchBarType.TextField && type.textFieldState.text.length > 2 ||
                        type is SearchBarType.Static && type.text.isNotBlank(),
                label = ""
            ) {
                when (it) {
                    true -> ClickableWithoutRipple(
                        color = AppColor.onSurface,
                        contentAlignment = Alignment.Center,
                        onClick = onClear,
                    ) { color ->
                        Icon(
                            iconRes = AppIcon.Close.iconRes,
                            color = color,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(sizeInScreen)
                        )
                    }

                    false -> {}
                }
            }

        }
        if (type is SearchBarType.TextField) ClickableWithoutRipple(
            color = AppColor.primary,
            contentAlignment = Alignment.Center,
            onClick = type.onCancel,
        ) {
            Text("Ακύρωση", style = AppTypo.titleSmall.copy(color = it))
        }
    }
}


@Composable
fun SearchBarContainer(
    modifier: Modifier = Modifier,
    sharedElementCard: String,
    shadowElevation: Int = 1,
    content: @Composable RowScope.() -> Unit
) {
    SurfaceWithShadows(
        shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
        color = AppColor.surfaceLowest,
        shadowElevation = shadowElevation,
        modifier = modifier.mySharedElement(sharedElementCard)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
            modifier = Modifier.padding(
                horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp,
            )
        ) {
            content(this)
        }
    }
}


@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    searchStyle: TextStyle,
    searchLabel: String,
    textState: TextFieldState,
    sharedElementTextTag: String,
    focusRequester: FocusRequester
) {
    val isTypingModeMy = textState.text.isNotEmpty()

    BasicTextField(
        state = textState,
        modifier = modifier.focusRequester(focusRequester),
        enabled = true,
        readOnly = false,
        textStyle = searchStyle,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        onKeyboardAction = {
            onSearchClick()
        },
        onTextLayout = {},
        cursorBrush = SolidColor(searchStyle.color),
        decorator = {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
            ) {
                it()

                if (!isTypingModeMy) Text(
                    text = searchLabel,
                    style = searchStyle,
                    modifier = Modifier.mySharedElement(
                        "NavCardStartSelectText",
                        skipLookaheadSize = true
                    )

                )
            }
        }
    )

}


@Composable
@AppPreview.Brightness
private fun Preview() = ThessBusTheme {
    SearchBar(
        type = SearchBarType.Static(
            text = "Search term", alternativeText = "Search here",
        ) {},
        sharedElements = SearchBarSharedElementIds()
    )
}


data class SearchBarSharedElementIds(
    val placeHolder: String = "placeHolder",
    val text: String = "text",
    val closeIcon: String = "closeIcon",
    val searchBar: String = "searchBar",
    val magnifier: String = "magnifier"
)
