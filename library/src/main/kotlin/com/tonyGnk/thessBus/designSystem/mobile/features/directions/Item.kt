package com.tonyGnk.thessBus.designSystem.mobile.features.directions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.tonyGnk.thessBus.designSystem.mobile.R

@Stable
data class SelectTargetItem(
    val id: Int,
    val isSinglePoint: Boolean,
    val title: String,
    val subTitle: String,
    val category: DirectionsCategory = DirectionsCategory.OTHER,
)

enum class DirectionsCategory(
    val osmLabel: String,
    @DrawableRes val iconRes: Int
) {
    PHARMACY(osmLabel = "pharmacy", iconRes = R.drawable.pharmacy),
    CAFFE(osmLabel = "cafe", iconRes = R.drawable.mug_hot_alt),
    SUPER_MARKET(osmLabel = "supermarket", iconRes = R.drawable.shopping_cart),
    TICKET(osmLabel = "ticket", iconRes = R.drawable.ticket_alt),
    UNIVERSITY(osmLabel = "university", iconRes = R.drawable.graduation_cap),
    SCHOOL(osmLabel = "school", iconRes = R.drawable.school),
    COLLEGE(osmLabel = "college", iconRes = R.drawable.graduation_cap),
    HOTEL(osmLabel = "hotel", iconRes = R.drawable.bed),
    DOCTOR(osmLabel = "doctor", iconRes = R.drawable.stethoscope),
    STUDIO(osmLabel = "studio", iconRes = R.drawable.radio_tower),
    BICYCLE_PARKING(osmLabel = "bicycle_parking", iconRes = R.drawable.biking),
    FAST_FOOD(osmLabel = "fast_food", iconRes = R.drawable.burger_fries),
    CHURCH(osmLabel = "church", iconRes = R.drawable.church),
    ATM(osmLabel = "atm", iconRes = R.drawable.insert_credit_card),
    HEAR_DRESSER(osmLabel = "hairdresser", iconRes = R.drawable.shopping_cart),
    BIKE_RENTAL(osmLabel = "bicycle_rental", iconRes = R.drawable.biking),
    FUEL(osmLabel = "fuel", iconRes = R.drawable.gas_pump_alt),
    BUS_STOP(osmLabel = "bus_stop", iconRes = R.drawable.bus_alt),
    CLOTHES(osmLabel = "clothes", iconRes = R.drawable.shirt_long_sleeve),

    //
    THEATER(osmLabel = "theater", iconRes = R.drawable.theater_masks),
    BANK(osmLabel = "bank", iconRes = R.drawable.bank),
    OTHER(osmLabel = "other", iconRes = R.drawable.question);
}


val SelectTargetItemFakeData = listOf(
    SelectTargetItem(
        id = 1,
        title = "Βασιλειάδης Χρ. Βασίλειος",
        subTitle = "Νικολάου Παρασκευά 17",
        category = DirectionsCategory.PHARMACY,
        isSinglePoint = true
    ),
    SelectTargetItem(
        id = 2,
        title = "Δεντρόσπιτο",
        subTitle = "Δεντρόσπιτο",
        category = DirectionsCategory.CAFFE,
        isSinglePoint = true
    ),
)
