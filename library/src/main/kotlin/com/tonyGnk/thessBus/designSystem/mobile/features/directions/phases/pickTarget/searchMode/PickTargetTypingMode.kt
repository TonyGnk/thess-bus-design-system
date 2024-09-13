package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.searchMode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetItem
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.PickTargetFakeResults
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize

@Composable
internal fun LazyListOfPickTargetItems(
    modifier: Modifier = Modifier,
    onClick: (PickTargetItem) -> Unit,
    state: LazyListState,
    horizontalPadding: Int,
    items: List<PickTargetItem>
) {
    LazyColumn(
        state = state,
        modifier = modifier.padding(horizontal = horizontalPadding.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(items = items, key = { it.id }) { result ->
            PickTargetResult(
                result = result,
                onClick = {
                    onClick(result)
                }
            )
        }
    }
}

@Composable
internal fun PickTargetResult(
    result: PickTargetItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = result.title
    val subTitle = result.subTitle

    val titleStyle = AppTypo.bodyLarge
    val subTitleStyle = AppTypo.labelMedium

    val totalHeight =
        title.findScreenSize(titleStyle).height + subTitle.findScreenSize(subTitleStyle).height

    // Trying to make exactly the same layout as the search bar
    val paddingOfTheBackButtonInSearch = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp + 12.dp
    val paddingForTheIcon = 7.dp
    val padding = paddingOfTheBackButtonInSearch - paddingForTheIcon

    SurfaceWithShadows(
        color = AppColor.surfaceContainerLowest,
        shadowElevation = 1,
        modifier = modifier.padding(vertical = 2.dp),
        onClick = onClick,
        shape = RoundedCornerShape(NavCardProperties.IN_CORNERS.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(padding),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(totalHeight)
                    .padding(7.dp)
            ) {
                Icon(
                    iconRes = result.iconRes,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    style = titleStyle,
                    color = AppColor.onSurface
                )
                if (subTitle.isNotBlank()) Text(
                    text = subTitle,
                    style = subTitleStyle,
                    color = AppColor.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }


}

@Composable
@AppPreview.Dark
private fun Preview() = ClpTheme {
    val results = PickTargetFakeResults

    LazyListOfPickTargetItems(
        items = results,
        onClick = { _ -> },
        state = rememberLazyListState(),
        horizontalPadding = 0
    )
}
