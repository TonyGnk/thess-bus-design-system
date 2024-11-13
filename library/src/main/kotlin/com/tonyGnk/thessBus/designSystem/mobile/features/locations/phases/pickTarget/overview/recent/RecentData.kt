package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview.recent

data class RecentItem(
    val id: Int,
    val label: String,
    val lat: Double,
    val lon: Double,
)


val FakeRecentItems = listOf(
    RecentItem(id = 1, label = "γγγ", lat = 40.640063, lon = 22.943383),
    RecentItem(id = 2, label = "αριστοτελους", lat = 40.64003, lon = 22.94337),
    RecentItem(id = 3, label = " ", lat = 40.64003, lon = 22.94337),
    RecentItem(id = 4, label = "στάσεις", lat = 40.64003, lon = 22.94337),
    RecentItem(id = 5, label = "γγγ", lat = 40.640063, lon = 22.943383),
    RecentItem(id = 6, label = "αριστοτελους", lat = 40.64003, lon = 22.94337),
    RecentItem(id = 7, label = "αριστοτελους", lat = 40.64003, lon = 22.94337),
    RecentItem(id = 8, label = "αριστοτελους", lat = 40.64003, lon = 22.94337)
)
