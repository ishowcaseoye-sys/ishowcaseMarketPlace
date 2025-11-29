package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.browser.dom.observers.IntersectionObserver
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.ScrollSnapAlign
import com.varabyte.kobweb.compose.css.ScrollSnapStop
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.dom.registerRefScope
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.scrollSnapAlign
import com.varabyte.kobweb.compose.ui.modifiers.scrollSnapStop
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.Shape
import com.varabyte.kobweb.silk.theme.shapes.clip
import chat.chat.components.sections.TopBarDefaults
import chat.chat.utils.Styles
import chat.chat.utils.rememberBreakpointAsState
import chat.chat.utils.rememberIsShortWindowAsState
import chat.chat.utils.rememberIsSmallBreakpoint
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Video
import org.w3c.dom.Element
import org.w3c.dom.HTMLVideoElement

@Composable
internal fun ShortVehicleVideoPlayer(
    stringUrl: String,
    rootElement: Element?,
    shape: Shape = Rect(20.px),
) {
    val breakpoint by rememberBreakpointAsState()
    val isShortWindowState = rememberIsShortWindowAsState()
    val isSmallBreakpoint by rememberIsSmallBreakpoint()

    // Player States
    var playerRef by remember { mutableStateOf<HTMLVideoElement?>(null) }
    val togglePlayPause: () -> Unit = remember(playerRef) {
        {
            playerRef?.let { player -> with(player) { if (paused) play() else pause() } }
        }
    }
    var isActive by remember { mutableStateOf(false) }

    val content = remember(isSmallBreakpoint) {
        movableContentOf { modifier: Modifier ->
            Box(
                ref = ref { e ->
                    val observer = IntersectionObserver(
                        IntersectionObserver.Options(root = rootElement, thresholds = listOf(0.5))
                    ) { entries ->
                        entries.firstOrNull()?.let { entry -> isActive = entry.isIntersecting }
                    }
                    observer.observe(e)
                },
                modifier = Modifier.clip(shape).fillMaxHeight().then(modifier),
                contentAlignment = Alignment.Center,
            ) {
                Video(
                    attrs = Modifier.background(Styles.BLACK)
                        //.fillMaxWidth()
                       /// .(1200.px)
                        .height(580.px)
                        .width( 254.px)
                        .margin (bottom = 470.px)
                        .padding(1.px)
                        .objectFit(ObjectFit.Cover)
                        .onClick { togglePlayPause() }
                        .toAttrs(),
                ) {
                    val videoPlayerRef = ref<HTMLVideoElement> { videoPlayer ->
                        playerRef = videoPlayer
                        with(videoPlayer) {
                            this.controls = false
                            this.loop = true
                            this.src = stringUrl
                            this.volume = 1.0
                        }
                    }
                    registerRefScope(videoPlayerRef)
                }
            }
        }
    }

    val modifier = remember(breakpoint) {
        Modifier
            .padding(topBottom = 12.px)
            .scrollSnapAlign(ScrollSnapAlign.Start)
            .scrollSnapStop(ScrollSnapStop.Always)
            .size(
                width = when (breakpoint) {
                    Breakpoint.ZERO -> 95.percent
                    Breakpoint.SM -> 450.px
                    Breakpoint.MD -> 500.px
                    Breakpoint.LG -> 560.px
                    Breakpoint.XL -> 600.px
                    Breakpoint.XXL -> 700.px
                },
                height = if (isShortWindowState.value) 80.vh
                else 100.vh - TopBarDefaults.HEIGHT - 40.px,
            )
    }

    // LaunchedEffect(isActive) { if (isActive) playerRef?.play() else playerRef?.pause() }

    if (isSmallBreakpoint) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.BottomEnd,
        ) {
            content(Modifier)
        }
    } else {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(25.px),
            verticalAlignment = Alignment.Bottom,
        ) {
            content(Modifier.weight(1))
        }
    }
}
