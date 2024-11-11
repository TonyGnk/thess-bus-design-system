package com.tonyGnk.thessBus.designSystem.mobile.components.core.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
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
    textTargetWidth: Dp,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    weight: FontWeight? = null,
    textAlign: TextAlign? = null,
    fontStyle: FontStyle? = null,
) {
    val gradientBrush = Brush.horizontalGradient(
        0f to backgroundColor.copy(alpha = 0.1f),
        1f to backgroundColor.copy(alpha = 0.99f)
    )
    val textSize = text.findScreenSize(style) //82.54
    val remainingWidth = textSize.width - textTargetWidth //3
    val widthOfFade = if (remainingWidth >= 1.dp) {
        textTargetWidth.div(3.2f)  // "abc".findScreenSize(style).width
    } else 0.dp

    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = modifier
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = style,
            //   color = style.color,
            weight = weight,
            //  fontWeight = weight,
            //     size = style.fontSize,

            //    fontSize = ,
            maxLines = 1,
            softWrap = false,
            textAlign = textAlign,
            fontStyle = fontStyle,
        )
        Box(
            modifier = Modifier
                .width(widthOfFade)
                .height(textSize.height)
                .background(gradientBrush)
        )
    }
}

@AppPreview.Dark
@Composable
private fun Preview() = ThessBusTheme {
    val text = "Πανεπιστήμιο"
    val style = AppTypo.bodySmall.copy(
        color = AppColor.primary,
    )
    Column {
        Box(
            Modifier
                .width(79.dp)
                .background(AppColor.surfaceLowest)
        ) {
            TextFade(
                text = text,
                style = style,
                backgroundColor = AppColor.surfaceLowest,
                textTargetWidth = 79.dp
            )
        }
        Text(
            text.findScreenSize(style).width.toString(),
            style = style
        )
    }
}
