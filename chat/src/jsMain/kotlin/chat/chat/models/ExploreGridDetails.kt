package chat.chat.models

import androidx.compose.runtime.Immutable
import chat.core.model.VideoThumbnailDetails

@Immutable
data class ExploreGridDetails(
    val asset: String,
    val title: String,
    val categoriesWithVideos: List<ExploreGridCategoryWithVideos> = emptyList(),
)


@Immutable
data class ExploreGridCategoryWithVideos(
    val label: String? = null,
    val videos: List<VideoThumbnailDetails>,
)
