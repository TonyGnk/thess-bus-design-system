package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R

@Composable
fun LandingPageAppBar() {
    Text(
        text = stringResource(id = R.string.large_app_name),
        style = AppTypo.topBar,
        modifier = Modifier.padding(horizontal = 18.dp)
    )
}

@Composable
fun LandingPageAppBarDescription() {
    Text(
        text = stringResource(id = R.string.app_desctiption),
        style = AppTypo.bodyMedium,
        modifier = Modifier.padding(horizontal = 18.dp)
    )
}
