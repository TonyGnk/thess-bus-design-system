package com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.searchMode

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.data.NavCardCategory
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.data.NavCardSelectItem
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.data.NavCardSelectItemFakeData
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import java.util.Locale

@Composable
fun NavCardSelectMode(
    detailedView: Boolean,
    results: List<NavCardSelectItem>
) {
    val isGreek = isLanguageGreek(LocalContext.current)
    val selectedX: MutableState<Double?> = remember { mutableStateOf(null) }
    val selectedY: MutableState<Double?> = remember { mutableStateOf(null) }

    SurfaceWithShadows(
        color = AppColor.surfaceContainerLowest,
        shape = RoundedCornerShape(NavCardProperties.SMALL_CORNERS.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(NavCardProperties.SEARCH_PADDING.dp),
                verticalArrangement = Arrangement.spacedBy(19.dp)
            ) {
                items(
                    items = results, key = { it.id }
                ) { item ->
                    if (detailedView) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(0.dp),
                            modifier = Modifier.selectable(
                                selected = true,
                                onClick = {
                                    selectedX.value = item.x
                                    selectedY.value = item.y
                                }
                            )
                        ) {
                            Text(
                                size = 17.sp,
                                //                        text = if (isGreek) item.elName else item.enName
                                text = item.elName + " - " + item.enName,
                            )
                            Text(
                                size = 11.sp,
                                //                        text = if (isGreek) item.elArea else item.enArea + item.category,
                                text = item.elArea + " - " + item.enArea + " - " + item.category,

                                color = AppColor.onSurface.copy(alpha = 0.7f)
                            )
                            Text(
                                size = 9.sp,
                                text = item.x.toString() + "  " + item.y.toString(),
                                color = AppColor.onSurface.copy(alpha = 0.4f)
                            )
                        }
                    } else ResultLayout(item)
                }
            }
        }
    }
}

@Composable
fun ResultLayout(
    result: NavCardSelectItem
) {
    val title = result.elName.ifBlank {
        result.enName.ifBlank {
            result.elArea.ifBlank {
                result.enArea.ifBlank {
                    "Unknown"
                }
            }
        }
    }
    val titleStyle = AppTypo.titleMedium

    val description = result.elArea.ifBlank { result.enArea }
    val descriptionStyle = AppTypo.bodyMedium


    val totalHeight =
        title.findScreenSize(titleStyle).height + description.findScreenSize(descriptionStyle).height

    Row(
        horizontalArrangement = Arrangement.spacedBy(23.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
    ) {
        SurfaceWithShadows(
            color = AppColor.surfaceContainer,
            shadowElevation = 1,
            shape = AppShape.round10,
            modifier = Modifier
                .size(totalHeight)
        ) {
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier.padding(3.dp)
            ) {
                Icon(
                    iconRes = result.category.iconRes,
                    modifier = Modifier

                    //.fillMaxSize() //size(totalHeight)
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
                text = description,
                style = descriptionStyle,
                color = AppColor.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
@AppPreview.Brightness
private fun Preview() = ClpTheme {
    val results = NavCardSelectItemFakeData

    NavCardSelectMode(
        results = results, detailedView = false
    )
}

@SuppressLint("ObsoleteSdkInt")
fun isLanguageGreek(context: Context): Boolean {
    val locale: Locale = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        context.resources.configuration.locales[0]
    } else {
        @Suppress("DEPRECATION")
        context.resources.configuration.locale
    }
    return locale.language == "el"
}
