package chat.chat.models

import androidx.compose.runtime.Immutable


@Immutable
data class PremiumVehicles(
    val id: String,
    val thumbnailAsset: String,
    val channelAsset: String? = null,
    val title: String,
    val channelName: String?,
    val isVerified: Boolean,
    val views: String,
    val daysSinceUploaded: String,
    val duration: String,
    val likeCount: String? = null,
    val dislikeCount: String? = null,
    val subscribersCount: String? = null,
    val uploadDate: String? = null,
) {

}