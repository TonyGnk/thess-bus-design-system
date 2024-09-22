package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget

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
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    onBackClick: () -> Unit,
    textState: TextFieldState,
    sharedElementTextTag: String = "",
    sharedElementIconTag: String = "",
    focusRequester: FocusRequester
) {
    val searchLabel = LocationsProperties.SEARCH_LABEL
    val searchStyle = LocationsProperties.searchTextStyle

    val sizeInScreen = searchLabel.findScreenSize(searchStyle).height - 1.dp

    SearchBarContainer(modifier = modifier) {
        IconButton(
            iconRes = AppIcon.back,
            color = AppColor.transparent,
            onClick = onBackClick,
            modifier = Modifier.size(sizeInScreen)
        )
        SearchField(
            searchStyle = searchStyle,
            modifier = Modifier
                .padding(vertical = LocationsProperties.IN_PADDING.dp)
                .fillMaxWidth()
                .weight(1f)
                .mySharedElement(sharedElementTextTag),
            searchLabel = searchLabel,
            onSearchClick = onSearchClick,
            textState = textState,
            focusRequester = focusRequester
        )
        IconButton(
            iconRes = AppIcon.search,
            color = AppColor.transparent,
            onClick = onSearchClick,
            modifier = Modifier
                .size(sizeInScreen)
                .mySharedElement(sharedElementIconTag)
        )
    }
}


@Composable
fun SearchBarContainer(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    SurfaceWithShadows(
        shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
        color = AppColor.surfaceContainerLowest,
        modifier = modifier
            .mySharedElement("SearchContainer")
            .clip(RoundedCornerShape(LocationsProperties.IN_CORNERS.dp))
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
private fun Preview() = ClpTheme {
    SearchBar(
        onBackClick = { },
        onSearchClick = { },
        focusRequester = remember { FocusRequester() },
        textState = rememberTextFieldState()
    )
}
