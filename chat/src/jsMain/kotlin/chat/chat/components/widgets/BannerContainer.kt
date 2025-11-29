package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import chat.core.model.VideoThumbnailDetails
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.clip
import chat.chat.AnnouncementTextStyle
import chat.chat.PreHeadlineTextStyle
import chat.chat.SubheadlineTextStyle
import chat.chat.utils.AnimatedVisibility
import chat.chat.utils.HorizontalScrollState
import chat.chat.utils.Styles
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.Element

@Composable
fun BannerContainer(modifier: Modifier = Modifier, videos: List<VideoThumbnailDetails>) {
    var rowRef by remember { mutableStateOf<Element?>(null) }
    val containerPadding = remember { 6.px }
    var showContainer by remember { mutableStateOf(true) }
    val horizontalScrollState = remember { mutableStateOf(HorizontalScrollState.ReachedStart) }

    AnimatedVisibility(
        isVisible = showContainer,
        modifier = Modifier.fillMaxWidth(),
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Styles.MISSED_VIDEOS_CONTAINER)
                .clip(Rect(24.px))
                .height(600.px)
                .padding(topBottom = containerPadding),
            verticalArrangement = Arrangement.spacedBy(containerPadding)
        ) {
            
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 2.px, bottom = 10.px)
                    .margin(bottom = 20.px),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                var containerRef by remember { mutableStateOf<Element?>(null) }
                ShortVehicleVideoPlayer (
                     "http://37.148.204.80:8080/files/appvideos/appdemo.mp4 ",
                    containerRef
                )

                Box(modifier = Modifier.margin(left=4.px))
                Box(
                    modifier = Modifier
                        //.width(400.px)
                        .fillMaxWidth()
                        .fontSize(218.px)
                        .fontWeight(FontWeight.Medium)
                        .margin (top=30.px)
                ) {

                    Column(
                        Modifier
                            .padding(1.px)
                            .fontSize(120.px)
                            .gap(1.5.cssRem)
                    ) {
                        Div( AnnouncementTextStyle.toAttrs()) {
                            SpanText(
                                "Introducing iShowcase", Modifier.color(
                                    when (ColorMode.current) {
                                        ColorMode.LIGHT -> Colors.Black
                                        ColorMode.DARK -> Colors.White
                                    }
                                )
                            )
                        }

                        Div(PreHeadlineTextStyle.toAttrs()) {
                            SpanText("A listing management platform")
                        }

                        Div(SubheadlineTextStyle.toAttrs()) {
                            SpanText("Features rich-multimedia and promotes products globally. Please check our ",
                                )
                            Link("/about", "About")
                            SpanText(" page for more information.")
                        }

                        Div( AnnouncementTextStyle.toAttrs()) {
                            SpanText(
                                text = "Coming Soon!")
                        }

                        Div(SubheadlineTextStyle.toAttrs()) {
                            SpanText(
                                "Powered by iSoft Fusion",
                            )
                        }

                        Box(modifier = Modifier.height(500.px))
                    }
                }
                //AssetImageButton(Asset.Icon.CLOSE) { showContainer = false }
            }
        }
    }
}

private const val VIDEO_THUMBNAIL_CARDS_GAP: Double = 20.0

