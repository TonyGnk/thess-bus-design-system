package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R

enum class LandingDestination(
    @StringRes val labelRes: Int, @DrawableRes val iconRes: Int
) {
    Features(R.string.landing_destinations_features, R.drawable.objects_column),
    Components(R.string.landing_destinations_components, R.drawable.add_circle),
    Icons(R.string.landing_destinations_icons, R.drawable.circle_nodes),
    Colors(R.string.landing_destinations_colors, R.drawable.palette),
}

enum class LayoutDestination(
    @StringRes val labelRes: Int
) {
    NavCard(R.string.Layouts_directions),
}

//(actions, communication, containment, navigation, selection, textInputs)
enum class ComponentsType(@StringRes val labelRes: Int) {
    Actions(R.string.component_types_actions),
    Communication(R.string.component_types_communication),
    Containment(R.string.component_types_containment),
    Navigation(R.string.component_types_navigation),
    Selection(R.string.component_types_selection),
    TextInputs(R.string.component_types_text_inputs),
}

enum class Components(
    @StringRes val labelRes: Int,
    val componentsType: ComponentsType
) {
    Buttons(R.string.components_actions_buttons, ComponentsType.Actions),
    FloatingActionButton(
        R.string.components_actions_floating_action_button,
        ComponentsType.Actions
    ),

    BasicTopBar(R.string.components_navigation_basic_top_bar, ComponentsType.Navigation),
    NavigationBar(R.string.components_navigation_navigation_bar, ComponentsType.Navigation),
}
