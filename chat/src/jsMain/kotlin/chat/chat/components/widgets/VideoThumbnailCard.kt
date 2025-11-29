package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntSize
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.aspectRatio
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.userSelect
import com.varabyte.kobweb.compose.ui.thenIfNotNull
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.Shape
import com.varabyte.kobweb.silk.theme.shapes.clip
import chat.chat.data.BASE_URL
import chat.chat.data.FILES
import chat.chat.data.USER_IMAGES
import chat.chat.utils.Asset
import chat.chat.utils.LocalNavigator
import chat.chat.utils.Route
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import chat.chat.utils.clickable
import chat.chat.utils.noShrink
import chat.chat.data.vehicle.Vehicle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Composable
fun VideoThumbnailCard(
    details: Vehicle,
    modifier: Modifier = Modifier,
    shape: Shape = Styles.Shape.CARD,
    // TODO: Use appropriate value for each screen (refer Figma)
    size:  IntSize? = null,
) {
    val navigator = LocalNavigator.current
    Column(
        modifier = Modifier
            .clickable {
                navigator.pushRoute(Route.Video(id = details.id.toString()))
            }
            .noShrink()
            .thenIfNotNull(size) { safeSize -> Modifier.maxWidth(safeSize.width.px) }
            .userSelect(UserSelect.None)
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(15.px)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .clip(shape)
                    .fillMaxWidth()
                    .aspectRatio(
                        size?.width ?: VideoThumbnailCardDefaults.SIZE.width,
                        size?.height ?: VideoThumbnailCardDefaults.SIZE.height,
                    )
                    .objectFit(ObjectFit.Cover),
                src = BASE_URL + FILES + details.img1,
                width = size?.width,
                height = size?.height,
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(Styles.VIDEO_CARD_DURATION_CONTAINER)
                    .margin(10.px)
                    .padding(leftRight = 6.px, topBottom = 3.px)
                    .clip(Rect(6.px))
                    .fontWeight(FontWeight.Medium)
            ) { Text(   details.priceMain.toString()  ) }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.px),
        ) {
            BASE_URL + USER_IMAGES + details.userId.toString()+
                    "/userimage"+details.userId.toString()+".jpg".toString().let { asset ->
                Image(src = asset, modifier = Modifier.clip(Circle()).size(48.px))
            }
            Column(
                modifier = Modifier.weight(1).color(Styles.VIDEO_CARD_SECONDARY_TEXT),
                verticalArrangement = Arrangement.spacedBy(8.px),
            ) {
                TextBox(
                    color = Styles.VIDEO_CARD_PRIMARY_TEXT,
                    lineHeight = 25,
                    maxLines = 2,
                    size = 18,
                    text = details.title,
                    weight = FontWeight.Medium,
                )
              /*  details.channelName?.let { safeChannelName ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.px),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(safeChannelName)
                        if (details.isVerified) Image(Asset.Icon.VERIFIED_BADGE)
                    }
                }
                Row { Text("${details.views} views • ${details.daysSinceUploaded} ago") }*/
            }
            AssetImageButton(Asset.Icon.MORE) {}
        }
    }
}

object VideoThumbnailCardDefaults {
    val SIZE = IntSize(width = 354, height = 198)
}
