package chat.chat.models

import androidx.compose.runtime.Immutable
import chat.core.model.VideoThumbnailDetails

@Immutable
data class SuggestionSectionData(val title: String, val videos: List<VideoThumbnailDetails>)
