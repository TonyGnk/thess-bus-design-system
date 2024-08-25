package com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.selectDestination

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.icon.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.icon.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.surface.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement


@Composable
fun SearchFieldContainer(
    modifier: Modifier = Modifier,
    query: String,
    searchEnabled: Boolean,
    onBackClick: () -> Unit,
    searchStyle: TextStyle,
    onQueryChange: (String) -> Unit,
) {
    val searchLabel = NavCardProperties.SEARCH_LABEL
    val sizeInScreen = searchLabel.findScreenSize(searchStyle)

    SurfaceWithShadows(
        shape = RoundedCornerShape(NavCardProperties.SMALL_CORNERS.dp),
        color = AppColor.surfaceContainerLowest,
        modifier = modifier.mySharedElement("NavCardStartSelect")
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier,
        ) {
            IconButton(
                iconRes = AppIcon.back,
                color = AppColor.transparent,
                contentColor = AppColor.onSurface,
                onClick = onBackClick,
                buttonModifier = Modifier.padding(horizontal = 4.dp),
                modifier = Modifier.size(sizeInScreen.height)
            )
//            Text(
//                text = searchLabel,
//                style = textStyle,
////                color = AppColor.onSurface,
//                modifier = Modifier
//
//                    .weight(1f)
//            )
            SearchField(
                query = query,
                onQueryChange = onQueryChange,
                searchStyle = searchStyle,
                modifier = Modifier
                    .padding(vertical = NavCardProperties.SEARCH_PADDING.dp)
                    .fillMaxWidth()
                    .weight(1f),
                searchEnabled = searchEnabled,
                searchLabel = searchLabel
            )
            Icon(
                iconRes = AppIcon.search,
                color = AppColor.onSurface,
                modifier = Modifier
                    .padding(NavCardProperties.SEARCH_PADDING.dp)
                    .size(sizeInScreen.height)
            )
        }
    }
}


@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    searchEnabled: Boolean,
    searchStyle: TextStyle,
    searchLabel: String
) {
    val focusRequester = remember { FocusRequester() }
    val sizeOfLabel = searchLabel.findScreenSize(searchStyle)

    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.focusRequester(focusRequester),
        enabled = true,
        readOnly = false,
        textStyle = searchStyle,
        keyboardOptions = KeyboardOptions(),
        keyboardActions = KeyboardActions(),
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        onTextLayout = {},
        interactionSource = null,
        cursorBrush = SolidColor(searchStyle.color),
        decorationBox =
        {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
            ) {
                it()

                AnimatedContent(
                    targetState = searchEnabled,
                    label = "",
                    modifier = Modifier.size(sizeOfLabel.width, sizeOfLabel.height),
//                    transitionSpec = {
//                        (fadeIn(animationSpec = tween(200, delayMillis = 60)) +
//                                scaleIn(
//                                    initialScale = 0.99f,
//                                    animationSpec = tween(220, delayMillis = 90)
//                                )
//                                )
//                            .togetherWith(
//                                fadeOut(animationSpec = tween(200))
//                            )
                    //  },
                ) {
                    when (it) {
                        true -> {}

                        false -> Text(
                            text = searchLabel,
                            style = searchStyle,
                            modifier = Modifier.mySharedElement("NavCardStartSelectText")
                        )
                    }
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


@Composable
@AppPreview.Brightness
private fun Preview() = ClpTheme {
    val query = remember { mutableStateOf("") }
    val searchEnabled by remember {
        derivedStateOf { query.value.isNotBlank() }
    }

    SearchFieldContainer(
        query = query.value,
        onBackClick = { },
        onQueryChange = { query.value = it },
        searchEnabled = searchEnabled,
        searchStyle = AppTypo.titleSmall.copy(color = AppColor.onSurface)
    )
}
