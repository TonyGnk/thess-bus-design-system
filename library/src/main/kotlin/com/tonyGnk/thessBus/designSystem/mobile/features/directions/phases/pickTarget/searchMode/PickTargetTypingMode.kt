package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.pickTarget.searchMode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.SelectTargetItem
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.SelectTargetItemFakeData
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize

@Composable
fun PickTargetTypingMode(
    onResultClick: (Int, Boolean) -> Unit,
    results: List<SelectTargetItem> = SelectTargetItemFakeData
) {
    LazyColumn {
        if (results.isEmpty()) {
            item {
                Text(text = "No results")
            }
        }
        items(items = results, key = { it.id }) { result ->
            Text(text = result.title)
            ResultLayout(
                result = result,
                onClick = {
                    onResultClick(result.id, result.isSinglePoint)
                }
            )
        }
    }
}

@Composable
fun ResultLayout(
    result: SelectTargetItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = result.title
    val subTitle = result.subTitle

    val titleStyle = AppTypo.titleMedium
    val subTitleStyle = AppTypo.bodyMedium

    val totalHeight =
        title.findScreenSize(titleStyle).height + subTitle.findScreenSize(subTitleStyle).height
    val shape = RoundedCornerShape(NavCardProperties.IN_CORNERS.dp)

    val paddingOfTheBackButtonInSearch = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp + 12.dp
    val paddingForTheIcon = 7.dp
    val padding = paddingOfTheBackButtonInSearch - paddingForTheIcon

    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        SurfaceWithShadows(
            color = AppColor.surfaceContainerLowest,
            shadowElevation = 1,
            onClick = onClick,
            shape = RoundedCornerShape(NavCardProperties.IN_CORNERS.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(padding),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = padding,
                        vertical = padding
                    )
            ) {
                SurfaceWithShadows(
                    color = AppColor.surfaceContainerLowest,
                    shadowElevation = 0,
                    tonalElevation = 0,
                    shape = AppShape.round10,
                    modifier = Modifier.size(totalHeight)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(7.dp)
                    ) {
                        Icon(
                            iconRes = result.category.iconRes,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Column {
                    Text(
                        text = title,
                        style = titleStyle,
                        color = AppColor.onSurface
                    )
                    Text(
                        text = subTitle,
                        style = subTitleStyle,
                        color = AppColor.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }

//        Row(
//            horizontalArrangement = Arrangement.spacedBy(padding),
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = modifier
//                .fillMaxWidth()
//                .clip(shape)
//                .background(AppColor.surfaceContainer)
//                .selectable(
//                    selected = false,
//                    role = Role.Button,
//                    interactionSource = remember { MutableInteractionSource() },
//                    indication = rememberRipple(),
//                    onClick = onClick
//                )
//                .padding(
//                    horizontal = padding,
//                    vertical = padding
//                )
//        ) {
//            SurfaceWithShadows(
//                color = AppColor.surfaceContainerLowest,
//                shadowElevation = 1,
//                shape = AppShape.round10,
//                modifier = Modifier.size(totalHeight)
//            ) {
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier.padding(7.dp)
//                ) {
//                    Icon(
//                        iconRes = result.category.iconRes,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                }
//            }
//            Column {
//                Text(
//                    text = title,
//                    style = titleStyle,
//                    color = AppColor.onSurface
//                )
//                Text(
//                    text = subTitle,
//                    style = subTitleStyle,
//                    color = AppColor.onSurface.copy(alpha = 0.7f)
//                )
//            }
//        }
    }


}

@Composable
@AppPreview.Dark
private fun Preview() = ClpTheme {
    val results = SelectTargetItemFakeData

    PickTargetTypingMode(
        results = results, onResultClick = { _, _ -> }
    )
}
