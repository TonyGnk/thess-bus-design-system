package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.collection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
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
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme


@Composable
fun OverviewCollectionItem(
    horizontalPadding: PaddingValues = PaddingValues(
        horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
    )
) {
    val topBarTextStyle = AppTypo.bodyLarge

    LazyColumn(
        // contentPadding = extendedWindowInsets,
        // modifier = Modifier.height(900.dp)
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
            labelField(
                modifier = Modifier.padding(horizontalPadding),
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
            Spacer(modifier = Modifier.height(1500.dp))
        }
        item {
            Text(
                modifier = Modifier.padding(horizontalPadding),
                text = "Χρώμα",
                style = AppTypo.titleMedium
            )
        }
        item {
            Spacer(modifier = Modifier.height(1500.dp))
        }


    }


}

@Composable
private fun labelField(
    modifier: Modifier = Modifier,
    basicTextFieldStyle: TextFieldState = rememberTextFieldState()
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                AppColor.surfaceLowest, shape = RoundedCornerShape(
                    LocationsProperties.IN_CORNERS
                )
            )
            .padding(LocationsProperties.IN_PADDING.dp)
    ) {
        BasicTextField(
            modifier = modifier.fillMaxWidth(),
            state = basicTextFieldStyle
        )
    }
}

private fun localTextField() {

}

@Composable
@AppPreview.Light
private fun Preview() = ClpTheme {
    OverviewCollectionItem()
}
