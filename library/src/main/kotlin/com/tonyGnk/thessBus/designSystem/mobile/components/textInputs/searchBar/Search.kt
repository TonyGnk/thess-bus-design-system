package com.tonyGnk.thessBus.designSystem.mobile.components.textInputs.searchBar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.R
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.iconButtons.IconButton
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.mySharedElement

sealed interface SearchBarType {
    data class TextField(
        val textFieldState: TextFieldState,
        val focusRequester: FocusRequester
    ) : SearchBarType

    data class Static(
        val text: String,
        val alternativeText: String,
        val onTextClick: () -> Unit
    ) : SearchBarType
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    onBackClick: () -> Unit,
    type: SearchBarType,
    sharedElementPlaceHolderTag: String,
    sharedElementIconTag: String,
    sharedElementBox: String,
    sharedElementTextTag: String,

    ) {
    val searchLabel = LocationsProperties.SEARCH_LABEL
    val searchStyle = LocationsProperties.searchTextStyle

    val sizeInScreen = searchLabel.findScreenSize(searchStyle).height - 1.dp

    SearchBarContainer(
        modifier = modifier, sharedElementCard = sharedElementBox
    ) {
        val modifierOfTheCenteredItem = Modifier
            .padding(vertical = LocationsProperties.IN_PADDING.dp)
            .fillMaxWidth()
            .weight(1f)
            .mySharedElement(sharedElementPlaceHolderTag)

        IconButton(
            iconRes = AppIcon.back,
            color = AppColor.transparent,
            onClick = onBackClick,
            modifier = Modifier.size(sizeInScreen)
        )
        when (type) {
            is SearchBarType.TextField -> SearchField(
                searchStyle = searchStyle,
                modifier = modifierOfTheCenteredItem,
                searchLabel = searchLabel,
                onSearchClick = onSearchClick,
                textState = type.textFieldState,
                sharedElementTextTag = sharedElementTextTag,
                focusRequester = type.focusRequester
            )

            is SearchBarType.Static -> Text(
                text = type.text.ifBlank { type.alternativeText },
                style = searchStyle,
                modifier = modifierOfTheCenteredItem.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    type.onTextClick()
                }
            )
        }

        IconButton(
            iconRes = AppIcon.search,
            color = AppColor.transparent,
            onClick = onSearchClick,
            modifier = Modifier
                .size(sizeInScreen)
                .mySharedElement(sharedElementIconTag)
        )
    }
}


@Composable
fun SearchBarContainer(
    modifier: Modifier = Modifier,
    sharedElementCard: String,
    content: @Composable RowScope.() -> Unit
) {
    SurfaceWithShadows(
        shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
        color = AppColor.surfaceLowest,
        modifier = modifier
            .mySharedElement(sharedElementCard)
            .clip(RoundedCornerShape(LocationsProperties.IN_CORNERS.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp),
            modifier = Modifier.padding(
                horizontal = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp,
            )
        ) {
            content(this)
        }
    }
}


@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    searchStyle: TextStyle,
    searchLabel: String,
    textState: TextFieldState,
    sharedElementTextTag: String,
    focusRequester: FocusRequester
) {
    val isTypingModeMy = textState.text.isNotEmpty()

    BasicTextField(
        state = textState,
        modifier = modifier.focusRequester(focusRequester),
        enabled = true,
        readOnly = false,
        textStyle = searchStyle,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        onKeyboardAction = {
            onSearchClick()
        },
        onTextLayout = {},
        cursorBrush = SolidColor(searchStyle.color),
        decorator = {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
            ) {
                it()

                if (!isTypingModeMy) Text(
                    text = searchLabel,
                    style = searchStyle,
                    modifier = Modifier.mySharedElement(
                        "NavCardStartSelectText",
                        skipLookaheadSize = true
                    )

                )
            }
        }
    )

}


@Composable
@AppPreview.Brightness
private fun Preview() = ThessBusTheme {
    SearchBar(
        onBackClick = { },
        onSearchClick = { },
        type = SearchBarType.Static(
            text = "Search term", alternativeText = "Search here",
        ) {},
        sharedElementPlaceHolderTag = "",
        sharedElementIconTag = "",
        sharedElementBox = "",
        sharedElementTextTag = ""
    )
}

class MyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val titleTextView: EditText

    var text: String = ""
        set(value) {
            field = value
            updateContent()
        }
    var fontSize: Float = 0f
        set(value) {
            field = value
            titleTextView.textSize = value
        }
    var textColor: Int = 0
        set(value) {
            field = value
            titleTextView.setTextColor(value)
        }

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.my_view_layout, this, true)

        titleTextView = findViewById(R.id.editText)

        updateContent()

        titleTextView.requestFocus()

        // Show the keyboard
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(titleTextView, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun updateContent() {
        titleTextView.setText(text)
        titleTextView.setSelection(text.length)
    }
}
