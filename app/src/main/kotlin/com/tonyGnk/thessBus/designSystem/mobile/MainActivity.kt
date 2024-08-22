package com.tonyGnk.thessBus.designSystem.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.bar.NavigationBar
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
                        .background(AppColor.surfaceContainer),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    NavigationBar(Triple(true, true, true))
                }
            }
        }
    }
}


@Composable
@Preview
private fun Preview() {
    Text(text = "Hello")
}
