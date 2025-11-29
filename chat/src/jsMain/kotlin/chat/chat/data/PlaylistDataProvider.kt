package chat.chat.data

import chat.core.model.VideoThumbnailDetails
import chat.chat.models.PlaylistItemData

class PlaylistDataProvider {
    //fun getAllPlaylists(): List<PlaylistItemData> = IN_MEMORY_PLAYLISTS
    fun getAllPlaylists(): List<PlaylistItemData> = listOf()

    fun getPlaylistForId(id: String): PlaylistItemData {
        //return IN_MEMORY_PLAYLISTS.find { it.id == id } ?: IN_MEMORY_PLAYLISTS.first()
        return TODO()
    }

    fun getVideosForPlaylistWithId(@Suppress("UNUSED_PARAMETER") id: String): List<VideoThumbnailDetails> {
        /*return IN_MEMORY_THUMBNAIL_DETAILS.filter { details ->
            details.id == "m9kd28VOTLK" ||
                    details.id == "p3va17HYCEF" ||
                    details.id == "u2qm13POIUYT" ||
                    details.id == "o8zj46LKJHGF"
        }*/
        return listOf()
    }
}
