package com.tonyGnk.thessBus.designSystem.mobile.components.core.icons


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
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import androidx.compose.material3.Icon as MaterialIcon

@Composable
fun Icon(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int = 0,
    contentDescription: String = "",
    color: Color = LocalContentColor.current
) {
    if (iconRes != 0) MaterialIcon(
        painter = painterResource(iconRes),
        tint = color,
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun Icon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    contentDescription: String = "",
    color: Color = LocalContentColor.current
) {
    if (imageVector != null) MaterialIcon(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = color
    )
}

@Composable
@AppPreview.Brightness
private fun IconPreview() = ClpTheme {
    Icon(
        iconRes = R.drawable.search
    )
}
