package chat.chat.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.CSSLengthOrPercentageNumericValue
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import chat.chat.components.widgets.BannerContainer
import chat.chat.components.widgets.ColoredBorderContainer
import chat.chat.components.widgets.FilterRow
import chat.chat.components.widgets.PropertyThumbnailCard
import chat.chat.components.widgets.ShortElectronicThumbnailCard
import chat.chat.components.widgets.ShortFashionThumbnailCard
import chat.chat.components.widgets.ShortFurnitureThumbnailCard
import chat.chat.components.widgets.ShortJobSeekersThumbnailCard
import chat.chat.components.widgets.ShortPropertyThumbnailCardDefaults
import chat.chat.components.widgets.VehicleThumbnailCard
import ishowcase.data.FeedProvider
import chat.chat.data.electronic.Electronic
import chat.chat.data.fashion.Fashion
import chat.chat.data.furniture.Furniture
import chat.chat.data.jobseeker.JobSeeker
import chat.chat.data.property.Property
import chat.chat.utils.AnimatedVisibility
import chat.chat.utils.Asset
import chat.chat.utils.BasicGrid
import chat.chat.utils.Constants
import chat.chat.utils.GridGap
import chat.chat.utils.Styles
import chat.chat.utils.isGreaterThan
import chat.chat.utils.rememberBreakpointAsState
import chat.chat.data.vehicle.Vehicle
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.px

object HomePageDefaults {
    val SPACE_BETWEEN_CONTENT = 30.px
}

@Composable
fun HomeScreenPage(showPersonalisedFeedDialogState: MutableState<Boolean>  ) {
    var isLoading by remember { mutableStateOf(true) }
    console.info( "AXA - HomeScreenPage 1"  )
    val scope = rememberCoroutineScope()
    val ctx = rememberPageContext()
    val feedProvider = remember { FeedProvider() }
    var message by remember { mutableStateOf("Loading...") }

    /* LaunchedEffect(Unit) {
        scope.launch {
            try {
                // Call the API endpoint "hello"
                val response = window.api.tryGet("hello")
                console.info("This is response => "+response )
                response?.let {
                    console.info("This is response => "+response )
                    //message = String(it) // Assuming the API returns a String
                } ?: run {
                    message = "Error: No response from API"
                    console.info("This is response ERROR => "+response )
                }
            } catch (e: Exception) {
                message = "Error: ${e.message}"
            }
        }
    }*/

    Column(modifier = Modifier.fillMaxWidth()) {

        FilterRow(showPersonalisedFeedDialogState = showPersonalisedFeedDialogState)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 27.px, bottom = 27.px),
        ) {

            BannerContainer (
                modifier = Modifier.margin(bottom = HomePageDefaults.SPACE_BETWEEN_CONTENT),
                videos =  listOf(),
            )

            MainPropertiesGrid(
              properties = remember(feedProvider) { feedProvider.getAllPropertiesFeed() }
            )

            MainVehiclesGrid(
              vehicles = remember(feedProvider) { feedProvider.getPremiumVehicleFeed() },
            )

            Box(modifier = Modifier.height(20.px))
            ShortSuggestionsFashions(
                shorts = remember(feedProvider) {
                    feedProvider.getAllFashionFeed()
                }
            )

            Box(modifier = Modifier.height(20.px))
            ShortSuggestionsElectronics(
                shorts = remember(feedProvider) {
                    feedProvider.getAllElectronicsFeed()
                }
            )

            Box(modifier = Modifier.height(20.px))
            ShortSuggestionsFurniture(
                shorts = remember(feedProvider) {
                    feedProvider.getAllFurnitureFeed()
                }
            )
            Box(modifier = Modifier.height(20.px))
            ShortSuggestionsJobSeeker(
                shorts = remember(feedProvider) {
                    feedProvider.getAllJobSeekersFeed()
                }
            )

            Box(modifier = Modifier.height(20.px))
        }
    }
}

@Composable
private fun ShortSuggestionsJobSeeker(modifier: Modifier = Modifier, shorts: List<JobSeeker>) {
    var display by remember { mutableStateOf(true) }
    AnimatedVisibility(modifier = Modifier.fillMaxWidth(), isVisible = display) {
        ColoredBorderContainer(
            modifier = modifier,
            title = "JobSeekers",
            onNegativeCTA = { display = false },
            onPositiveCTA = {},
            color = Styles.BLUE_BORDER,
            asset = Asset.Icon.SHORTS_SELECTED,
            scrollPixels = ShortPropertyThumbnailCardDefaults.SIZE.width.toDouble(),
        ) {
            shorts.forEach { short ->
                ShortJobSeekersThumbnailCard(short)
            }
        }
    }
}

@Composable
private fun ShortSuggestionsFurniture(modifier: Modifier = Modifier, shorts: List<Furniture>) {
    var display by remember { mutableStateOf(true) }
    AnimatedVisibility(modifier = Modifier.fillMaxWidth(), isVisible = display) {
        ColoredBorderContainer(
            modifier = modifier,
            title = "Furniture",
            onNegativeCTA = { display = false },
            onPositiveCTA = {},
            color = Styles.BLUE_BORDER,
            asset = Asset.Icon.SHORTS_SELECTED,
            scrollPixels = ShortPropertyThumbnailCardDefaults.SIZE.width.toDouble(),
        ) {
            shorts.forEach { short ->
                ShortFurnitureThumbnailCard(short)
            }
        }
    }
}

@Composable
private fun ShortSuggestionsElectronics(modifier: Modifier = Modifier, shorts: List<Electronic>) {
    var display by remember { mutableStateOf(true) }
    AnimatedVisibility(modifier = Modifier.fillMaxWidth(), isVisible = display) {
        ColoredBorderContainer(
            modifier = modifier,
            title = "Electronics",
            onNegativeCTA = { display = false },
            onPositiveCTA = {},
            color = Styles.BLUE_BORDER,
            asset = Asset.Icon.SHORTS_SELECTED,
            scrollPixels = ShortPropertyThumbnailCardDefaults.SIZE.width.toDouble(),
        ) {
            shorts.forEach { short ->
                ShortElectronicThumbnailCard(short)
            }
        }
    }
}

@Composable
fun MainPropertiesGrid(
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
fun MainVehiclesGrid(
    vehicles: List<Vehicle>,
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
        vehicles.forEach { vehicle ->
            VehicleThumbnailCard(
                vehicle = vehicle,
                segment = false,
                // TODO: Remove this bottom margin & update all its usages
                modifier = Modifier.margin(bottom = 36.px),
            )
        }
    }
}

@Composable
private fun ShortSuggestionsFashions(modifier: Modifier = Modifier, shorts: List<Fashion>) {
    var display by remember { mutableStateOf(true) }
    AnimatedVisibility(modifier = Modifier.fillMaxWidth(), isVisible = display) {
        ColoredBorderContainer(
            modifier = modifier,
            title = "Fashion",
            onNegativeCTA = { display = false },
            onPositiveCTA = {},
            color = Styles.BLUE_BORDER,
            asset = Asset.Icon.SHORTS_SELECTED,
            scrollPixels = ShortPropertyThumbnailCardDefaults.SIZE.width.toDouble(),
        ) {
            shorts.forEach { short ->
                ShortFashionThumbnailCard(short)
            }
        }
    }
}
