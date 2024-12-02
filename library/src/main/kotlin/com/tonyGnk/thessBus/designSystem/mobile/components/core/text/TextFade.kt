package com.tonyGnk.thessBus.designSystem.mobile.components.core.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize

@Composable
fun TextFade(
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    weight: FontWeight? = null,
    textAlign: TextAlign? = null,
    fontStyle: FontStyle? = null,
) {
    val density = LocalDensity.current
    var availableWidth by remember { mutableIntStateOf(1) }
    val realAvailableWidth = with(density) { availableWidth.toDp() }

    val gradientBrush = Brush.horizontalGradient(
        0f to backgroundColor.copy(alpha = 0.1f),
        1f to backgroundColor
    )

    val textSize = text.findScreenSize(style)
    val remainingWidth = textSize.width - realAvailableWidth
    val widthOfFade = if (remainingWidth >= 1.dp) {
        realAvailableWidth.div(3.2f)
    } else 0.dp

    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = modifier.onGloballyPositioned { layoutCoordinates ->
            availableWidth = layoutCoordinates.size.width
        },
    ) {

        Text(
            text = text,
            modifier = Modifier,
            style = style,
            weight = weight,
            maxLines = 1,
            softWrap = false,
            textAlign = textAlign,
            fontStyle = fontStyle,
        )
        Box(
            modifier = Modifier
                .width(widthOfFade)
                .offset { IntOffset(1, 0) }
                .height(textSize.height)
                .background(gradientBrush)
        )

    }

}

@AppPreview.Dark
@Composable
private fun Preview2() = ThessBusTheme {
    val text = "Αριστοτέλειο Πανεπιστήμιο Πανεπιστήμιο Θεσσαλονίκης"
    val style = AppTypo.bodySmall.copy(
        color = AppColor.primary,
    )
    Column(
        modifier = Modifier
            .width(290.dp)
            .background(AppColor.surface)
            .padding(8.dp)
    ) {
        Box(
            Modifier
                .background(AppColor.surfaceLowest)
                .padding(8.dp)
        ) {
            Row {
                TextFade(
                    modifier = Modifier.weight(1f),
                    text = text,
                    style = style,
                    backgroundColor = AppColor.surfaceLowest,
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextFade(
                    modifier = Modifier.weight(1f),
                    text = text,
                    style = style,
                    backgroundColor = AppColor.surfaceLowest,
                )
            }
        }
    }
}
