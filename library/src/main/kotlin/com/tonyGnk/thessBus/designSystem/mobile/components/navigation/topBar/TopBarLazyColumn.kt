package com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.ClickableWithoutRipple
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getExtendedWindowInsets
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.iconSizeFor
import kotlinx.coroutines.launch

data class TopBarLazyColumnLeftSide(
    val iconRes: Int = AppIcon.Back.iconRes,
    val contentDescription: String = "",
    val destinationName: String = "Folders",
    val onClick: () -> Unit = {}
)

data class TopBarLazyColumnRightSide(
    val iconRes: Int = AppIcon.Add.iconRes,
    val contentDescription: String = "",
    val onClick: () -> Unit = {}
)

@Composable
fun TopBarLazyColumn(
    modifier: Modifier = Modifier,
    screenName: String = "Notes",
    leftSide: TopBarLazyColumnLeftSide = TopBarLazyColumnLeftSide(),
    rightSide: TopBarLazyColumnRightSide = TopBarLazyColumnRightSide(),
    content: LazyListScope.() -> Unit
) {
    val state = rememberLazyListState()

    val labelIsHiding = remember {
        derivedStateOf {
            state.firstVisibleItemIndex != 0
        }
    }
    val canScrollBackward = remember {
        derivedStateOf {
            state.canScrollBackward
        }
    }

    val textStyle = AppTypo.titleMedium.copy(
        fontSize = 18.sp,
        letterSpacing = (-0.35).sp
    )
    val heightOfText = "text".findScreenSize(textStyle).height
    val alpha by animateFloatAsState(
        animationSpec = tween(durationMillis = 300),
        targetValue = if (canScrollBackward.value) 0f else 1f,
        label = "alpha animation"
    )
    val smallColor = when (isSystemInDarkTheme()) {
        true -> AppColor.surfaceLowest
        false -> AppColor.background
    }
    val largeColor = when (isSystemInDarkTheme()) {
        true -> AppColor.background
        false -> AppColor.surfaceLowest
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(smallColor)
            .background(largeColor.copy(alpha))
            .padding(
                getExtendedWindowInsets(
                    DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
                ),
            )
    ) {
        Header(
            screenName = screenName,
            topBarLazyColumnLeftSide = leftSide,
            listState = state,
            textStyle = textStyle,
            labelIsHiding = labelIsHiding.value,
            topBarLazyColumnRightSide = rightSide,
            heightOfText = heightOfText
        )
        LazyColumn(
            state = state,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(largeColor)
        ) {
            item(
                key = "label",
            ) {
                Text(
                    text = screenName,
                    style = AppTypo.headlineLarge,
                    weight = FontWeight.W900,
                    modifier = Modifier
                        .padding(
                            horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
                        )
                )
            }
            content()
        }
    }

}

@Composable
private fun Header(
    screenName: String,
    topBarLazyColumnLeftSide: TopBarLazyColumnLeftSide,
    topBarLazyColumnRightSide: TopBarLazyColumnRightSide,
    listState: LazyListState,
    textStyle: TextStyle,
    labelIsHiding: Boolean,
    heightOfText: Dp,
) {
    val actionColor = AppColor.primary
    val scope = rememberCoroutineScope()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = DefaultScaffoldValues.SEMI_BEZEL_PADDING.dp,
                bottom = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp,
                end = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp,
            )
    ) {
        ClickableWithoutRipple(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.weight(1f),
            color = actionColor,
            onClick = topBarLazyColumnLeftSide.onClick
        ) { color ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.clip(AppShape.round30)
            ) {
                Icon(
                    modifier = Modifier.iconSizeFor(heightOfText),
                    iconRes = topBarLazyColumnLeftSide.iconRes,
                    contentDescription = topBarLazyColumnLeftSide.contentDescription,
                    color = color,
                )
                Text(
                    text = topBarLazyColumnLeftSide.destinationName,
                    style = textStyle.copy(color = color),
                )
            }
        }
        AnimatedVisibility(labelIsHiding) {
            ClickableWithoutRipple(
                contentAlignment = Alignment.CenterEnd,
                color = AppColor.onSurface,
                onClick = {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            ) { color ->
                Text(
                    text = screenName,
                    textAlign = TextAlign.Center,
                    style = textStyle.copy(color = color),
                    weight = FontWeight.Bold,
                )
            }
        }
        ClickableWithoutRipple(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.weight(1f),
            color = actionColor,
            onClick = topBarLazyColumnRightSide.onClick
        ) { color ->
            Icon(
                modifier = Modifier.iconSizeFor(heightOfText),
                color = color,
                contentDescription = topBarLazyColumnRightSide.contentDescription,
                iconRes = topBarLazyColumnRightSide.iconRes,
            )
        }
    }
}

@Composable
@AppPreview.Dark
private fun Preview() = ThessBusTheme {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBarLazyColumn() {}
    }
}
