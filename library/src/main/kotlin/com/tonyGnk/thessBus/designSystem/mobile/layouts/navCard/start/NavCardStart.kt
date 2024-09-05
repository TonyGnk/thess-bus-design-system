package com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.ListItemSurface
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement

@Stable
object NavCardProperties {
    const val SEARCH_PADDING = 18
    const val LARGE_CORNERS = 33
    const val SEARCH_ARRANGEMENT = SEARCH_PADDING.div(1.5f)
    const val SMALL_CORNERS = LARGE_CORNERS - SEARCH_PADDING
    const val SEARCH_LABEL = "Search here"
}

@Composable
fun NavCardStart(
    modifier: Modifier = Modifier,
    largeLabel: String = "Where are you going?",
    onSearchClick: () -> Unit = {},
) {
    val searchStyle = AppTypo.titleMedium.copy(color = AppColor.onSurface)

    Column(
        verticalArrangement = Arrangement.spacedBy(NavCardProperties.SEARCH_ARRANGEMENT.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .clip(RoundedCornerShape(NavCardProperties.LARGE_CORNERS.dp))
            .background(AppColor.primary)
            .padding(NavCardProperties.SEARCH_PADDING.dp)
        ,
    ) {
        LargeLabel(largeLabel)
        NavigationListItem(
            searchStyle = searchStyle,
            searchLabel = NavCardProperties.SEARCH_LABEL,
            onClick = onSearchClick,
        )
    }
}

@Composable
private fun LargeLabel(largeLabel: String) {
    Text(
        text = largeLabel,
        style = AppTypo.titleLarge,
        weight = FontWeight.Black,
        color = AppColor.onPrimary,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NavigationListItem(
    searchLabel: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    searchStyle: TextStyle
) {
    val sizeInScreen = searchLabel.findScreenSize(searchStyle)

    ListItemSurface(
        onClick = onClick,
        shape = RoundedCornerShape(NavCardProperties.SMALL_CORNERS.dp),
        tonalElevation = 0.dp,
        color = AppColor.inverseOnSurface,
        shadowElevation = 2.dp,
        padding = PaddingValues(NavCardProperties.SEARCH_PADDING.dp),
        modifier = modifier.mySharedElement("NavCardStartSelect")
    ) {
        Row {
            Text(
                text = searchLabel,
                style = searchStyle,
                modifier = Modifier
                    .weight(1f)
                    .mySharedElement("NavCardStartSelectText")
            )
            Icon(
                iconRes = AppIcon.search,
                color = AppColor.onSurface,
                modifier = Modifier
                    .size(sizeInScreen.height)
                    .mySharedElement("NavCardStartSelectMagnifier")
            )
        }
    }
}


@Composable
@AppPreview.Brightness
private fun Preview() = ClpTheme {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        NavCardStart()
    }
}
