package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.extendedWindowInsets
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getExtendedWindowInsets
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R

private const val FLAT_ICON_ICON = 0xFF17D1C6

private const val FLAT_ICON_BACK = 0xFF081126

@Composable
fun IconsGridPage(onBack: () -> Unit = {}) {

    val context = LocalContext.current
    val navigateToFlatIcon = remember {
        {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flaticon.com/"))
            context.startActivity(intent)
        }
    }

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = getExtendedWindowInsets(),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
        columns = GridCells.Adaptive(minSize = 72.dp)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            BasicTopBar(
                label = stringResource(R.string.landing_destinations_icons),
                backIcon = TopBarBackIcon(
                    onBack = onBack
                ),
                applyHorizontalPadding = false
            )
        }

        items(
            items = AppIcon.entries,
            key = { it.name },
            contentType = { "icon" } // Optional: helps with performance
        ) { appIcon ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = AppColor.surfaceLowest, shape = AppShape.round15
                    )
                    .padding(16.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    iconRes = appIcon.iconRes,
                    contentDescription = "",
                    color = AppColor.onSurface
                )
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .extendedWindowInsets()
            .padding(
                start = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp,
                bottom = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp,
                end = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
            )
    ) {
        SurfaceWithShadows(
            color = Color(FLAT_ICON_BACK),
            contentColor = Color.White,
            shape = AppShape.round15,
            onClick = navigateToFlatIcon,
            shadowElevation = 0,
            modifier = Modifier
                .widthIn(max = 500.dp)
                .padding(8.dp)
                .border(
                    width = 1.dp,
                    color = AppColor.onSurface.copy(alpha = 0.2f),
                    shape = AppShape.round15
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .padding(DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.im_flaticon_by_color_negative_hor),
                    contentDescription = null,
                    modifier = Modifier
                        .height(24.dp)
                        .weight(1f),
                    alignment = Alignment.CenterStart
                )

                Icon(
                    color = Color(FLAT_ICON_ICON),
                    modifier = Modifier.size(24.dp),
                    iconRes = AppIcon.ArrowRight.iconRes,
                )
            }
        }
    }
}


@AppPreview.Dark
@Composable
private fun Preview() = ThessBusTheme {
    IconsGridPage()
}
