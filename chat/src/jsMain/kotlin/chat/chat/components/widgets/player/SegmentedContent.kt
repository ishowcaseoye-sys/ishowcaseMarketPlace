package chat.chat.components.widgets.player

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import chat.chat.components.widgets.ElectronicThumbnailCard
import chat.chat.components.widgets.FashionThumbnailCard
import chat.chat.components.widgets.FurnitureThumbnailCard
import chat.chat.components.widgets.JobSeekerThumbnailCard
import chat.chat.components.widgets.PropertyThumbnailCard
import chat.chat.components.widgets.SegmentedButton
import chat.chat.components.widgets.VehicleThumbnailCard
import chat.chat.pages.SegmentedContentType
import chat.chat.utils.Crossfade
import chat.chat.utils.SpacedColumn
import chat.chat.utils.TextBox
import chat.chat.utils.rememberIsLargeBreakpoint
import ishowcase.data.FeedProvider
import org.jetbrains.compose.web.css.px

@Composable
fun SegmentedContent(
    category: String,
    state: MutableState<SegmentedContentType>,
    modifier: Modifier = Modifier,
) {

    val isLargeBreakpoint by rememberIsLargeBreakpoint()
    val feedProvider = remember { FeedProvider() }
    val vehicles = remember(feedProvider) { feedProvider.getPremiumVehicleFeed() }
    val properties = remember(feedProvider) { feedProvider.getAllPropertiesFeed() }
    val electronics = remember(feedProvider) { feedProvider.getAllElectronicsFeed() }
    val fashions = remember(feedProvider) { feedProvider.getAllFashionFeed() }
    val furniture = remember(feedProvider) { feedProvider.getAllFurnitureFeed() }
    val jobseekers =  remember(feedProvider) { feedProvider.getAllJobSeekersFeed() }

    Column(modifier = Modifier.fillMaxSize().then(modifier)) {
        SegmentedButton(
            segments = SegmentedContentType.entries.map { e -> e.label },
            selectedIndex = state.value.ordinal,
            onSegmentClick = { index ->
                //state.value = SegmentedContentType.entries.elementAt(index)
            },
        )
        Crossfade(
            targetState = state.value,
            modifier = Modifier.fillMaxWidth().weight(1),
        ) { animatedIndex ->
            when (animatedIndex) {
                SegmentedContentType.Suggestions -> SpacedColumn(
                    spacePx = 24,
                    modifier = Modifier.padding(topBottom = 24.px)
                ) {
                    if (category=="Vehicles") {
                        vehicles.forEach { sectionData -> VehicleThumbnailCard(sectionData, true) }
                    } else if (category=="Properties") {
                        properties.forEach { sectionData -> PropertyThumbnailCard(sectionData, true) }
                    } else if (category=="Fashions") {
                        fashions.forEach { sectionData -> FashionThumbnailCard(sectionData, true) }
                    } else if (category=="Furniture") {
                        furniture.forEach { sectionData -> FurnitureThumbnailCard(sectionData, true) }
                    } else if (category=="Electronics") {
                        electronics.forEach { sectionData -> ElectronicThumbnailCard(sectionData, true) }
                    } else if (category=="JobSeekers") {
                        jobseekers.forEach { sectionData -> JobSeekerThumbnailCard(sectionData, true) }
                    }
                }

                SegmentedContentType.Transcripts -> Box(
                    modifier = Modifier.fillMaxWidth().margin(top = 24.px),
                    contentAlignment = Alignment.Center,
                ) {
                    TextBox(text = "Your transcripts will show here")
                }

                SegmentedContentType.LiveChat -> { }
                   /* LiveChatSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isLargeBreakpoint) 84.vh else 65.vh)
                        .margin(top = 8.px, bottom = 22.px)
                )  */
            }
        }
    }

}
