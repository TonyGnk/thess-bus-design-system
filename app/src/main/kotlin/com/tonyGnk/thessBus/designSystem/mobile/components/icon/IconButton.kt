package com.tonyGnk.thessBus.designSystem.mobile.components.icon


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.IconButton as MaterialIconButton
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import androidx.compose.material3.Button as MaterialButton

@Composable
fun IconButton(
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    color: Color = AppColor.primary,
    contentColor: Color = AppColor.onPrimary,
    contentDescription: String = "",
) {
    MaterialIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = color,
            contentColor = contentColor
        ),
        modifier = buttonModifier
    ) {
        Icon(
            iconRes = iconRes,
            color = contentColor,
            contentDescription = contentDescription,
            modifier = modifier
        )
    }
}

@Composable
fun ButtonZ(
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    color: Color = AppColor.primary,
    contentColor: Color = AppColor.onPrimary,
    shape: Shape = AppShape.round50
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = color, shape = shape)
            .clip(shape)
            .clickable { onClick() }
    ) {
        Icon(
            iconRes = iconRes,
            color = contentColor,
            modifier = Modifier.padding(2.dp)
        )
    }
}


@AppPreview.Light
@Composable
private fun PreviewIconButton() = ClpTheme {
    Box(
        modifier = Modifier.background(AppColor.background)
    ) {
        IconButton(iconRes = AppIcon.search)
    }
}

@AppPreview.Light
@Composable
private fun PreviewIconButtonZ() = ClpTheme {
    Box(Modifier.background(AppColor.background)) {
//        ButtonZ(
//            // modifier =Modifier.width(44.dp).height(11.dp)
//        )
        Text("Hello", style = AppTypo.bodyMedium)
    }
}
