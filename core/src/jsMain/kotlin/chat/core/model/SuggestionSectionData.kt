package chat.core.model

import androidx.compose.runtime.Immutable

@Immutable
data class SuggestionSectionData(val title: String, val videos: List<VideoThumbnailDetails>)
