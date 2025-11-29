package chat.chat.models

import androidx.compose.runtime.Immutable
import chat.chat.utils.Route


@Immutable
data class NavRailItemData(
    val label: String,
    val route: Route,
    val iconType: NavRailListItemIconType? = null,
    val count: Int = 0,
    val children: List<NavRailItemData>? = null,
    val hasBottomDivider: Boolean = false,
)

