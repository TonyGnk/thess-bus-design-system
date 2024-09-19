package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor

@Composable
fun Pixel4Phone(
    modifier: Modifier = Modifier,
    phoneColor: Color = Color.Black,
    content: @Composable (Modifier) -> Unit = {}
) {
    val gradientBlueBrush = Brush.sweepGradient(
        colors = listOf(
            Color(0xFF141216),
            Color(0xFF0E0F1E),
            Color(0xFF3a4978),
            Color(0xFF0E0F1E),
            Color(0xFF141216),
            Color(0xFF281D25),
            Color(0xFF141216),
        )
    )

    Column(
        modifier = modifier
            .border(3.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(11))
            .background(phoneColor, RoundedCornerShape(11))
            .padding(12.dp)
            .clip(RoundedCornerShape(9))
    ) {
        //Top Bezel
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f)
            ) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .weight(3f)
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(4f)
                        .padding(2.dp)
                        .background(Color.DarkGray.copy(alpha = 0.8f), RoundedCornerShape(50))
                )
                Box(
                    Modifier
                        .fillMaxHeight()
                        .weight(3f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(9f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(4f)
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(3f)
                        .aspectRatio(1f)
                        .padding(3.dp)
                        .border(2.dp, Color.DarkGray.copy(alpha = 0.8f), CircleShape)
                        .background(gradientBlueBrush, CircleShape)
                    //

                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(36f)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
            )
        }
        //Actual Screen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(20f)
                .background(AppColor.surfaceContainer, RoundedCornerShape(8))
                .clip(RoundedCornerShape(8))
        ) {
            content(Modifier)
        }
        //Bottom Bezel
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
    }
}
