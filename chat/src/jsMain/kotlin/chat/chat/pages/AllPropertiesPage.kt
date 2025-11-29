package chat.chat.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.CSSLengthOrPercentageNumericValue
import chat.chat.components.widgets.AssetImageButton
import chat.chat.components.widgets.AssetSvgButton
import chat.chat.components.widgets.AssetSvgButtonType
import chat.chat.data.SearchDataProvider
import chat.chat.utils.Asset
import chat.chat.utils.Constants
import chat.chat.utils.LocalNavigator
import chat.chat.utils.SpacedRow
import chat.chat.utils.Styles
import chat.chat.utils.Wrap
import chat.chat.utils.rememberIsLargeBreakpoint
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexWrap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.rowGap
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import chat.chat.components.widgets.ColoredBorderContainer
import chat.chat.components.widgets.PropertyThumbnailCard
import chat.chat.components.widgets.ShortPropertyThumbnailCard
import chat.chat.components.widgets.ShortPropertyThumbnailCardDefaults
import ishowcase.data.FeedProvider
import chat.chat.data.property.Property
import chat.chat.utils.AnimatedVisibility
import chat.chat.utils.BasicGrid
import chat.chat.utils.GridGap
import chat.chat.utils.isGreaterThan
import chat.chat.utils.rememberBreakpointAsState
import com.varabyte.kobweb.core.Page
import kotlinx.browser.window
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.px


@Composable
fun AllPropertiesPage(query: String) {
    val searchDataProvider = remember { SearchDataProvider() }
    val feedProvider = remember { FeedProvider() }
    val navigator = LocalNavigator.current
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4")
    var selectedOption by remember { mutableStateOf(options.first()) }

    Row(modifier = Modifier.padding(top=2.px) ) {
        AssetImageButton(
            asset = Asset.Icon.ARROW_LEFT,
            modifier = Modifier.background(Styles.ARROW_BUTTON_CONTAINER)
                .margin(right=8.px),
            onClick = {
                //onBackPressed
                //navigator.pop()
                // navigator::pop
                if (window.history.length > 1) { // Check if there's history to go back to
                    window.history.back()
                }
            }
        )
        Filters(modifier = Modifier.margin(bottom = 2.px))
    }

    Column(modifier = Modifier.padding(top=90.px)) {

        ShortSuggestionsProperty(
            shorts = remember(searchDataProvider) {
                searchDataProvider.getSearchedVideosForQuery("").filter { it.typeId==2 }
            }
        )

        Box(modifier = Modifier.height(30.px))

        MainSearchPropertiesGrid(
            properties = remember(searchDataProvider) {
                searchDataProvider.getSearchedVideosForQuery("").filter { it.typeId==1 }
            }
        )
    }
}

@Composable
fun MainSearchPropertiesGrid(
    properties: List<Property>,
    modifier: Modifier = Modifier,
    gridGap: GridGap = GridGap(20.px),
    minWidth: CSSLengthOrPercentageNumericValue = 250.px,
) {
    val breakpoint by rememberBreakpointAsState()
    BasicGrid(
        modifier = Modifier.fillMaxWidth().display(DisplayStyle.Grid).then(modifier),
        columnBuilder = {
            minmax(
                min = if (breakpoint.isGreaterThan(Breakpoint.MD)) minWidth
                else Constants.MOBILE_MAX_AVAILABLE_WIDTH.px,
                max = 1.fr
            )
        },
        gridGap = gridGap,
    ) {
        properties.forEach { property ->
            //console.info("AXA - " + details.title)
            PropertyThumbnailCard(
                property = property,
                segment = false,
                // TODO: Remove this bottom margin & update all its usages
                modifier = Modifier.margin(bottom = 36.px),
            )
        }
    }
}

@Composable
private fun ShortSuggestionsProperty(modifier: Modifier = Modifier, shorts: List<Property>) {
    var display by remember { mutableStateOf(true) }
    console.info("Number of JobSeeker Shorts = "+ shorts.size)
    AnimatedVisibility(modifier = Modifier.fillMaxWidth(), isVisible = display) {
        ColoredBorderContainer(
            modifier = modifier,
            title = "Premium Properties",
            onNegativeCTA = { display = false },
            onPositiveCTA = {},
            color = Styles.BLUE_BORDER,
            asset = Asset.Icon.SHORTS_SELECTED,
            scrollPixels = ShortPropertyThumbnailCardDefaults.SIZE.width.toDouble(),
        ) {
            shorts.forEach { short ->
                ShortPropertyThumbnailCard(short)
            }
        }
    }
}

@Composable
private fun Filters(modifier: Modifier = Modifier ) {
    val isLargeBreakpoint by rememberIsLargeBreakpoint()
    var isLayoutTypeGrid by remember { mutableStateOf(false) }
    var isWatchTypeWatched by remember { mutableStateOf<Boolean?>(null) }

    Wrap(
        horizontalGapPx = 8,
        modifier = Modifier.fillMaxWidth().then(modifier),
    ) {

        AssetSvgButton(
            endIconPath = Asset.Path.ARROW_DOWN,
            id = "type_button",
            isDense = true,
            onClick = {},
            secondaryText = "Type:",
            text = "All",
            type = AssetSvgButtonType.SelectableChip,
        )
        AssetSvgButton(
            endIconPath = Asset.Path.ARROW_DOWN,
            id = "upload_date_button",
            isDense = true,
            onClick = {},
            secondaryText = "Upload Date:",
            text = "Any Time",
            type = AssetSvgButtonType.SelectableChip,
        )
        AssetSvgButton(
            endIconPath = Asset.Path.ARROW_DOWN,
            id = "duration_button",
            isDense = true,
            onClick = {},
            secondaryText = "Duration:",
            text = "Any",
            type = AssetSvgButtonType.SelectableChip,
        )
        AssetSvgButton(
            endIconPath = Asset.Path.ARROW_DOWN,
            id = "features_button",
            isDense = true,
            onClick = {},
            secondaryText = "Features:",
            text = "Any",
            type = AssetSvgButtonType.SelectableChip,
        )
        AssetSvgButton(
            endIconPath = Asset.Path.ARROW_DOWN,
            id = "sort_by_button",
            isDense = true,
            onClick = {},
            secondaryText = "Sort by:",
            text = "Relevance",
            type = AssetSvgButtonType.SelectableChip,
        )
        if (isLargeBreakpoint) Spacer()
        SpacedRow(spacePx = 8, modifier = Modifier.flexWrap(FlexWrap.Wrap).rowGap(8.px)) {
            AssetSvgButton(
                isDense = true,
                id = "watch_status_all_button",
                isSelected = isWatchTypeWatched == null,
                onClick = { isWatchTypeWatched = null },
                text = "All",
                type = AssetSvgButtonType.SelectableChip,
            )
            AssetSvgButton(
                isDense = true,
                id = "watch_status_unwatched_button",
                isSelected = isWatchTypeWatched == false,
                onClick = { isWatchTypeWatched = false },
                text = "Unwatched",
                type = AssetSvgButtonType.SelectableChip,
            )
            AssetSvgButton(
                isDense = true,
                id = "watch_status_watched_button",
                isSelected = isWatchTypeWatched == true,
                onClick = { isWatchTypeWatched = true },
                text = "Watched",
                type = AssetSvgButtonType.SelectableChip,
            )
            // Divider
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .background(Styles.DIVIDER_LIGHTER)
                    .height(Constants.VERTICAL_DIVIDER_SIZE.height.px)
                    .margin(leftRight = 8.px)
                    .width(Constants.VERTICAL_DIVIDER_SIZE.width.px)
            )
            AssetSvgButton(
                isDense = true,
                id = "layout_type_grid_button",
                isSelected = isLayoutTypeGrid,
                onClick = { isLayoutTypeGrid = true },
                startIconPath = Asset.Path.GRID,
                type = AssetSvgButtonType.SelectableChip,
            )
            AssetSvgButton(
                isDense = true,
                id = "layout_type_list_button",
                isSelected = !isLayoutTypeGrid,
                onClick = { isLayoutTypeGrid = false },
                startIconPath = Asset.Path.LIST,
                type = AssetSvgButtonType.SelectableChip,
            )
        }
    }
}
