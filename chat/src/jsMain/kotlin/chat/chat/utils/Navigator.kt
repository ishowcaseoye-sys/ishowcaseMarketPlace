package chat.chat.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.staticCompositionLocalOf
import chat.chat.pages.ChannelTab
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Stable
class Navigator(private val initialRoute: Route) : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val currentRouteFlow = MutableStateFlow(initialRoute)

    val currentRouteState: State<Route>
        @Composable get() = currentRouteFlow.collectAsState(initialRoute)

    init {
        launch {
            window.addEventListener(
                type = "popstate",
                callback = {
                    val path = with(window.location) { pathname + search }
                    Route.valueOf(path)?.let { safeRoute ->
                        currentRouteFlow.tryEmit(safeRoute)
                    }
                },
            )
        }
    }

    fun pushRoute(route: Route) {
        if (currentRouteFlow.value == route) {
            println("Provided route is same as the current route. Push event ignored.")
            return
        }
        window.history.pushState(route, "", route.path)
        currentRouteFlow.tryEmit(route)
    }

    fun pop() = window.history.back()
}

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("Please override the compositionLocal with a Navigator object!")
}

sealed class Route(val path: String) {
    data object Home : Route(path = HOME)
    //data object Vehicle : Route(path = VEHICLE)
    data object Explore : Route(path = EXPLORE)
    data object Shorts : Route(path = SHORTS)
    data class Short(val id: String) : Route(path = "$SHORTS/$id")
    data class ShortsVehicle(val id: String ) : Route(path = "$SHORTS_VEHICLE/$id")
    /******* My Insertion *********/
    data class ShortsFashion(val id: String ) : Route(path = "$SHORTS_FASHION/$id")
    data class ShortsFurniture(val id: String ) : Route(path = "$SHORTS_FURNITURE/$id")
    data class ShortsElectronics(val id: String ) : Route(path = "$SHORTS_ELECTRONICS/$id")
    data class ShortsJobSeekers(val id: String ) : Route(path = "$SHORTS_JOBSEEKERS/$id")
    /******* My Insertion *********/
    data object History : Route(path = HISTORY)
    data object TVMode : Route(path = TV_MODE)
    data object WatchLater : Route(path = WATCH_LATER)
    data object LikedVideos : Route(path = LIKED_VIDEOS)
    data object Playlists : Route(path = PLAYLISTS)
    data class Playlist(val id: String) : Route(path = "$PLAYLIST$id")
    data object Collections : Route(path = COLLECTIONS)
    data class Collection(val id: String) : Route(path = "$COLLECTION$id")
    data object Subscriptions : Route(path = SUBSCRIPTIONS)

    data class Page(val id: String, val selectedTab: ChannelTab? = null) :
        Route(path = "$PAGE$id/${selectedTab?.label.orEmpty()}")

    data class Vehicle(val id: String) : Route(path = "$VIDEO$id")
    data class VideoVehicle(val id: String) : Route(path = "$VIDEO$id")
    data class VideoProperty(val id: String) : Route(path = "$VIDEO$id")
/***** My Inclusion ********/

    data class VideoFashion(val id: String) : Route(path = "$VIDEO$id")
    data class VideoFurniture(val id: String) : Route(path = "$VIDEO$id")
    data class VideoElectronics(val id: String) : Route(path = "$VIDEO$id")
    data class VideoJobSeekers(val id: String) : Route(path = "$VIDEO$id")
/***** End  ****************/
    data class Video(val id: String) : Route (path = "$VIDEO$id")
    data class Search(val query: String) : Route (path = "$SEARCH$query")
    data class AllProperties(val query: String) : Route(path = "$SEARCH$query")
    data class AllVehicles(val query: String) : Route(path = "$SEARCH$query")
    data class AllFurniture(val query: String) : Route(path = "$SEARCH$query")
    data class AllFashion(val query: String) : Route(path = "$SEARCH$query")
    data class AllElectronic(val query: String) : Route(path = "$SEARCH$query")
    data class AllJobSeeker(val query: String) : Route(path = "$SEARCH$query")

    companion object {
        fun valueOf(path: String): Route? {
            return when {
                path == HOME || path.isEmpty() -> Home
                path.contains(EXPLORE) -> Explore
                path == SHORTS -> Shorts
                path.contains(SHORTS) -> Short(id = path.substringAfterLast('/'))
                path.contains(SHORTS_VEHICLE) -> ShortsVehicle(id =  path.trim())
                /******* My Insertion *****/
                path.contains(SHORTS_FASHION) -> ShortsVehicle(id =  path.trim())
                path.contains(SHORTS_FURNITURE) -> ShortsVehicle(id =  path.trim())
                path.contains(SHORTS_ELECTRONICS) -> ShortsVehicle(id =  path.trim())
                path.contains(SHORTS_JOBSEEKERS) -> ShortsVehicle(id =  path.trim())

                /****** End   ************/
                path.contains(HISTORY) -> History
                path.contains(TV_MODE) -> TVMode
                path.contains(WATCH_LATER) -> WatchLater
                path.contains(LIKED_VIDEOS) -> LikedVideos
                path.contains(PLAYLISTS) -> Playlists
                path.contains(PLAYLIST) -> Playlist(id = path.substringAfterLast('='))
                path.contains(COLLECTIONS) -> Collections
                path.contains(COLLECTION) -> Collection(id = path.substringAfterLast('='))
                path.contains(SUBSCRIPTIONS) -> Subscriptions

                path.contains(PAGE) -> Page(
                    id = path.substringAfterLast('@').substringBeforeLast('/'),
                    selectedTab = ChannelTab.entries.firstOrNull { tab ->
                        tab.label == path.substringAfterLast('/')
                    } ?: ChannelTab.Home,
                )
                path.contains(VEHICLE) -> Video(id = path.substringAfterLast('='))
                path.contains(VIDEO_VEHICLE) -> Video(id = path.substringAfterLast('='))
                path.contains(VIDEO_PROPERTY) -> Video(id = path.substringAfterLast('='))
                path.contains(VIDEO) -> Video(id = path.substringAfterLast('='))
                path.contains(SEARCH) -> Search(query = path.substringAfterLast('='))
                /******** My Insertion ********/
                path.contains(VIDEO_FASHION) -> Video(id = path.substringAfterLast('='))
                path.contains(FASHION) -> Video(id = path.substringAfterLast('='))

                path.contains(VIDEO_FURNITURE) -> Video(id = path.substringAfterLast('='))
                path.contains(FURNITURE) -> Video(id = path.substringAfterLast('='))

                path.contains(VIDEO_ELECTRONICS) -> Video(id = path.substringAfterLast('='))
                path.contains(ELECTRONICS) -> Video(id = path.substringAfterLast('='))

                path.contains(VIDEO_JOBSEEKERS) -> Video(id = path.substringAfterLast('='))
                path.contains(JOBSEEKERS) -> Video(id = path.substringAfterLast('='))

                /**** End  ******/
                else -> null
            }
        }
    }
}

private const val HOME = "/"
private const val EXPLORE = "/feed/trending"
private const val SHORTS = "/shorts"
private const val SHORTS_VEHICLE = "/shorts"
/******* My Insertion  *******/
private const val SHORTS_FASHION = "/shorts"
private const val SHORTS_FURNITURE = "/shorts"
private const val SHORTS_ELECTRONICS = "/shorts"
private const val SHORTS_JOBSEEKERS = "/shorts"
/******* End  *******/
private const val HISTORY = "/feed/history"
private const val TV_MODE = "/tv_mode"
private const val WATCH_LATER = "/playlist?list=WL"
private const val LIKED_VIDEOS = "/playlist?list=LL"
private const val PLAYLISTS = "/feed/playlists"
private const val PLAYLIST = "/playlist?list="
private const val COLLECTIONS = "/collections"
private const val COLLECTION = "/collection?list="
private const val SUBSCRIPTIONS = "/feed/subscriptions"
private const val PAGE = "/@"
private const val VIDEO = "/watch?v="
private const val VIDEO_PROPERTY = "/watch?v="
private const val VIDEO_VEHICLE = "/watch?v="
private const val VEHICLE = "/watch?v="
/******* My Insertion  *******/
private const val VIDEO_FASHION = "/watch?v="
private const val FASHION = "/watch?v="

private const val VIDEO_FURNITURE = "/watch?v="
private const val FURNITURE = "/watch?v="

private const val VIDEO_ELECTRONICS = "/watch?v="
private const val ELECTRONICS = "/watch?v="

private const val VIDEO_JOBSEEKERS = "/watch?v="
private const val JOBSEEKERS = "/watch?v="

/******* End  *******/
private const val SEARCH = "/results?search_query="
private const val ALL_PROPERTIES = "/results?search_query="
