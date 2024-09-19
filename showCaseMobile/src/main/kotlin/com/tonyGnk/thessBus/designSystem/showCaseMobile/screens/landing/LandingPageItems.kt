package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.FilledButton
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R

@Composable
fun LandingPageAppBar() {
    Text(
        text = stringResource(id = R.string.large_app_name),
        style = AppTypo.appBar,
    )
}

@Composable
fun LandingPageAppBarDescription() {
    Text(
        text = stringResource(id = R.string.app_description),
        style = AppTypo.bodyMedium,
    )
}

@Composable
fun UpdateButton() {
    val context = LocalContext.current

    FilledButton(
        padding = PaddingValues(10.dp),
        onClick = {
            val intent =
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://play.google.com/store/apps/details?id=com.tonyGnk.thessBus.designSystem.mobile"
                    )
                )
            context.startActivity(intent)
        },
        iconRes = R.drawable.play_store,
        text = stringResource(id = R.string.get_updates),
        modifier = Modifier
    )
}
