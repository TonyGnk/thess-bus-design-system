package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.shared.searchContainer.SearchButton
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme

@Stable
object NavCardProperties {
    const val OUTER_PADDING = 20
    const val OUTER_CORNERS = 22//33

    const val IN_PADDING = OUTER_PADDING.div(1.3f)  //18
    const val IN_CORNERS = OUTER_CORNERS.div(1.3f)
    const val SEARCH_ARRANGEMENT = OUTER_PADDING * 0.75f
    const val SEARCH_LABEL = "Search here"
    val searchTextStyle: TextStyle
        @Composable
        get() = AppTypo.titleMedium.copy(color = AppColor.onSurface)
}

@Composable
fun DirectionsStart(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(0.dp),
    onSearchClick: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(NavCardProperties.SEARCH_ARRANGEMENT.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(padding)
            .fillMaxWidth()
            .clip(RoundedCornerShape(NavCardProperties.OUTER_CORNERS.dp))
            .background(AppColor.primary)
            .padding(NavCardProperties.OUTER_PADDING.dp),
    ) {
        LargeLabel()
        SearchButton(
            searchLabel = NavCardProperties.SEARCH_LABEL,
            onClick = onSearchClick,
            color = AppColor.inverseOnSurface,
            rippleColor = AppColor.inverseSurface
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
@AppPreview.Dark
private fun Preview() = ClpTheme {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        DirectionsStart()
    }
}
