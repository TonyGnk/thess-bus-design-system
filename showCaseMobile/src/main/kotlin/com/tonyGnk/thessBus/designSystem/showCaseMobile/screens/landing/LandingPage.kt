package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing

import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.FilledTonalButton
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.FilledButton
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.TonalButton
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Surface
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R

@Composable
fun LandingPage(
    navigateToNavBar: () -> Unit = {}
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.padding(18.dp))
            LandingPageAppBar()
            Spacer(modifier = Modifier.padding(9.dp))
            LandingPageAppBarDescription()
            Spacer(modifier = Modifier.padding(9.dp))
            UpdateButton()
            Spacer(modifier = Modifier.padding(9.dp))
            ListContainer(
                modifier = Modifier.weight(1f),
                navigateToNavBar = navigateToNavBar
            )
        }
    }
}

@Composable
fun ListContainer(
    modifier: Modifier = Modifier,
    navigateToNavBar: () -> Unit
) {
    Surface(
        color = AppColor.surfaceContainerLowest,
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            item {
                ListItem(
                    label = R.string.navigation_bar,
                    navigateToNavBar = navigateToNavBar
                )
            }
        }
    }
}

@Composable
fun UpdateButton() {
    val context = LocalContext.current

    Box(
        modifier = Modifier.padding(horizontal = 18.dp)
    ) {
        FilledButton(
            padding = 10,
            onClick = {
                val intent =
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(
                            "https://play.google.com/store/apps/details?id=com.tonyGnk.thessBus.designSystem.mobile"
                        )
                    )
                context.startActivity(intent)
            },
            iconRes = R.drawable.google_play_store_svgrepo_com__1_,
            text = stringResource(id = R.string.get_updates),
            modifier = Modifier
        )
    }
}


@Composable
fun ListItem(
    @StringRes label: Int,
    navigateToNavBar: () -> Unit
) {
    val paddingValues = 11.dp
    Column {
        com.tonyGnk.thessBus.designSystem.mobile.components.containment.ListItem(
            padding = PaddingValues(paddingValues),
            onClick = navigateToNavBar,
//            modifier = Modifier.padding(
//                8.dp
//            )
        ) {
            Text(text = stringResource(id = label))
        }
        Box(modifier = Modifier.padding(horizontal = paddingValues + 14.dp)) {
            // HorizontalDivider()
        }
    }
}

@AppPreview.Brightness
@Composable
fun LandingPagePreview() = ClpTheme {
    LandingPage()
}
