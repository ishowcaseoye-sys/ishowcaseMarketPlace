package chat.chat.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import chat.chat.components.sections.NavRail
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gridRow
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.framework.annotations.DelicateApi
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import chat.chat.components.sections.Footer
import chat.chat.components.sections.TopBar
import chat.chat.components.sections.TopBarDefaults
import chat.chat.components.widgets.NotificationPanel
import chat.chat.components.widgets.WorkInProgressSection
import chat.chat.data.repository.DataRepository
import chat.chat.data.repository.DataRepositoryImpl
import chat.chat.pages.AllElectronicPage
import chat.chat.pages.AllFashionPage
import chat.chat.pages.AllFurniturePage
import chat.chat.pages.AllJobSeekersPage
import chat.chat.pages.AllPropertiesPage
import chat.chat.pages.AllVehiclesPage
import chat.chat.pages.ChannelPage
import chat.chat.pages.CollectionPage
import chat.chat.pages.ExplorePage
import chat.chat.pages.HomeScreenPage
import chat.chat.pages.PlaylistPage
import chat.chat.pages.SearchPage
import chat.chat.pages.ShortDetails
import chat.chat.pages.ShortsGrid
import chat.chat.pages.SubscriptionsPage
import chat.chat.pages.TVModePage
import chat.chat.pages.VideoElectronicsPlayerPage
import chat.chat.pages.VideoFashionPlayerPage
import chat.chat.pages.VideoFurniturePlayerPage
import chat.chat.pages.VideoJobSeekersPlayerPage
import chat.chat.pages.VideoPlayerPage
import chat.chat.pages.VideoPropertyPlayerPage
import chat.chat.pages.VideoVehiclePlayerPage
import chat.chat.utils.Constants
import chat.chat.utils.Crossfade
import chat.chat.utils.Dialog
import chat.chat.utils.LocalNavigator
import chat.chat.utils.LocalNotificationPanelManager
import chat.chat.utils.Route
import chat.chat.utils.Styles
import chat.chat.utils.hideScrollBar
import chat.chat.utils.isGreaterThan
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@OptIn(DelicateApi::class)
@Composable
fun MainLayout( ) {

    val context = rememberPageContext()
    val navigator = LocalNavigator.current
    val notificationPanelManager = LocalNotificationPanelManager.current
    val currentRoute by navigator.currentRouteState
    val breakpoint = rememberBreakpoint()
    val dataRepository: DataRepository = DataRepositoryImpl()

    val isLargeBreakpoint by remember(breakpoint) {
        derivedStateOf { breakpoint isGreaterThan Breakpoint.SM }
    }
    val horizontalPaddingState = (if (isLargeBreakpoint) 24f else 12f)
    val isNavRailExpandedState = remember { mutableStateOf(false) }
    val navRailWidthPx = (if (isNavRailExpandedState.value) 250f else 50f)
    // TODO: Create a composition local for this
    val showPersonalisedFeedDialogState = remember { mutableStateOf(false) }

    // Auto close NavRail on item selection for smaller devices
    LaunchedEffect(currentRoute) {

        if (!isLargeBreakpoint) isNavRailExpandedState.value = false
        //dataRepository.runPremiumVehiclesListString()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(
                modifier = Modifier.background(Styles.SURFACE)
                    .padding(leftRight = horizontalPaddingState.px)
                    .position(Position.Sticky)
                    .top(0.px)
                    .zIndex(1),
                onLogoClick = { navigator.pushRoute(Route.Home) },
                onDrawerButtonClick = {
                    isNavRailExpandedState.value = !isNavRailExpandedState.value
                },
                onOpenNotificationPanel = notificationPanelManager::open,
                onSearch = { query -> navigator.pushRoute(Route.Search(query)) },
                context = context
            )
            Row(modifier = Modifier.fillMaxSize()) {
                NavRail(
                    modifier = Modifier.height(100.vh - TopBarDefaults.HEIGHT)
                        .position(Position.Sticky)
                        .top(TopBarDefaults.HEIGHT)
                        .width(navRailWidthPx.px)
                        .margin(leftRight = horizontalPaddingState.px)
                        .overflow(overflowX = Overflow.Hidden, overflowY = Overflow.Scroll)
                        .hideScrollBar(),
                    isExpandedState = isNavRailExpandedState,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(100.percent - navRailWidthPx.px)
                        .fillMaxHeight()
                        .overflow(overflowX = Overflow.Scroll, overflowY = Overflow.Hidden)
                        .hideScrollBar()
                ) {

                    Crossfade(
                        // TODO: Substring this route to avoid reloading common parts of a page
                        targetState = currentRoute,
                        modifier = Modifier
                            .fillMaxWidth(100.percent - horizontalPaddingState.px)
                            .fillMaxHeight()
                            .minWidth(Constants.MOBILE_MAX_AVAILABLE_WIDTH.px)
                            .padding(top = Constants.CONTENT_PADDING),
                        onStateChange = { window.scrollTo(0.0, 0.0) },
                        animateTranslationY = false,
                    ) { animatedRoute ->
                        when (animatedRoute) {

                                is Route.VideoProperty -> {
                                    console.info("About to call VideoPropertyPlayerPage..")
                                    VideoPropertyPlayerPage(id = animatedRoute.id)
                                }
                                is Route.Video -> {
                                    VideoPlayerPage(id = animatedRoute.id)
                                }

                                is Route.VideoVehicle-> {
                                    VideoVehiclePlayerPage(id = animatedRoute.id)
                                }

                                is Route.VideoFashion -> {
                                    console.info("About to call VideoFashionPlayerPage..")
                                    VideoFashionPlayerPage(id = animatedRoute.id)
                                }

                                is Route.VideoFurniture -> {
                                    console.info("About to call VideoFurniturePlayerPage..")
                                    VideoFurniturePlayerPage(id = animatedRoute.id)
                                }

                                is Route.VideoElectronics -> {
                                    console.info("About to call VideoElectronicPlayerPage..")
                                    VideoElectronicsPlayerPage(id = animatedRoute.id)
                                }

                                is Route.VideoJobSeekers -> {
                                    console.info("About to call VideoJobSeekersPlayerPage..")
                                    VideoJobSeekersPlayerPage(id = animatedRoute.id)
                                }

                                Route.Home -> HomeScreenPage(
                                    showPersonalisedFeedDialogState = showPersonalisedFeedDialogState,
                                )

                                is Route.Vehicle-> {
                                    //VehiclePage(id = animatedRoute.id)
                                }

                                is Route.Collection -> {
                                    CollectionPage(collectionId = animatedRoute.id)
                                }

                                Route.Explore -> {
                                    console.info("info: Explore list size" )
                                    ExplorePage(
                                        Modifier.padding(bottom = Constants.CONTENT_PADDING)
                                    )
                                }

                                Route.History -> {
                                    //HistoryPage()
                                }

                                /*  Route.Home -> HomePage(
                                    showPersonalisedFeedDialogState = showPersonalisedFeedDialogState,
                                )*/


                                Route.LikedVideos -> {
                                   /* WorkInProgressSection(
                                        modifier = Modifier.align(Alignment.Center)
                                    )*/
                                }

                                is Route.Page -> {
                                    ChannelPage(
                                        id = animatedRoute.id,
                                        initialTab = animatedRoute.selectedTab,
                                    )
                                }

                                is Route.Playlist -> {
                                    PlaylistPage(id = animatedRoute.id)
                                }

                                Route.Playlists -> {
                                    //AllPlaylistsPage()
                                }

                                is Route.Short -> {
                                    console.info("MainLayout().Route.Short 1 animatedRoute.id = " + animatedRoute.id)
                                    ShortDetails(
                                        id = animatedRoute.id,
                                        onBackPressed = navigator::pop,
                                    )
                                }

                                is Route.ShortsVehicle -> {
                                    console.info("MainLayout().Route.Short 1")
                                    ShortDetails(
                                        id = animatedRoute.id,
                                        onBackPressed = navigator::pop,
                                    )
                                }

                                is Route.ShortsFashion -> {
                                    console.info("MainLayout().Route.Short 1")
                                    ShortDetails(
                                        id = animatedRoute.id,
                                        onBackPressed = navigator::pop,
                                    )
                                }


                                is Route.ShortsFurniture -> {
                                    console.info("MainLayout().Route.Short 1")
                                    ShortDetails(
                                        id = animatedRoute.id,
                                        onBackPressed = navigator::pop,
                                    )
                                }


                                is Route.ShortsElectronics -> {
                                    console.info("MainLayout().Route.Short 1")
                                    ShortDetails(
                                        id = animatedRoute.id,
                                        onBackPressed = navigator::pop,
                                    )
                                }


                                is Route.ShortsJobSeekers -> {
                                    console.info("MainLayout().Route.Short 1")
                                    ShortDetails(
                                        id = animatedRoute.id,
                                        onBackPressed = navigator::pop,
                                    )
                                }

                                Route.Shorts -> {
                                    //console.info("MainLayout().Route.Short 2")
                                      ShortsGrid(showPersonalisedFeedDialogState)
                                }

                                /*Route.Shorts -> {
                                     ShortsGrid(showPersonalisedFeedDialogState)
                                }*/

                                Route.Collections, Route.Subscriptions -> {
                                    SubscriptionsPage(
                                        isSubscriptionsCategorySelected = animatedRoute == Route.Subscriptions,
                                    )
                                }

                                Route.TVMode -> {
                                     TVModePage()
                                }

                                Route.WatchLater ->  {
                                      WorkInProgressSection(
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                            is Route.Search -> {
                                SearchPage(query = animatedRoute.query)
                            }

                            is Route.AllProperties -> {
                                AllPropertiesPage(query = animatedRoute.query)
                            }

                            is Route.AllVehicles -> {
                                AllVehiclesPage(query = animatedRoute.query)
                            }

                            is Route.AllFurniture -> {
                                AllFurniturePage(query = animatedRoute.query)
                            }

                            is Route.AllFashion -> {
                                AllFashionPage(query = animatedRoute.query)
                            }

                            is Route.AllElectronic -> {
                                AllElectronicPage(query = animatedRoute.query)
                            }

                            is Route.AllJobSeeker -> {
                                AllJobSeekersPage(query = animatedRoute.query)
                            }
                        }
                    }
                }
            }
        }

        Footer(Modifier.fillMaxWidth().gridRow(2))

        Dialog(
            isDisplayed = showPersonalisedFeedDialogState.value,
            onDismissed = { showPersonalisedFeedDialogState.value = false },
            content = {
                // PersonalisedFeedDialog(onClose = { showPersonalisedFeedDialogState.value = false })
            },
        )

        Dialog(
            modifier = Modifier.fillMaxSize().padding(top = 64.px, right = 16.px),
            isDisplayed = notificationPanelManager.isOpen,
            scrimDimFactor = 0.1f,
            contentAlignment = Alignment.TopEnd,
            onDismissed = notificationPanelManager::close,
            content = {
                 NotificationPanel()
                 },
        )
    }
}


