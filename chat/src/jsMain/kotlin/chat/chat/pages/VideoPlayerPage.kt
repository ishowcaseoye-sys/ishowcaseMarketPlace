package chat.chat.pages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.PointerEvents
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.pointerEvents
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.thenIf
import chat.chat.components.widgets.player.PlayerAndCommentsProperty
import chat.chat.data.BASE_URL
import chat.chat.data.FILES
import chat.chat.data.property.Property
import chat.chat.data.propertyJsonString
import chat.chat.utils.AnimatedVisibility
import chat.chat.utils.Styles
import chat.chat.utils.clickable
import chat.chat.utils.convertPropertyJsonStringToList
import chat.chat.utils.noShrink
import chat.chat.utils.rememberIsLargeBreakpoint
import chat.chat.utils.rememberWindowWidthAsState
import chat.chat.components.widgets.player.SegmentedContent
import chat.chat.utils.LocalNavigator
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.times
import org.jetbrains.compose.web.css.vh

@Composable
fun VideoPlayerPage(
    id: String ,
    modifier: Modifier = Modifier,
) {
    val navigator = LocalNavigator.current
    val properties = convertPropertyJsonStringToList ( propertyJsonString )
    console.info("AXA - entered vehicle id = $id")
    /*for (property in properties) {
        console.info("AXA - vehicle = "+property.id)
    }*/
    val propertyList = properties.filter (
            {
                it.id == id.toInt()
            }
        )
    var property : Property = propertyList.get(0)
    //console.info("AXA - entered vehicle id = $property")
    val videoId = BASE_URL + FILES + property.video1
    console.info("AXA - videoId = $videoId")
    //console.info("info VideoPlayerPage 1-  vehicles = ${property.size}")
    //val vehicle = property.filter { it.id == id.toInt() }
    //val videoId =  BASE_URL + FILES + vehicle[0].video1
    //console.info("info VideoPlayerPage 1-  vehicle = ${vehicle.size}")

    val isLargeBreakpoint by rememberIsLargeBreakpoint()
    val isTheaterModeOn = remember { mutableStateOf(false) }

    // Segment States
    val selectedSegment = remember { mutableStateOf(SegmentedContentType.Suggestions) }
    val isSegmentedContentVisible = remember { mutableStateOf(false) }
    val windowWidth by rememberWindowWidthAsState()
    val animatedFixedSegmentSizeFactor by animateFloatAsState(
        when {
            !isLargeBreakpoint -> 0f
            isLargeBreakpoint && isTheaterModeOn.value -> 0f
            else -> 1f
        }
    )
    val shouldDisplayFixedSegmentContent by remember {
        derivedStateOf { animatedFixedSegmentSizeFactor != 0f }
    }
    val animatedFloatingSegmentWidth by animateFloatAsState(
        if (isSegmentedContentVisible.value) 473f.coerceAtMost(windowWidth * 0.8f)
        else 0f,
    )

    val segmentContent = remember {
        movableContentOf { modifier: Modifier ->
            SegmentedContent(category = "Properties", state = selectedSegment, modifier = modifier)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
        Row(modifier = Modifier.fillMaxSize().then(modifier)) {
            PlayerAndCommentsProperty(

                modifier = Modifier.weight(1),
                property = property,
                isTheaterModeOn = isTheaterModeOn,
                isSegmentedContentVisible = isSegmentedContentVisible,
                selectedSegment = selectedSegment,
                videoId = videoId,
                segmentedContent = if (!shouldDisplayFixedSegmentContent && isLargeBreakpoint) {
                    {
                        segmentContent(Modifier.width(SUGGESTIONS_SECTION_MAX_WIDTH))
                    }
                } else null,
                onBackPressed = navigator::pop

            )
            Box(modifier = Modifier.width(34.px * animatedFixedSegmentSizeFactor))
            if (shouldDisplayFixedSegmentContent) {
                segmentContent(
                    Modifier
                        .noShrink()
                        .opacity(animatedFixedSegmentSizeFactor)
                        .width(SUGGESTIONS_SECTION_MAX_WIDTH * animatedFixedSegmentSizeFactor)
                )
            }
        }
        if (!isLargeBreakpoint) {
            AnimatedVisibility(
                isVisible = isSegmentedContentVisible.value,
                modifier = Modifier
                    .fillMaxSize()
                    .thenIf(!isSegmentedContentVisible.value) { Modifier.pointerEvents(PointerEvents.None) }
                    .zIndex(1),
                skipDelay = true,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Styles.SURFACE.copyf(alpha = 0.5f))
                        .clickable(noPointer = true) { isSegmentedContentVisible.value = false }
                )
            }
            Row(
                modifier = Modifier.background(Styles.SURFACE_ELEVATED)
                    .borderRadius(12.px)
                    .height(75.vh)
                    .margin(left = 12.px)
                    .overflow { y(Overflow.Scroll) }
                    .width(animatedFloatingSegmentWidth.px)
                    .zIndex(1),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(left = 16.px, top = 16.px, right = 16.px),
                    content = { segmentContent(Modifier.fillMaxSize()) },
                )
            }
        }
    }
}

enum class SegmentedContentType(val label: String) {
    Suggestions("Suggestions"),
    Transcripts("Transcripts"),
    LiveChat("Live Chat")
}

private val SUGGESTIONS_SECTION_MAX_WIDTH = 411.px
