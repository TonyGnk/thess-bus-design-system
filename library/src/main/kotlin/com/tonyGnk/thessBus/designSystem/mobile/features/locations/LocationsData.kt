package com.tonyGnk.thessBus.designSystem.mobile.features.locations

import androidx.annotation.DrawableRes
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon


sealed interface DirectionsFeatureItemType {
    data class MultipleItems(
        val items: List<SingleItem>
    ) : DirectionsFeatureItemType

    data class SingleItem(
        val id: String,
        val lat: Double,
        val lon: Double,
        val title: String,
        val subTitle: String,
        @DrawableRes val iconRes: Int,
    ) : DirectionsFeatureItemType

    data object JustMap : DirectionsFeatureItemType
}

enum class LocationsPoiCategory(
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


val PickTargetFakeResults = listOf(
    DirectionsFeatureItemType.SingleItem(
        id = "1",
        lat = 40.640063,
        lon = 22.943383,
        title = "Βασιλειάδης Χρ. Βασίλειος",
        subTitle = "Νικολάου Παρασκευά 17",
        iconRes = LocationsPoiCategory.PHARMACY.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "2",
        lat = 40.640033,
        lon = 22.943373,
        title = "Δεντρόσπιτο",
        subTitle = "Δεντρόσπιτο",
        iconRes = LocationsPoiCategory.CAFFE.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "4",
        lat = 40.640033,
        lon = 22.943373,
        title = "Κυλικείο Πρυτανείας",
        subTitle = "Κυλικείο Πρυτανείας",
        iconRes = LocationsPoiCategory.CAFFE.iconRes,
    ),
)

val PickTargetFakeHistory = listOf(
    DirectionsFeatureItemType.SingleItem(
        id = "1",
        lat = 40.640063,
        lon = 22.943383,
        title = "γγγ",
        subTitle = "",
        iconRes = AppIcon.ClockFive.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "2",
        lat = 40.64003,
        lon = 22.94337,
        title = "αριστοτελους",
        subTitle = "",
        iconRes = AppIcon.ClockFive.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "3",
        lat = 40.64003,
        lon = 22.94337,
        title = " ",
        subTitle = "",
        iconRes = AppIcon.ClockFive.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "4",
        lat = 40.64003,
        lon = 22.94337,
        title = "στάσεις",
        subTitle = "",
        iconRes = AppIcon.ClockFive.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "5",
        lat = 40.640063,
        lon = 22.943383,
        title = "γγγ",
        subTitle = "",
        iconRes = AppIcon.ClockFive.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "6",
        lat = 40.64003,
        lon = 22.94337,
        title = "αριστοτελους",
        subTitle = "",
        iconRes = AppIcon.ClockFive.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "6",
        lat = 40.64003,
        lon = 22.94337,
        title = "αριστοτελους",
        subTitle = "",
        iconRes = AppIcon.ClockFive.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "6",
        lat = 40.64003,
        lon = 22.94337,
        title = "αριστοτελους",
        subTitle = "",
        iconRes = AppIcon.ClockFive.iconRes,
    ),
)
