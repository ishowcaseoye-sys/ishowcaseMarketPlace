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
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.rowGap
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.clip
import chat.chat.data.BASE_URL
import chat.chat.data.FILES
import chat.chat.data.jobseeker.JobSeeker
import chat.chat.utils.dateFormat
import chat.chat.utils.numberCalculation
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.w3c.dom.Element

@Composable
fun PlayerAndCommentsJobSeekers(
    modifier: Modifier = Modifier,
    jobSeeker: JobSeeker,
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
    val videoStr = BASE_URL + FILES + jobSeeker.video1

    Row() {

        Column(modifier = modifier.margin (top=1.px) ) {

            Box(modifier = modifier.minWidth(540.px)) {
                VideoPlayerProperty(
                    videoId = videoStr,
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

            /* TextBox(
                 maxLines = 2,
                 size = 20,
                 text = videoDetails.title,
                 weight = FontWeight.SemiBold,
             )
             Box(modifier = Modifier.height(12.5.px))

             */
            // Video Info
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

                    /*SpacedRow(
                        ref = ref { e -> channelRowRef = e },
                        spacePx = 15,
                        modifier = Modifier.clickable {
                            navigator.pushRoute(Route.Page(id = videoDetails.propTypeId.toString()))
                        }
                    ) {
                        val userImageUrl =
                            BASE_URL + USER_IMAGES + videoDetails.marketedByImage.toString()
                        Image(
                            modifier = Modifier.size(46.px).clip(Circle()),
                            src = userImageUrl,
                        )
                        SpacedColumn(1.29) {
                            SpacedRow(8) {
                                TextBox(
                                    modifier = Modifier.thenIf(isChannelRowHovered) {
                                        Modifier.textDecorationLine(TextDecorationLine.Underline)
                                    },
                                    text = videoDetails.priceMain.toString(),
                                    size = 18,
                                    lineHeight = 28.3,
                                    weight = FontWeight.Medium,
                                )
                                Image(src = Asset.Icon.VERIFIED_BADGE)
                            }
                            TextBox(
                                text = "${videoDetails.id.toString()} subscribers",
                                size = 14,
                                color = Styles.VIDEO_CARD_SECONDARY_TEXT,
                            )
                        }
                    }
                    SubscribeButton()*/
                }
                SpacedRow(24) {
                    IconLabel(iconAsset = Asset.Icon.EYE, label = jobSeeker.id.toString())
                    IconLabel(iconAsset = Asset.Icon.DATE, label = dateFormat(jobSeeker.whenAvailableToStart))
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
                        labelLeft = "23",   //videoDetails.likeCount,
                        labelRight = "2",  //videoDetails.dislikeCount,
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

            // Description
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
                        text = jobSeeker.description,
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

            Box(modifier = Modifier.height(31.px))

            // Comments
            SpacedRow(
                spacePx = 16,
                modifier = Modifier.fillMaxWidth(),
                centerContentVertically = false,
            ) {
                CommentsSection(modifier = Modifier.weight(1), videoId = videoId)
                segmentedContent?.invoke()
            }
        }

        Box(modifier = Modifier.width(30.5.px))

        Column(modifier = modifier.margin (top=1.px).padding(left=16.px, right=16.px )) {
            /**************
            // Right side
             **************/
            Column(modifier = Modifier.flexWrap(FlexWrap.Wrap)) {
                TextBox(
                    color = Colors.RoyalBlue,
                    lineHeight = 52,
                    maxLines = 1,
                    size = 62,
                    text = "Job Seekers",
                    weight = FontWeight.Normal,
                )
                Box(modifier = Modifier.height(10.5.px))
                TextBox(
                    color = Colors.SteelBlue,
                    //color = Styles.VIDEO_CARD_PRIMARY_TEXT,
                    lineHeight = 32,
                    maxLines = 3,
                    size = 26,
                    text = jobSeeker.title,
                    weight = FontWeight.Normal,
                )
            }

            Box(modifier = Modifier.height(20.5.px))
            // Amount
            Box(
                modifier = Modifier
                    .border(1.px, LineStyle.Solid, Styles.WHITE.copyf(alpha = 0.1f))
                    .borderRadius(15.43.px)
                    .fillMaxWidth()
                    .padding(leftRight = 24.px, topBottom = 20.px)
                    .whiteSpace(WhiteSpace.PreLine)
            ) {
                TextBox(
                    text =  jobSeeker.expSalaryCurrency.substring(0,1) +" "
                            + numberCalculation( jobSeeker.expSalaryMain )  ,
                    size = 30,
                    color = Styles.BLUE_BORDER,
                )
            }
            Box(modifier = Modifier.height(20.5.px))
            Row() {
                // Icon
                val userImageUrl = BASE_URL + FILES + jobSeeker.img1.toString()
                userImageUrl.let { asset ->
                    Image(src = asset, modifier = Modifier.clip(Circle()).size(82.px))
                }
                Box(modifier = Modifier.width(10.5.px))
                Column() {
                    TextBox(
                        text = jobSeeker.firstname+" "+jobSeeker.surname ,
                        maxLines = if (isDescriptionBoxExpanded) null else 3,
                        modifier = Modifier.fontSize(38.px)
                    )
                    TextBox(
                        text = jobSeeker.telephone,
                        maxLines = if (isDescriptionBoxExpanded) null else 3,
                    )

                    TextBox(
                        text = jobSeeker.email,
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
                        text = jobSeeker.description,
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
            Box(modifier = Modifier.height(30.5.px))

            /*
                    fashionTypes(fashion)
                    Box(modifier = Modifier.height(20.5.px))
                    fashionAttr(fashion)
            */

        }
    }
}
