package chat.chat.models

import androidx.compose.runtime.Immutable
import chat.chat.data.fashion.Fashion

@Immutable
data class ExploreGridDetailsPlus(
    val asset: String,
    val title: String,
    val categoriesWithVideos: List<ExploreGridCategoryWithVideosPlus> = emptyList(),
)

@Immutable
data class ExploreGridCategoryWithVideosPlus(
    val label: String? = null,
    val videos: List<Fashion>,
)
