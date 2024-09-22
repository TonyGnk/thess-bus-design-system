package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.DirectionsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement

@Stable
object LocationsProperties {
    const val OUTER_PADDING = 20
    const val OUTER_CORNERS = 22

    const val IN_PADDING = OUTER_PADDING.div(1.3f)
    const val IN_CORNERS = OUTER_CORNERS.div(1.3f)
    const val SEARCH_ARRANGEMENT = OUTER_PADDING * 0.75f
    const val SEARCH_LABEL = "Search here"
    val searchTextStyle: TextStyle
        @Composable
        get() = AppTypo.titleMedium.copy(color = AppColor.onSurface)
}

@Stable
data class LocationsCardItems(
    val onSearchClick: () -> Unit,
    val sharedElementCard: String,
    val sharedElementText: String,
    val sharedElementMagnifier: String
) {
    companion object {
        val preview = LocationsCardItems(
            onSearchClick = {},
            sharedElementCard = "",
            sharedElementText = "",
            sharedElementMagnifier = ""
        )
    }
}

@Composable
fun LocationsCard(
    modifier: Modifier = Modifier,
    items: LocationsCardItems = LocationsCardItems.preview
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(LocationsProperties.SEARCH_ARRANGEMENT.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(LocationsProperties.OUTER_CORNERS.dp))
            .background(AppColor.primary)
            .padding(LocationsProperties.OUTER_PADDING.dp),
    ) {
        LargeLabel()
        SearchButton(
            searchLabel = LocationsProperties.SEARCH_LABEL,
            onClick = items.onSearchClick,
            color = AppColor.inverseOnSurface,
            rippleColor = AppColor.inverseSurface,
            sharedElementTag = items.sharedElementCard,
            sharedElementTextTag = items.sharedElementText,
        )
    }
}

@Composable
private fun LargeLabel() = Text(
    text = "Where are you going?",
    style = AppTypo.titleLarge,
    weight = FontWeight.Black,
    color = AppColor.onPrimary,
    textAlign = TextAlign.Start,
    modifier = Modifier.fillMaxWidth()
)

@Composable
fun SearchButton(
    searchLabel: String,
    onClick: () -> Unit,
    color: Color,
    rippleColor: Color,
    sharedElementTag: String,
    sharedElementTextTag: String,
    modifier: Modifier = Modifier
) {
    val searchStyle = LocationsProperties.searchTextStyle
    val sizeInScreen = searchLabel.findScreenSize(searchStyle).height - 1.dp

    SurfaceWithShadows(
        shadowElevation = 2,
        shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
        color = color,
        contentColor = rippleColor,
        onClick = onClick,
        modifier = modifier.mySharedElement(sharedElementTag)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
            modifier = modifier.padding(
                start = LocationsProperties.IN_PADDING.dp,
                end = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp,
            )
        ) {
            Text(
                text = searchLabel,
                style = searchStyle,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = LocationsProperties.IN_PADDING.dp)
                    .mySharedElement(sharedElementTextTag)
            )
            IconButton(
                iconRes = AppIcon.search,
                color = AppColor.transparent,
                modifier = Modifier
                    .size(sizeInScreen)
                    .mySharedElement("SearchContainerMagnifier")
            )
        }
    }
}


@Composable
@AppPreview.Dark
private fun Preview() = ClpTheme {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        LocationsCard()
    }
}
