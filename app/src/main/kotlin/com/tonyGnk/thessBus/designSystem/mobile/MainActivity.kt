package com.tonyGnk.thessBus.designSystem.mobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.icon.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.bar.NavigationBar
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.NavigationCardPreview
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClpTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppColor.surfaceContainer)
                        .statusBarsPadding(),
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        // SpeechToTextScreen(viewModel)
                        NavigationCardPreview()
                        //  item { NavigationCard() }
                        //  item { NavigationCardSelect() }
                        IconButton(
                            color = AppColor.transparent,
                            contentColor = AppColor.transparent,
                            modifier = Modifier.size(38.dp),
                            iconRes = AppIcon.search, onClick = {
                                val intent =
                                    Intent(
                                        Intent.ACTION_VIEW, Uri.parse(
                                            "https://play.google.com/store/apps/details?id=com.tonyGnk.thessBus.designSystem.mobile"
                                        )
                                    )
                                startActivity(intent)
                            }
                        )
                    }
                    NavigationBar(Triple(true, true, true))
                }
            }
        }
    }
}


@Composable
@AppPreview.Light
private fun Preview() = ClpTheme {
    Text(text = "Hello")
}
