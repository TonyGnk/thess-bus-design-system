package com.tonyGnk.thessBus.designSystem.mobile.components.core.text

import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import androidx.compose.material3.HorizontalDivider as MaterialHorizontalDivider
import androidx.compose.material3.Switch as MaterialSwitch
import androidx.compose.material3.Text as MaterialText

@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = AppColor.onSurface,
    weight: FontWeight? = null,
    size: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
    fontStyle: FontStyle? = null,
) {
    MaterialText(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        fontWeight = weight,
        fontSize = size,
        maxLines = maxLines,
        softWrap = softWrap,
        textAlign = textAlign,
        fontStyle = fontStyle,
    )
}

@Composable
fun Text(
    annotatedString: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = Color.Unspecified,
    weight: FontWeight? = null,
    size: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
    fontStyle: FontStyle? = null,
) {
    MaterialText(
        text = annotatedString,
        modifier = modifier,
        style = style,
        color = color,
        fontWeight = weight,
        fontSize = size,
        maxLines = maxLines,
        softWrap = softWrap,
        textAlign = textAlign,
        fontStyle = fontStyle,
    )
}

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    thumbContent: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
) {
    MaterialSwitch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        thumbContent = thumbContent,
        enabled = enabled,
    )
}

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color,
) {
    MaterialHorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}
