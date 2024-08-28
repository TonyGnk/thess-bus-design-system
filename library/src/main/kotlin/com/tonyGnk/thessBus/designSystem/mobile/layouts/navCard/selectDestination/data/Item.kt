package com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.tonyGnk.thessBus.designSystem.mobile.R

@Stable
data class NavCardSelectItem(
    val id: Int,
    val elName: String,
    val enName: String,
    val elArea: String,
    val enArea: String,
    val category: NavCardCategory = NavCardCategory.OTHER,
    val x: Double = 22.9587498,
    val y: Double = 22.9587498
)

enum class NavCardCategory(
    val osmLabel: String,
    @DrawableRes val iconRes: Int
) {
    PHARMACY(
        osmLabel = "pharmacy",
        iconRes = R.drawable.pharmacy
    ),
    BANK(
        osmLabel = "bank",
        iconRes = R.drawable.bank
    ),
    OTHER(
        osmLabel = "other",
        iconRes = R.drawable.question
    )
}


val NavCardSelectItemFakeData = listOf(
    NavCardSelectItem(
        id = 1,
        elName = "Βασιλειάδης Χρ. Βασίλειος",
        enName = "",
        elArea = "Νικολάου Παρασκευά 17",
        enArea = "Νικολάου Παρασκευά 17",
        category = NavCardCategory.PHARMACY
    ),
    NavCardSelectItem(
        id = 2,
        elName = "Eurobank",
        enName = "Eurobank",
        elArea = "Ιωάννη Τσιμισκή 27",
        enArea = "Ιωάννη Τσιμισκή 27",
        category = NavCardCategory.BANK
    ),

    NavCardSelectItem(
        id = 96,
        elName = "Εκδοτήριο ΟΑΣΘ",
        enName = "",
        elArea = "Αλεξάνδρου Παπαναστασίου",
        enArea = "Αλεξάνδρου",

        ),
//    NavCardSelectItem(
//        id = 97,
//        elName = "Μασούτης",
//        enName = "Masoutis",
//        elArea = "Αμπελοκήπων 17",
//        enArea = "Ampelokipon 17",
//        x = 22.926429,
//        y = 40.5061769
//    ),

    NavCardSelectItem(
        id = 98,
        elName = "Ωραιοπούλου",
        enName = "Oraiopoulou",
        elArea = "Θεσσαλονίκη",
        enArea = "Thessaloniki"
    ),
//    NavCardSelectItem(
//        id = 99,
//        elName = "Ταβέρνα Άσυλο",
//        enName = "Taverna Asilo",
//        elArea = "Πανεπσιτημίου, Άγιος Παύλος",
//        enArea = "Panepistimiou, Agios Pavlos"
//    ),
)
