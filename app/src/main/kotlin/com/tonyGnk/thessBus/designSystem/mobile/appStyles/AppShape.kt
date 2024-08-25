package com.tonyGnk.thessBus.designSystem.mobile.appStyles

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

object AppShape {
    val square: Shape
        @Composable
        get() = RectangleShape

    val round05: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(5.dp)

    val round10: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(10.dp)

    val round15: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(15.dp)

    val round20: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(20.dp)

    val round25: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(25.dp)

    val round30: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(30.dp)

    val round40: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(40.dp)

    val round50: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(50.dp)

}
