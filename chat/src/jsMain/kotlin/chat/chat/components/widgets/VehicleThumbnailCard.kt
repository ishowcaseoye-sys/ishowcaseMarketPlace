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
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.thenIfNotNull
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.Shape
import com.varabyte.kobweb.silk.theme.shapes.clip
import chat.chat.data.BASE_URL
import chat.chat.data.FILES
import chat.chat.data.USER_IMAGES
import chat.chat.data.vehicle.Vehicle
import chat.chat.utils.Asset
import chat.chat.utils.LocalNavigator
import chat.chat.utils.Route
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import chat.chat.utils.clickable
import chat.chat.utils.noShrink
import chat.chat.utils.numberCalculation
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Composable
fun VehicleThumbnailCard(
    vehicle: Vehicle,
    segment: Boolean,
    modifier: Modifier = Modifier,
    shape: Shape = Styles.Shape.CARD,
    size: IntSize? = null,
) {
    val navigator = LocalNavigator.current
    val logoIcon = BASE_URL + FILES + vehicle.vehicleLogo
    Column(
        modifier = Modifier
            .clickable {
                navigator.pushRoute(Route.VideoVehicle(id = vehicle.id.toString()))
            }
            .noShrink()
            .thenIfNotNull(size) { safeSize -> Modifier.maxWidth(safeSize.width.px) }
            .userSelect(UserSelect.None)
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(10.px)
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
                src = BASE_URL + FILES + vehicle.img1,
                width = size?.width,
                height = size?.height,
            )
            Box(
                modifier = Modifier.align(Alignment.BottomStart)
                    .size(40.px).zIndex(10)
            ) {

                Image( logoIcon ,modifier = Modifier.size(60.px).padding(bottom=20.px) )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .margin(6.px)
                    .padding( 1.px)
                    .fontWeight(FontWeight.Light)
            ) {
               // Icon
                Image(Asset.Icon.LIKED, modifier = Modifier
                    .clip(Circle()).size(32.px)
                    .padding ( 8.px)
                    .background(Styles.VIDEO_CARD_DURATION_CONTAINER))
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(Styles.VIDEO_CARD_DURATION_CONTAINER)
                    .margin(10.px)
                    .padding(leftRight = 6.px, topBottom = 3.px)
                    .clip(Rect(6.px))
                    .fontWeight(FontWeight.Light)
            ) {
                Text( vehicle.priceCurrency.substring(0,1) +" "
                    + numberCalculation( vehicle.priceMain )  )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.px),
        ) {

            val userImageUrl = BASE_URL + USER_IMAGES + vehicle.marketedByImage.toString()
            userImageUrl.let { asset ->
                Image(src = asset, modifier = Modifier.clip(Circle()).size(56.px))
            }
            Column(
                modifier = Modifier.
                    weight(1).color(Styles.VIDEO_CARD_SECONDARY_TEXT)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(1.px),
            ) {
                TextBox(
                    color = Styles.VIDEO_CARD_PRIMARY_TEXT,
                    lineHeight = 16,
                    maxLines = 2,
                    size = 15,
                    text = vehicle.title,
                    weight = FontWeight.Medium,
                )
                Row () {

                    TextBox(
                       vehicle.makeModelYearStr, color = Styles.RED,
                        maxLines = 2,
                    )
                }
            }
        }

        if (!segment) {
            vehicle.description.let { safeChannelName ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.px),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    TextBox(
                        color = Styles.VIDEO_CARD_PRIMARY_TEXT,
                        lineHeight = 15,
                        maxLines = 2,
                        size = 13,
                        text = safeChannelName,
                        weight = FontWeight.Light,
                    )
                }
            }
        }
    }
}
