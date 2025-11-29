package chat.chat.components.widgets.player

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import chat.chat.pages.SegmentedContentType
import chat.chat.utils.LocalNavigator
import chat.chat.utils.rememberIsLargeBreakpoint
import chat.chat.utils.rememberMouseEventAsState
import com.varabyte.kobweb.compose.ui.Modifier
import org.w3c.dom.Element

@Composable
fun PlayerAndComments(
    modifier: Modifier = Modifier,
    videoId: String,
    selectedSegment: MutableState<SegmentedContentType>,
    isTheaterModeOn: MutableState<Boolean>,
    isSegmentedContentVisible: MutableState<Boolean>,
    segmentedContent: (@Composable () -> Unit)?,
) {
    console.info("PlayerAndComments Begin")
    val navigator = LocalNavigator.current
    val isLargeBreakpoint by rememberIsLargeBreakpoint()

    var isDescriptionBoxExpanded by remember { mutableStateOf(false) }
    var descriptionToggleRef by remember { mutableStateOf<Element?>(null) }
    val descriptionToggleMouseEventState by rememberMouseEventAsState(descriptionToggleRef)

/*
    // Data States
    val videoPlayerDataProvider = remember { VideoPlayerDataProvider() }
    val videoDetails = remember(videoPlayerDataProvider, videoId) {
        videoPlayerDataProvider.getVideoDetailsForId(videoId)
    }


    Column(modifier = modifier) {
        VideoPlayer(
            videoId = videoId,
            isTheaterModeOn = isTheaterModeOn,
            selectedSegment = selectedSegment,
        )

        Box(modifier = Modifier.height(13.px) as androidx.compose.ui.Modifier)

        TextBox(
            maxLines = 2,
            size = 20,
            text = videoDetails.title,
            weight = FontWeight.SemiBold,
        )
        Box(modifier = Modifier.height(12.5.px) as androidx.compose.ui.Modifier)

        // Video Info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .display(DisplayStyle.Flex)
                .flexWrap(FlexWrap.Wrap)
                .columnGap(20.px)
                .rowGap(20.px) as androidx.compose.ui.Modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SpacedRow(20) {
                var channelRowRef by remember { mutableStateOf<Element?>(null) }
                val isChannelRowHovered =
                    rememberMouseEventAsState(channelRowRef).value == MouseEventState.Hovered

                SpacedRow(
                    ref = ref { e -> channelRowRef = e },
                    spacePx = 15,
                    modifier = Modifier.clickable {
                        navigator.pushRoute(Route.Page(id = videoDetails.channelId))
                    }
                ) {
                    Image(
                        modifier = Modifier.size(46.px).clip(Circle()),
                        src = videoDetails.channelAsset,
                    )
                    SpacedColumn(1.29) {
                        SpacedRow(8) {
                            TextBox(
                                modifier = Modifier.thenIf(isChannelRowHovered) {
                                    Modifier.textDecorationLine(TextDecorationLine.Underline)
                                },
                                text = videoDetails.channelName,
                                size = 18,
                                lineHeight = 28.3,
                                weight = FontWeight.Medium,
                            )
                            Image(src = Asset.Icon.VERIFIED_BADGE)
                        }
                        TextBox(
                            text = "${videoDetails.subscribersCount} subscribers",
                            size = 14,
                            color = Styles.VIDEO_CARD_SECONDARY_TEXT,
                        )
                    }
                }
                SubscribeButton()
            }
            SpacedRow(24) {
                IconLabel(iconAsset = Asset.Icon.EYE, label = videoDetails.viewCount)
                IconLabel(iconAsset = Asset.Icon.DATE, label = videoDetails.uploadDate)
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
                    labelLeft = videoDetails.likeCount,
                    labelRight = videoDetails.dislikeCount,
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
                    text = videoDetails.description,
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

*/


}
