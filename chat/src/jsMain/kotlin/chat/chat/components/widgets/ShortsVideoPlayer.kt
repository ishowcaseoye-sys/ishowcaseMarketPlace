package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.browser.dom.observers.IntersectionObserver
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.ScrollSnapAlign
import com.varabyte.kobweb.compose.css.ScrollSnapStop
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.dom.registerRefScope
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.scrollSnapAlign
import com.varabyte.kobweb.compose.ui.modifiers.scrollSnapStop
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.overlay.PopupPlacement
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.Shape
import com.varabyte.kobweb.silk.theme.shapes.clip
import chat.chat.components.sections.TopBarDefaults
import chat.chat.data.vehicle.Vehicle
import chat.chat.pages.ShortActionsDefaults
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import chat.chat.utils.noShrink
import chat.chat.utils.rememberBreakpointAsState
import chat.chat.utils.rememberIsShortWindowAsState
import chat.chat.utils.rememberIsSmallBreakpoint
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.plus
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Video
import org.w3c.dom.Element
import org.w3c.dom.HTMLVideoElement

@Composable
private fun ShortVehicleVideoPlayer(
    details: Vehicle,
    rootElement: Element?,
    shape: Shape = Rect(16.px),
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
                contentAlignment = Alignment.BottomStart,
            ) {
                Video(
                    attrs = Modifier.background(Styles.BLACK)
                        .fillMaxSize()
                        .objectFit(ObjectFit.Cover)
                        .onClick { togglePlayPause() }
                        .toAttrs(),
                ) {
                    val videoPlayerRef = ref<HTMLVideoElement> { videoPlayer ->
                        playerRef = videoPlayer
                        with(videoPlayer) {
                            this.controls = false
                            this.loop = true
                            this.src = details.video1
                            this.volume = 1.0
                        }
                    }
                    registerRefScope(videoPlayerRef)
                }

                // Gradient / Scrim
                Box(
                    Modifier
                        .fillMaxSize()
                        .backgroundImage(
                            linearGradient(LinearGradient.Direction.ToTop) {
                                add(Colors.Black.copyf(alpha = 0.41f))
                                add(Colors.Transparent)
                            }
                        )
                )

                Column(
                    modifier = Modifier.padding(topBottom = 24.px, leftRight = 21.px),
                    verticalArrangement = Arrangement.spacedBy(18.px)
                ) {
                    val content = remember {
                        movableContentOf {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(15.px),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(46.px)
                                        .clip(Circle())
                                        .noShrink(),
                                    src = details.img1,
                                )

                                Column(
                                    modifier = Modifier.weight(1),
                                    verticalArrangement = Arrangement.spacedBy(4.px)
                                ) {
                                    TextBox(
                                        maxLines = 1,
                                        size = 18,
                                        text = details.title,
                                        weight = FontWeight.Medium,
                                    )
                                    TextBox(
                                        maxLines = 1,
                                        modifier = Modifier.opacity(0.63f),
                                        size = 14,
                                        text = "${details.id} subscribers",
                                    )
                                }
                            }
                            SubscribeButton(popupPlacement = PopupPlacement.TopLeft)
                        }
                    }

                    if (isSmallBreakpoint) {
                        Column(
                            modifier = Modifier.margin(
                                right = ShortActionsDefaults.WIDTH + 16.px
                            ),
                            verticalArrangement = Arrangement.spacedBy(20.px)
                        ) {
                            content()
                        }
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(20.px),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            content()
                        }
                    }

                    TextBox(text = details.title, maxLines = 2)
                }
            }

            /*ShortVehicleActions(
                modifier = Modifier
                    .noShrink()
                    .margin(
                        right = if (isSmallBreakpoint && !isShortWindowState.value) 24.px else 0.px,
                        bottom = if (isSmallBreakpoint) 24.px else 0.px,
                    ),
                isShortWindowState = isShortWindowState,
            )*/
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

    LaunchedEffect(isActive) { if (isActive) playerRef?.play() else playerRef?.pause() }

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
