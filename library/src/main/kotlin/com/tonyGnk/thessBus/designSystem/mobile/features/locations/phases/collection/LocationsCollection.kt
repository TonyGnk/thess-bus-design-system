package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.collection


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.TextButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.CenteredTopBar
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.extendedWindowInsets


@Composable
fun OverviewCollection(

) {
    val horizontalPadding = PaddingValues(
        horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
    )
    val topBarTextStyle = AppTypo.bodyLarge

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = extendedWindowInsets,
    ) {
        item {
            CenteredTopBar(
                leftContent = {
                    TextButton(text = "Ακύρωση", textStyle = topBarTextStyle)
                },
                centerContent = {
                    Text("Επεξεργασία", style = topBarTextStyle)
                },
                rightContent = {
                    TextButton(text = "Αποθήκευση", textStyle = topBarTextStyle)
                }
            )
        }
        item {
            Text(
                modifier = Modifier.padding(horizontalPadding),
                text = "Ετικέτα",
                style = AppTypo.titleMedium
            )
        }
        item {
            Text(
                modifier = Modifier.padding(horizontalPadding),
                text = "Σημείο",
                style = AppTypo.titleMedium
            )
        }
        item {
            SurfaceWithShadows(
                modifier = Modifier.padding(horizontalPadding),
            ) { }
        }
        item {
            Text(
                modifier = Modifier.padding(horizontalPadding),
                text = "Εικονίδιο",
                style = AppTypo.titleMedium
            )
        }
        item {
            SurfaceWithShadows(
                color = AppColor.surfaceLowest,
                modifier = Modifier
                    .padding(horizontalPadding)
                    .fillMaxWidth()
                    .height(1500.dp),
            ) { }
        }
        item {
            Text(
                modifier = Modifier.padding(horizontalPadding),
                text = "Χρώμα",
                style = AppTypo.titleMedium
            )
        }
        item {
            SurfaceWithShadows(
                modifier = Modifier
                    .padding(horizontalPadding)
                    .height(1500.dp),
            ) { }
        }


    }


}


@Composable
@AppPreview.Light
private fun Preview() = ClpTheme {
    OverviewCollection()
}
