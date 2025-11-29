package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntSize
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.userSelect
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.Shape
import com.varabyte.kobweb.silk.theme.shapes.clip
import chat.chat.data.BASE_URL
import chat.chat.data.FILES
import chat.chat.data.USER_IMAGES
import chat.chat.data.furniture.Furniture
import chat.chat.utils.LocalNavigator
import chat.chat.utils.Route
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import chat.chat.utils.limitTextWithEllipsis
import chat.chat.utils.noShrink
import chat.chat.utils.numberCalculation
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Composable
fun ShortFurnitureThumbnailCard(
    furniture: Furniture,
    shape: Shape = Styles.Shape.CARD,
    modifier: Modifier = Modifier,
    size: IntSize = ShortVehicleThumbnailCardDefaults.SIZE,
) {
    val navigator = LocalNavigator.current

    //console.info("ShortFurnitureThumbnailCard URL = "+BASE_URL + FILES + furniture.video1)

    Column(
        modifier = Modifier
            .cursor(Cursor.Pointer)
            .maxWidth(size.width.px)
            .noShrink()
            .onClick {
                navigator.pushRoute(Route.VideoFurniture(id = furniture.id.toString()))
                //ShortsGrid(true)
            }
            .userSelect(UserSelect.None)
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(15.px)
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                modifier = Modifier
                    .clip(shape)
                    .objectFit(ObjectFit.Cover)
                    .size(width = size.width.px, height = size.height.px),
                src =  BASE_URL + FILES + furniture.img1,
                //src = BASE_URL + FILES + details.img1
            )
            val userImageUrl = BASE_URL + USER_IMAGES + furniture.marketedByImage.toString()
            userImageUrl.let { asset ->
                Image(src = asset, modifier = Modifier.clip(Circle()).size(60.px)
                    .margin (4.px))
            }
            //AssetImageButton(modifier = Modifier.margin(4.px), asset = Asset.Icon.MORE) {}
        }


        Column(modifier = Modifier.color(Styles.VIDEO_CARD_SECONDARY_TEXT)) {
            Box(
                modifier = Modifier.color(Styles.VIDEO_CARD_PRIMARY_TEXT)
                    .fontSize(18.px)
                    .fontWeight(FontWeight.Medium)
                    .limitTextWithEllipsis()
            ) { Text(furniture.title) }
            val amount = furniture.priceCurrency.substring(0,1) +" "+
            numberCalculation( furniture.priceMain ).toString()
            Box(Modifier.margin(topBottom = 1.px).limitTextWithEllipsis()) {
                TextBox( amount.toString() , size=20,   color = Styles.RED  )
            }

            Box(Modifier.limitTextWithEllipsis()) {
                TextBox( furniture.description ,  size=14, maxLines=2 )
            }
        }
    }
}

/*object ShortElectronicThumbnailCardDefaults {
    val SIZE = IntSize(width = 231, height = 409)
}*/


