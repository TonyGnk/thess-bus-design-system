package com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.selectDestination.data

import androidx.compose.runtime.Stable

@Stable
data class NavCardSelectItem(
    val id: Int,
    val elName: String,
    val enName: String,
    val elArea: String,
    val enArea: String,
    val category: String = "",
    val x: Double = 22.9587498,
    val y: Double = 22.9587498
)
