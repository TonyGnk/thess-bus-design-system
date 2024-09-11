package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.selectTarget

import android.annotation.SuppressLint
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onSearchClick: () -> Unit,
    isTypingMode: Boolean,
    onBackClick: () -> Unit,
    onQueryChange: (String) -> Unit,
    focusRequester: FocusRequester
) {
    val searchLabel = NavCardProperties.SEARCH_LABEL
    val searchStyle = AppTypo.titleMedium.copy(color = AppColor.onSurface)

    val sizeInScreen = searchLabel.findScreenSize(searchStyle).height - 1.dp

    SearchBarContainer(modifier = modifier) {
        IconButton(
            iconRes = AppIcon.back,
            color = AppColor.transparent,
            onClick = onBackClick,
            modifier = Modifier.size(sizeInScreen)
        )
        SearchField(
            query = query,
            onQueryChange = onQueryChange,
            searchStyle = searchStyle,
            modifier = Modifier
                .padding(vertical = NavCardProperties.IN_PADDING.dp)
                .fillMaxWidth()
                .weight(1f)
                .mySharedElement("SearchContainerText"),
            searchEnabled = isTypingMode,
            searchLabel = searchLabel,
            onSearchClick = onSearchClick,
            focusRequester = focusRequester
        )
        IconButton(
            iconRes = AppIcon.search,
            color = AppColor.transparent,
            onClick = onSearchClick,
            modifier = Modifier
                .size(sizeInScreen)
                .mySharedElement("SearchContainerMagnifier")
        )
    }
}


@Composable
fun SearchBarContainer(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    SurfaceWithShadows(
        shape = RoundedCornerShape(NavCardProperties.IN_CORNERS.dp),
        color = AppColor.surfaceContainerLowest,
        modifier = modifier
            .mySharedElement("SearchContainer")
            .clip(RoundedCornerShape(NavCardProperties.IN_CORNERS.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
            modifier = modifier.padding(
                start = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp,
                end = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp
            )
        ) {
            content(this)
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    query: String,
    onSearchClick: () -> Unit,
    onQueryChange: (String) -> Unit,
    searchEnabled: Boolean,
    searchStyle: TextStyle,
    searchLabel: String,
    focusRequester: FocusRequester
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.focusRequester(focusRequester),
        enabled = true,
        readOnly = false,
        textStyle = searchStyle,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchClick() }
        ),
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        onTextLayout = {},
        cursorBrush = SolidColor(searchStyle.color),
        decorationBox = {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
            ) {
                it()

                if (!searchEnabled) Text(
                    text = searchLabel,
                    style = searchStyle,
                    modifier = Modifier.mySharedElement("NavCardStartSelectText")
                )
            }
        }
    )
}


@Composable
@AppPreview.Brightness
private fun Preview() = ClpTheme {
    SearchBar(
        query = "",
        onQueryChange = { },
        isTypingMode = false,
        onBackClick = { },
        onSearchClick = { },
        focusRequester = remember { FocusRequester() }
    )
}
