package chat.chat.components.widgets.player

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import chat.chat.components.widgets.AssetImageButton
import chat.chat.components.widgets.IconLabel
import chat.chat.components.widgets.SegmentedButtonPair
import chat.chat.components.widgets.UnderlinedToggleText
import chat.chat.components.widgets.comments.CommentsSection
import chat.chat.pages.SegmentedContentType
import chat.chat.utils.Asset
import chat.chat.utils.LocalNavigator
import chat.chat.utils.MouseEventState
import chat.chat.utils.SpacedColumn
import chat.chat.utils.SpacedRow
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import chat.chat.utils.rememberIsLargeBreakpoint
import chat.chat.utils.rememberMouseEventAsState
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.columnGap
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexWrap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.rowGap
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.clip
import chat.chat.components.widgets.propertyAttr
import chat.chat.components.widgets.propertyTypes
import chat.chat.data.BASE_URL
import chat.chat.data.FILES
import chat.chat.data.USER_IMAGES
import chat.chat.data.property.Property
import chat.chat.utils.dateFormat
import chat.chat.utils.numberCalculation
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.w3c.dom.Element
import org.jetbrains.compose.web.dom.Img

@Composable
fun PlayerAndCommentsProperty(
    modifier: Modifier = Modifier,
    property: Property,
    videoId: String,
    selectedSegment: MutableState<SegmentedContentType>,
    isTheaterModeOn: MutableState<Boolean>,
    isSegmentedContentVisible: MutableState<Boolean>,
    onBackPressed: () -> Unit,
    segmentedContent: (@Composable () -> Unit)?,
) {

    val navigator = LocalNavigator.current
    val isLargeBreakpoint by rememberIsLargeBreakpoint()

    var isDescriptionBoxExpanded by remember { mutableStateOf(false) }
    var descriptionToggleRef by remember { mutableStateOf<Element?>(null) }
    val descriptionToggleMouseEventState by rememberMouseEventAsState(descriptionToggleRef)

    Row() {

        Column(modifier = modifier.margin (top=1.px).fillMaxWidth()  ) {

            Box(modifier = modifier.minWidth(540.px)) {
                VideoPlayerProperty(
                    videoId = BASE_URL + FILES + property.video1,
                    isTheaterModeOn = isTheaterModeOn,
                    selectedSegment = selectedSegment,
                )

                AssetImageButton(
                    asset = Asset.Icon.ARROW_LEFT,
                    modifier = Modifier.background(Styles.ARROW_BUTTON_CONTAINER)
                        .margin(12.px),
                    onClick = { onBackPressed() }
                )
            }

            Box(modifier = Modifier.height(20.px))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .display(DisplayStyle.Flex)
                    .flexWrap(FlexWrap.Wrap)
                    .columnGap(20.px)
                    .rowGap(20.px),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SpacedRow(20) {
                    var channelRowRef by remember { mutableStateOf<Element?>(null) }
                    val isChannelRowHovered =
                        rememberMouseEventAsState(channelRowRef).value == MouseEventState.Hovered

                }
                SpacedRow(24) {
                    IconLabel(iconAsset = Asset.Icon.EYE, label = property.views.toString())
                    IconLabel(iconAsset = Asset.Icon.DATE, label = dateFormat(property.enteredDate))
                }
                SpacedRow(
                    spacePx = 10,
                    modifier = Modifier
                        .display(DisplayStyle.Flex)
                        .flexWrap(FlexWrap.Wrap)
                        .rowGap(16.px)
                ) {
                    SegmentedButtonPair(
                        assetPathLeft = Asset.Path.LIKED,
                        assetPathRight = Asset.Path.DISLIKE,
                        containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
                        isLeftLabelBold = true,
                        labelLeft = property.likes.toString(),   //videoDetails.likeCount,
                        labelRight = "0",  //videoDetails.dislikeCount,
                        onClickLeft = {},
                        onClickRight = {},
                    )
                    AssetImageButton(
                        asset = Asset.Icon.SHARE,
                        containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
                    ) {}
                    AssetImageButton(
                        asset = Asset.Icon.ADD_TO_PLAYLIST,
                        containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
                    ) {}
                    AssetImageButton(
                        asset = Asset.Icon.WATCH_LATER,
                        containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
                    ) {}
                    AssetImageButton(
                        asset = Asset.Icon.MORE_HORIZONTAL,
                        containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
                    ) {}
                    if (!isLargeBreakpoint) {
                        AssetImageButton(
                            asset = Asset.Icon.ARROW_LEFT,
                            containerColor = Styles.ELEVATED_BUTTON_CONTAINER
                        ) { isSegmentedContentVisible.value = !isSegmentedContentVisible.value }
                    }
                }
            }

            Box(modifier = Modifier.height(20.5.px))

            Box(
                modifier = Modifier
                    .border(1.px, LineStyle.Solid, Styles.WHITE.copyf(alpha = 0.1f))
                    .borderRadius(15.43.px)
                    .fillMaxWidth()
                    .padding(leftRight = 24.px, topBottom = 20.px)
                    .whiteSpace(WhiteSpace.PreLine)
            ) {
                SpacedColumn(16) {
                    Column() {
                        val userImageUrl1 =
                            BASE_URL + FILES + property.buildingPlan1.toString()
                        userImageUrl1.let { asset ->
                            Image(src = asset, modifier = Modifier.fillMaxWidth())
                        }
                        val userImageUrl2 =
                            BASE_URL + FILES + property.buildingPlan2.toString()

                        userImageUrl2.let { asset ->
                            Image(src = asset, modifier = Modifier.fillMaxWidth())
                        }
                    }

                    UnderlinedToggleText(
                        isSelected = isDescriptionBoxExpanded,
                        mouseEventState = descriptionToggleMouseEventState,
                        onClick = { isDescriptionBoxExpanded = !isDescriptionBoxExpanded },
                        ref = ref { e -> descriptionToggleRef = e },
                    )
                }
            }

            Box(modifier = Modifier.height(31.px))

            // Comments

        }

        Box(modifier = Modifier.width(20.5.px))

        Column(modifier = modifier.margin (top=1.px).padding(left=16.px, right=16.px)
            ) {
            /**************
            // Right side
            **************/
            Column(modifier = Modifier.flexWrap(FlexWrap.Wrap)) {
                TextBox(
                    color = Colors.RoyalBlue,
                    lineHeight = 52,
                    maxLines = 1,
                    size = 62,
                    text = "Properties",
                    weight = FontWeight.Normal,
                )
                Box(modifier = Modifier.height(10.5.px))
                TextBox(
                    color = Colors.SteelBlue,
                    lineHeight = 32,
                    maxLines = 3,
                    size = 26,
                    text = property.title,
                    weight = FontWeight.Normal,
                )
            }

            Box(modifier = Modifier.height(20.5.px))
            // Amount
            Column() {

                TextBox(
                    text = "Plot Size : " +property.plotSize.toString()+" "+ property.sizeUnit.substring(0,3),
                    maxLines = if (isDescriptionBoxExpanded) null else 3,
                )
                TextBox(
                    text = "Building Size : " +property.buildingSize.toString()+" "+ property.sizeUnit.substring(0,3),
                    maxLines = if (isDescriptionBoxExpanded) null else 3,
                )
            }
            Box(modifier = Modifier.height(20.5.px))
            Box(
                modifier = Modifier
                    .border(1.px, LineStyle.Solid, Styles.WHITE.copyf(alpha = 0.1f))
                    .borderRadius(15.43.px)
                    .fillMaxWidth()
                    .padding(leftRight = 24.px, topBottom = 20.px)
                    .whiteSpace(WhiteSpace.PreLine)
            ) {
                TextBox(
                    text =  property.priceCurrency.substring(0,1) +" "
                            + numberCalculation( property.priceMain )  ,
                    size = 30,
                    color = Styles.BLUE_BORDER,
                )
            }
            Box(modifier = Modifier.height(20.5.px))
            Row() {
                    // Icon
                val userImageUrl = BASE_URL + USER_IMAGES + property.marketedByImage.toString()
                userImageUrl.let { asset ->
                    Image(src = asset, modifier = Modifier.clip(Circle()).size(82.px))
                }
                Box(modifier = Modifier.width(10.5.px))
                Column() {
                    Box(modifier = Modifier.height(2.5.px))
                    TextBox(
                        text = property.marketedByName,
                        maxLines = if (isDescriptionBoxExpanded) null else 3,
                    )
                    TextBox(
                        text = property.marketedByPhone,
                        maxLines = if (isDescriptionBoxExpanded) null else 3,
                    )
                    TextBox(
                        text = property.marketedByEmail,
                        maxLines = if (isDescriptionBoxExpanded) null else 3,
                    )
                }
            }
            Box(modifier = Modifier.height(20.5.px))
            Box(
                modifier = Modifier
                    .border(1.px, LineStyle.Solid, Styles.WHITE.copyf(alpha = 0.1f))
                    .borderRadius(15.43.px)
                    .fillMaxWidth()
                    .padding(leftRight = 24.px, topBottom = 20.px)
                    .whiteSpace(WhiteSpace.PreLine)
            ) {
                SpacedColumn(16) {

                    TextBox(
                        text = property.description,
                        maxLines = if (isDescriptionBoxExpanded) null else 3,
                    )
                    UnderlinedToggleText(
                        isSelected = isDescriptionBoxExpanded,
                        mouseEventState = descriptionToggleMouseEventState,
                        onClick = { isDescriptionBoxExpanded = !isDescriptionBoxExpanded },
                        ref = ref { e -> descriptionToggleRef = e },
                    )
                }
            }
            Box(modifier = Modifier.height(20.5.px))

            propertyTypes(property)
            Box(modifier = Modifier.height(20.5.px))
            propertyAttr(property)

            SpacedRow(
                spacePx = 16,
                modifier = Modifier.fillMaxWidth(),
                centerContentVertically = false,
            ) {
                CommentsSection(modifier = Modifier.weight(1), videoId = videoId)
                segmentedContent?.invoke()
            }
        }
    }
    //HorizontalImageAnimationPage(property)
}

@Composable
fun HorizontalImageAnimationPage(property: Property) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.px) // Set a height for the container
            .overflow(Overflow.Hidden), // Hide overflow for the infinite effect
        contentAlignment = Alignment.Center
    ) {
        // The inner container for images
        Row(
            modifier = Modifier
                .position(Position.Absolute)
                .top(0.px)
                .left(0.px)
                .whiteSpace(WhiteSpace.NoWrap) // Keep images in a single line

        ) {
            // Add images twice to create the infinite loop effect
            val images = listOf(
                BASE_URL + FILES +property.img1,
                BASE_URL + FILES +property.img2,
                BASE_URL + FILES +property.img3,
                BASE_URL + FILES +property.img4,
                BASE_URL + FILES +property.img5,
                BASE_URL + FILES +property.img6,
                BASE_URL + FILES +property.img7,
                BASE_URL + FILES +property.img8,
                BASE_URL + FILES +property.img9,
                BASE_URL + FILES +property.img10 )

            // First set of images
            Box(modifier = Modifier.size( 100.px) ) {
                images.forEach { imgSrc ->
                    Img(
                        src = imgSrc,
                        //modifier = Modifier.height(150.px).margin(right = 1.em)
                    )
                }
            }
            // Second set of images (duplicate)
            /*images.forEach { imgSrc ->
                Img(
                    src = imgSrc,
                    //modifier = Modifier.height(150.px).margin(right = 1.em)
                )
            }*/
        }
    }

}
