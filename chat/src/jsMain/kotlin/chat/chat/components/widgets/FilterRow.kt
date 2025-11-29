package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import chat.chat.pages.AllPropertiesPage
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import chat.chat.utils.AnimatedVisibility
import chat.chat.utils.Asset
import chat.chat.utils.HorizontalScrollState
import chat.chat.utils.LocalNavigator
import chat.chat.utils.Route
import chat.chat.utils.Route.AllProperties
import chat.chat.utils.Styles
import chat.chat.utils.bindScrollState
import chat.chat.utils.hideScrollBar
import chat.chat.utils.noShrink
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.w3c.dom.Element

@Composable
fun FilterRow(showPersonalisedFeedDialogState: MutableState<Boolean>) {
    var rowRef by remember { mutableStateOf<Element?>(null) }
    val horizontalScrollState = remember { mutableStateOf(HorizontalScrollState.ReachedStart) }
    var selectedFilterIndex by remember { mutableIntStateOf(0) }
    val context:PageContext
    val navigator = LocalNavigator.current
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        Row(
            ref = ref { element -> rowRef = element },
            modifier = Modifier
                .overflow(Overflow.Scroll)
                .hideScrollBar()
                .scrollBehavior(ScrollBehavior.Smooth)
                .bindScrollState(horizontalScrollState),
            horizontalArrangement = Arrangement.spacedBy(12.px),
        ) {
            // Personalised Feed Button
            AssetSvgButton(
                id = "filter_preferences_button",
                type = AssetSvgButtonType.SelectableChip,
                onClick = { showPersonalisedFeedDialogState.value = true },
            ) {
                Image(src = Asset.Icon.MAGIC_FEED)
            }

            // Filter Chips
            filters.forEachIndexed { index, filter ->
                val isSelected = index == selectedFilterIndex
                AssetSvgButton(
                    id = filter.name,
                    type = AssetSvgButtonType.SelectableChip,
                    text = filter.name,
                    startIconPath = filter.iconPath,
                    containerColor = if (!isSelected) filter.backgroundColor else Styles.WHITE,
                    iconPrimaryColor = if (!isSelected) filter.iconPrimaryColor else null,
                    iconSecondaryColor = if (!isSelected) filter.iconSecondaryColor else null,
                    isSelected = index == selectedFilterIndex,
                    onClick = {

                        selectedFilterIndex = index
                        console.info("Clicking on index no = " + index)
                        console.info("Clicking on filter.name = " + filter.name)
                        if (index == 1) {
                            // Properties
                            console.info("Going to AllProperties")
                            navigator.pushRoute(Route.AllProperties("8"))
                            //AllPropertiesPage()
                        } else if (index == 2) {
                            // Vehicles
                            console.info("Going to AllVehicles")
                            navigator.pushRoute(Route.AllVehicles("8"))
                        } else if (index == 3) {
                            // Furniture
                            console.info("Going to AllFurniture")
                            navigator.pushRoute(Route.AllFurniture("8"))
                        } else if (index == 4) {
                            // Fashion
                            console.info("Going to AllFashion")
                            navigator.pushRoute(Route.AllFashion("8"))
                        } else if (index == 5) {
                            // Electronic
                            console.info("Going to AllElectronic")
                            navigator.pushRoute(Route.AllElectronic("8"))
                        } else if (index == 6) {
                            // Jobseekers
                            console.info("Going to Jobseekers")
                            navigator.pushRoute(Route.AllJobSeeker("8"))
                        } else if (index == 7) {
                            // Lands
                            navigator.pushRoute(Route.AllProperties("Jobs"))
                        } else if (index == 8) {
                            // Jobseekers
                            // navigator.pushRoute(Route.AllProperties("8"))
                        }
                    },
                )
            }

            Box(modifier = Modifier.width(12.px).noShrink())
        }

        repeat(2) { index ->
            val startItem = index == 0
            AnimatedVisibility(
                isVisible = if (startItem) horizontalScrollState.value != HorizontalScrollState.ReachedStart
                else horizontalScrollState.value != HorizontalScrollState.ReachedEnd,
                modifier = Modifier
                    .align(if (startItem) Alignment.CenterStart else Alignment.CenterEnd)
                    .zIndex(1)
            ) {
                Box(
                    modifier = Modifier
                        .backgroundImage(
                            linearGradient(
                                if (startItem) LinearGradient.Direction.ToRight
                                else LinearGradient.Direction.ToLeft
                            ) {
                                add(Styles.SURFACE)
                                setMidpoint(75.percent)
                                add(Colors.Transparent)
                            }
                        )
                        .padding(right = if (startItem) 0.px else 8.px)
                        .width(61.px),
                    contentAlignment = if (startItem) Alignment.CenterStart
                    else Alignment.CenterEnd,
                ) {
                    AssetImageButton(
                        asset = if (startItem) Asset.Icon.ARROW_LEFT
                        else Asset.Icon.ARROW_RIGHT,
                        onClick = {
                            rowRef?.scrollBy(x = if (startItem) -200.0 else 200.0, y = 0.0)
                        },
                    )
                }
            }
        }
    }
}

private class ButtonFilterInfo(
    val name: String,
    val iconPath: String? = null,
    val backgroundColor: Color = Styles.WHITE.copyf(alpha = Styles.Opacity.ASSET_SVG_BUTTON),
    val iconPrimaryColor: Color? = null,
    val iconSecondaryColor: Color? = null,
    val onClick: (() -> Unit)? = null
)

private class FilterInfo(
    val name: String,
    val iconPath: String? = null,
    val backgroundColor: Color = Styles.WHITE.copyf(alpha = Styles.Opacity.ASSET_SVG_BUTTON),
    val iconPrimaryColor: Color? = null,
    val iconSecondaryColor: Color? = null,
)

private const val SPECIAL_FILTER_NAME = "Suggested New Categories"

private val filters = listOf(
    FilterInfo(name = "Home"),
    FilterInfo(name = "Properties"),
    FilterInfo(name = "Vehicles"),
    FilterInfo(name = "Furniture"),
    FilterInfo(name = "Fashion"),
    FilterInfo(name = "Electronics"),
    FilterInfo(name = "JobSeekers"),
    FilterInfo(name = "Lands"),
/*  FilterInfo(name = "Handyman"),
    FilterInfo(name = "Jobs"),
    FilterInfo(name = "Appliances"),
    FilterInfo(name = "Computers"),
    FilterInfo(name = "Phones"),*/

    FilterInfo(
        name = SPECIAL_FILTER_NAME,
        iconPath = Asset.Path.DYNAMIC,
        backgroundColor = Styles.BACKGROUND_SELECTED,
        iconPrimaryColor = Styles.RED,
        iconSecondaryColor = Styles.PINK,
    ),
)
