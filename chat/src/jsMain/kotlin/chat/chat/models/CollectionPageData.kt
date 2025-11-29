package chat.chat.models

import androidx.compose.runtime.Immutable
import chat.core.model.VideoThumbnailDetails

@Immutable
data class CollectionPageData(val name: String, val videos: List<VideoThumbnailDetails>)
