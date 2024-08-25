package com.tonyGnk.thessBus.designSystem.mobile.components.icon


import androidx.annotation.DrawableRes
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import androidx.compose.material3.Icon as MaterialIcon

@Composable
fun Icon(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    contentDescription: String = "",
    color: Color = LocalContentColor.current
) {
    MaterialIcon(
        painter = painterResource(iconRes),
        tint = color,
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun Icon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    tint: Color = LocalContentColor.current
) {
    MaterialIcon(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
@AppPreview.Brightness
fun IconPreview() {
    Icon(
        iconRes = R.drawable.search
    )
}
