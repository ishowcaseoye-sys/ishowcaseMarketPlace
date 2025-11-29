package chat.chat.components.widgets.player

import androidx.compose.runtime.Composable
import chat.chat.models.SuggestionSectionData
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.clip
import chat.chat.utils.Asset
import chat.chat.utils.LocalNavigator
import chat.chat.utils.Route
import chat.chat.utils.SpacedColumn
import chat.chat.utils.SpacedRow
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import chat.chat.utils.clickable
import chat.chat.utils.noShrink
import org.jetbrains.compose.web.css.px

@Composable
fun SuggestionSection(data: SuggestionSectionData) {
    val navigator = LocalNavigator.current

    SpacedColumn(spacePx = 14, modifier = Modifier.fillMaxWidth()) {
        TextBox(
            modifier = Modifier.margin(bottom = 2.px),
            text = data.title,
            size = 14,
        )
        data.videos.forEach { video ->
            SpacedRow(
                spacePx = 8,
                modifier = Modifier
                    .borderRadius(8.px)
                    .fillMaxWidth()
                    .clickable { navigator.pushRoute(Route.Video(id = video.id)) },
                centerContentVertically = false,
            ) {
                Box(
                    modifier = Modifier
                        .clip(Rect(8.px))
                        .noShrink()
                        .size(width = 168.px, height = 94.px),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize().objectFit(ObjectFit.Cover),
                        src = video.thumbnailAsset
                    )
                    TextBox(
                        modifier = Modifier
                            .background(Styles.BLACK.copyf(alpha = 0.6f))
                            .clip(Rect(4.px))
                            .margin(4.px)
                            .padding(leftRight = 4.px, topBottom = 2.px),
                        text = video.duration,
                        size = 12,
                    )
                }
                SpacedColumn(4) {
                    TextBox(
                        color = Styles.VIDEO_CARD_PRIMARY_TEXT,
                        lineHeight = 20,
                        maxLines = 2,
                        size = 14,
                        text = video.title,
                        weight = FontWeight.Medium,
                    )
                    Column {
                        video.channelName?.let { channelName ->
                            SpacedRow(4) {
                                TextBox(
                                    color = Styles.VIDEO_CARD_SECONDARY_TEXT,
                                    lineHeight = 18,
                                    size = 12,
                                    text = channelName,
                                )
                                if (video.isVerified) {
                                    Image(
                                        modifier = Modifier.size(12.px),
                                        src = Asset.Icon.VERIFIED_BADGE,
                                    )
                                }
                            }
                        }
                        TextBox(
                            color = Styles.VIDEO_CARD_SECONDARY_TEXT,
                            lineHeight = 18,
                            size = 12,
                            text = "${video.views} views • ${video.daysSinceUploaded} ago",
                        )
                    }
                }
            }
        }
    }
}
