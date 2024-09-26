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
        val points: PickTargetPointsType,
        val title: String,
        val subTitle: String,
        @DrawableRes val iconRes: Int,
    ) : DirectionsFeatureItemType

    data object JustMap : DirectionsFeatureItemType
}

sealed interface PickTargetPointsType {
    data class Single(
        val lat: Double,
        val lon: Double
    ) : PickTargetPointsType

    data class Multi(
        val points: List<Pair<Double, Double>>
    ) : PickTargetPointsType
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
        points = PickTargetPointsType.Single(40.640063, 22.943383),
        title = "Βασιλειάδης Χρ. Βασίλειος",
        subTitle = "Νικολάου Παρασκευά 17",
        iconRes = LocationsPoiCategory.PHARMACY.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "2",
        points = PickTargetPointsType.Single(40.640033, 22.943373),
        title = "Δεντρόσπιτο",
        subTitle = "Δεντρόσπιτο",
        iconRes = LocationsPoiCategory.CAFFE.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "3",
        points = PickTargetPointsType.Multi(
            listOf(
                40.62357 to 22.96215,
                40.6236 to 22.96214,
                40.62365 to 22.96211,
                40.62365 to 22.96211,
                40.62371 to 22.96207,
                40.62376 to 22.96204,
                40.62382 to 22.96199,
                40.62476 to 22.96121,
                40.62516 to 22.96088,
                40.62552 to 22.96059,
                40.62602 to 22.9602,
                40.62605 to 22.96017,
                40.62615 to 22.9601,
                40.62622 to 22.96006,
                40.62636 to 22.95992,
                40.62647 to 22.95982,
                40.62652 to 22.95978,
                40.62657 to 22.95973,
                40.62666 to 22.95964,
                40.62679 to 22.9595,
                40.62718 to 22.9591,
                40.62815 to 22.95804,
                40.62852 to 22.95762,
                40.62861 to 22.95752,
                40.62875 to 22.95735,
                40.62898 to 22.95708,
            )
        ),
        title = "Σκλαβενίτης",
        subTitle = "Σκλαβενίτης",
        iconRes = LocationsPoiCategory.SUPER_MARKET.iconRes,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "4",
        points = PickTargetPointsType.Single(40.6305712, 22.9564884),
        title = "Κυλικείο Πρυτανείας",
        subTitle = "Κυλικείο Πρυτανείας",
        iconRes = LocationsPoiCategory.CAFFE.iconRes,
    ),
)

val PickTargetFakeFavorites = listOf(
    DirectionsFeatureItemType.SingleItem(
        id = "1",
        points = PickTargetPointsType.Single(40.64063, 22.94338),
        title = "Σπίτι",
        subTitle = "Καραολή Δημητρίου 17",
        iconRes = AppIcon.house
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "2",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "Δουλειά",
        subTitle = "Βασιλέως Ηρακλείου 2",
        iconRes = R.drawable.bank,
    ),
)

val PickTargetFakeHistory = listOf(
    DirectionsFeatureItemType.SingleItem(
        id = "1",
        points = PickTargetPointsType.Single(40.64063, 22.94338),
        title = "γγγ",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "2",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "αριστοτελους",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "3",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = " ",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "4",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "στάσεις",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "5",
        points = PickTargetPointsType.Single(40.64063, 22.94338),
        title = "γγγ",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "6",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "αριστοτελους",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "7",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = " ",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "8",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "στάσεις",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    DirectionsFeatureItemType.SingleItem(
        id = "9",
        points = PickTargetPointsType.Single(40.64063, 22.94338),
        title = "πανεπιστήμιο",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
)
