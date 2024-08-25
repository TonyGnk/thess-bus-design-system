package com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.selectDestination

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.surface.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.selectDestination.data.NavCardSelectItem
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navigationCard.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import java.util.Locale

@Composable
fun NavCardSelectSearch(
    results: List<NavCardSelectItem>
) {
    val isGreek = isLanguageGreek(LocalContext.current)
    val selectedX: MutableState<Double?> = remember { mutableStateOf(null) }
    val selectedY: MutableState<Double?> = remember { mutableStateOf(null) }

    SurfaceWithShadows(
        color = AppColor.surfaceContainerLowest,
        shape = RoundedCornerShape(NavCardProperties.SMALL_CORNERS.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(NavCardProperties.SEARCH_PADDING.dp)
                    .clip(RoundedCornerShape(NavCardProperties.SMALL_CORNERS.dp)),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(
                    items = results, key = { it.id }
                ) { item ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                        modifier = Modifier.selectable(
                            selected = true,
                            onClick = {
                                selectedX.value = item.x
                                selectedY.value = item.y
                            }
                        )
                    ) {
                        Text(
                            size = 17.sp,
                            //                        text = if (isGreek) item.elName else item.enName
                            text = item.elName + " - " + item.enName,
                        )
                        Text(
                            size = 11.sp,
                            //                        text = if (isGreek) item.elArea else item.enArea + item.category,
                            text = item.elArea + " - " + item.enArea + " - " + item.category,

                            color = AppColor.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            size = 9.sp,
                            text = item.x.toString() + "  " + item.y.toString(),
                            color = AppColor.onSurface.copy(alpha = 0.4f)
                        )
                    }
                }
            }
            MyMapArea(
                properties = MapProperties(),
                selectedX = selectedX.value,
                selectedY = selectedY.value
            )
        }
    }
}

@Composable
@AppPreview.Brightness
private fun Preview() = ClpTheme {
    val results = listOf(
        NavCardSelectItem(
            id = 1,
            elName = "Ξηροκρήνη",
            enName = "Xirokrini",
            elArea = "Θεσσαλονίκη",
            enArea = "Thessaloniki"
        ),

        NavCardSelectItem(
            id = 2,
            elName = "Ωραιοπούλου",
            enName = "Oraiopoulou",
            elArea = "Θεσσαλονίκη",
            enArea = "Thessaloniki"
        ),
        NavCardSelectItem(
            id = 3,
            elName = "Ταβέρνα Άσυλο",
            enName = "Taverna Asilo",
            elArea = "Πανεπσιτημίου, Άγιος Παύλος",
            enArea = "Panepistimiou, Agios Pavlos"
        ),
    )

    NavCardSelectSearch(results = results)
}

@SuppressLint("ObsoleteSdkInt")
fun isLanguageGreek(context: Context): Boolean {
    val locale: Locale = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        context.resources.configuration.locales[0]
    } else {
        @Suppress("DEPRECATION")
        context.resources.configuration.locale
    }
    return locale.language == "el"
}

@Composable
fun MyMapArea(properties: MapProperties, selectedX: Double?, selectedY: Double?) {
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    val cameraPositionState = rememberCameraPositionState()
    val markerState = rememberMarkerState()

    LaunchedEffect(selectedX, selectedY) {
        if (selectedX != null && selectedY != null) {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(
                    LatLng(selectedY, selectedX),
                    15f // Adjust zoom level as needed
                ),
            )
            markerState.position = LatLng(selectedY, selectedX)
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(NavCardProperties.SMALL_CORNERS.dp)),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState,
    ) {
        if (selectedX != null && selectedY != null) Marker(
            state = markerState,
            title = "Selected place",
            icon = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_ORANGE
            )
        )
    }
}
