package com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.selectTarget.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.directions.phases.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize

@Composable
internal fun NavCardSelectQuickOptions(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState
) {
    val text = "Επιλέξτε σημείο στο χάρτη"
    val style = AppTypo.titleMedium
    val size = text.findScreenSize(style).height - 1.dp
    val shape = RoundedCornerShape(NavCardProperties.IN_CORNERS.dp)

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            item {
//                TonalButton(
//                    iconRes = AppIcon.routes,
//                    text = "Στάσεις",
//                    padding = PaddingValues(18.dp),
//                    modifier = Modifier
//                )
//            }
//            item {
//                TonalButton(
//                    iconRes = AppIcon.routes,
//                    text = "Διαδρομές",
//                    padding = PaddingValues(18.dp),
//                    modifier = Modifier
//                )
//            }
//            item {
//                TonalButton(
//                    iconRes = AppIcon.category,
//                    text = "Κατηγορίες",
//                    padding = PaddingValues(18.dp),
//                    modifier = Modifier.padding(end = 0.dp)
//                )
//            }
//        }
        //spacer
        //       Spacer(modifier = Modifier.size(30.dp))

        MyItem("Saved Places")
        // MyItem("Διαδρομές")
        // Text("Πρόσφατα")

//        SurfaceWithShadows(
//            shape = shape,
//            color = AppColor.surfaceContainerLowest,
//            modifier = modifier
//                .fillMaxWidth()
//                .zIndex(1f)
//                .clip(shape)
//                .clickable { },
//        ) {
//            Row(
//                modifier = Modifier.padding(NavCardProperties.IN_PADDING.dp),
//                horizontalArrangement = Arrangement.spacedBy(NavCardProperties.IN_PADDING.dp),
//            ) {
//                Icon(
//                    iconRes = R.drawable.map_marker,
//                    color = AppColor.onSurface,
//                    modifier = Modifier.size(size)
//                )
//                Text(
//                    text = text,
//                    style = style,
//                    color = AppColor.onSurface,
//                )
//            }
//
//        }
    }
}


@AppPreview.Dark
@Composable
private fun Preview() = ClpTheme {
    NavCardSelectQuickOptions(
        lazyListState = rememberLazyListState()
    )
}


@Composable
fun MyItem(
    label: String,
) {
    val text = "Επιλέξτε σημείο στο χάρτη"
    val style = AppTypo.titleMedium
    val size = text.findScreenSize(style).height - 1.dp
    val shape = RoundedCornerShape(NavCardProperties.IN_CORNERS.dp)

    Column(
        modifier = Modifier.padding(bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = style)
            // TextButton(text = "Επεξεργασία", padding = PaddingValues(9.dp))
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalAlignment = Alignment.Start,
            // modifier = Modifier.padding(10.dp)
        ) {
            MyBox()
            MyBox()
            MyBox()
            MyBox()
            MyBox()
            MyBox()
        }

    }
}


@Composable
private fun MyBox() {
    SurfaceWithShadows(
        shape = RoundedCornerShape(NavCardProperties.IN_CORNERS.dp),
        color = AppColor.surfaceContainerLowest,
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
    ) {
        Column {
            Text("Τοποθεσία 1", modifier = Modifier.padding(17.dp))
        }

    }
}
