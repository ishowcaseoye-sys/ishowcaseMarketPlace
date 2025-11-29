package chat.chat.models

import androidx.compose.runtime.Immutable
import chat.core.model.VideoThumbnailDetails

@Immutable
data class ThumbnailGridData(val date: String, val thumbnailDetails: List<VideoThumbnailDetails>)
