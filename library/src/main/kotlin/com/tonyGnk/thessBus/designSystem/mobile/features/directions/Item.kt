package com.tonyGnk.thessBus.designSystem.mobile.features.directions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon

@Stable
data class PickTargetItem(
    val id: String,
    val points: PickTargetPointsType,
    val title: String,
    val subTitle: String,
    @DrawableRes val iconRes: Int,
)

sealed interface PickTargetPointsType {
    data class Single(
        val lat: Double,
        val lon: Double
    ) : PickTargetPointsType

    data class Multi(
        val points: List<Pair<Double, Double>>
    ) : PickTargetPointsType
}

enum class DirectionsPoiCategory(
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
    PickTargetItem(
        id = "1",
        points = PickTargetPointsType.Single(40.640063, 22.943383),
        title = "Βασιλειάδης Χρ. Βασίλειος",
        subTitle = "Νικολάου Παρασκευά 17",
        iconRes = DirectionsPoiCategory.PHARMACY.iconRes,
    ),
    PickTargetItem(
        id = "2",
        points = PickTargetPointsType.Single(40.640033, 22.943373),
        title = "Δεντρόσπιτο",
        subTitle = "Δεντρόσπιτο",
        iconRes = DirectionsPoiCategory.CAFFE.iconRes,
    ),
    PickTargetItem(
        id = "3",
        points = PickTargetPointsType.Multi(
            listOf(
                40.64003 to 22.94337,
                40.64002 to 22.94337,
                40.64002 to 22.94336,
                40.64003 to 22.94336,
            )
        ),
        title = "Σκλαβενίτης",
        subTitle = "Σκλαβενίτης",
        iconRes = DirectionsPoiCategory.SUPER_MARKET.iconRes,
    ),
)

val PickTargetFakeFavorites = listOf(
    PickTargetItem(
        id = "1",
        points = PickTargetPointsType.Single(40.64063, 22.94338),
        title = "Σπίτι",
        subTitle = "Καραολή Δημητρίου 17",
        iconRes = AppIcon.house
    ),
    PickTargetItem(
        id = "2",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "Δουλειά",
        subTitle = "Βασιλέως Ηρακλείου 2",
        iconRes = R.drawable.bank,
    ),
)

val PickTargetFakeHistory = listOf(
    PickTargetItem(
        id = "1",
        points = PickTargetPointsType.Single(40.64063, 22.94338),
        title = "γγγ",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "2",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "αριστοτελους",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "3",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = " ",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "4",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "στάσεις",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "5",
        points = PickTargetPointsType.Single(40.64063, 22.94338),
        title = "γγγ",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "6",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "αριστοτελους",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "7",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = " ",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "8",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "στάσεις",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "9",
        points = PickTargetPointsType.Single(40.64063, 22.94338),
        title = "πανεπιστήμιο",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "10",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "καφετέρια",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "11",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "στάσεις",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "12",
        points = PickTargetPointsType.Single(40.64063, 22.94338),
        title = "γγγ",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
    PickTargetItem(
        id = "13",
        points = PickTargetPointsType.Single(40.64003, 22.94337),
        title = "αριστοτελους",
        subTitle = "",
        iconRes = AppIcon.clockFive,
    ),
)
