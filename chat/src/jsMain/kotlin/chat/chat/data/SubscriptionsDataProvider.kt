package chat.chat.data

import chat.chat.models.ChannelListItemData
import chat.chat.models.ThumbnailGridData

class SubscriptionsDataProvider {
    fun getDataByTimeline(): List<ThumbnailGridData> {
        return listOf(
            /*ThumbnailGridData(
                date = "Today - 9 Mar 2025",
                thumbnailDetails = IN_MEMORY_THUMBNAIL_DETAILS.filter { details ->
                    details.id == "m9kd28VOTLK" ||
                            details.id == "y9br63ASDFGHJ" ||
                            details.id == "h2lk95QWERTYU" ||
                            details.id == "o4vx28HJKLZXCV"
                },
            ),
            ThumbnailGridData(
                date = "Yesterday - 8 Mar 2025",
                thumbnailDetails = IN_MEMORY_THUMBNAIL_DETAILS.filter { details ->
                    details.id == "w6cm71MNBVCXZA" ||
                            details.id == "e8qz34POIUYTRE" ||
                            details.id == "k1fj50LKJHGFDS" ||
                            details.id == "r7dt89ZXCVBNMA"
                },
            ),
            ThumbnailGridData(
                date = "7 Mar 2025",
                thumbnailDetails = IN_MEMORY_THUMBNAIL_DETAILS.filter { details ->
                    details.id == "m9kd28VOTLK" ||
                            details.id == "y9br63ASDFGHJ" ||
                            details.id == "h2lk95QWERTYU" ||
                            details.id == "o4vx28HJKLZXCV"
                },
            ), */
        )
    }

    fun getDataByChannel(): List<List<ChannelListItemData>> {
        return listOf(
           /* listOf(
                IN_MEMORY_THUMBNAIL_DETAILS
                    .first { it.id == "m9kd28VOTLK" }
                    .toChannelListItemThumbnailDetails(),
                FAKE_CHANNEL_POST,
                IN_MEMORY_THUMBNAIL_DETAILS
                    .first { it.id == "u2qm13POIUYT" }
                    .toChannelListItemThumbnailDetails(),
                IN_MEMORY_THUMBNAIL_DETAILS
                    .first { it.id == "p3va17HYCEF" }
                    .toChannelListItemThumbnailDetails(),
            ),
            listOf(
                IN_MEMORY_THUMBNAIL_DETAILS
                    .first { it.id == "w5ak28QAZXSW" }
                    .toChannelListItemThumbnailDetails(),
                FAKE_CHANNEL_POST,
                IN_MEMORY_THUMBNAIL_DETAILS
                    .first { it.id == "y9br63ASDFGHJ" }
                    .toChannelListItemThumbnailDetails(),
            ),
            listOf(
                IN_MEMORY_THUMBNAIL_DETAILS
                    .first { it.id == "t8dg34CVBJNM" }
                    .toChannelListItemThumbnailDetails(),
                FAKE_CHANNEL_POST,
                IN_MEMORY_THUMBNAIL_DETAILS
                    .first { it.id == "h2lk95QWERTYU" }
                    .toChannelListItemThumbnailDetails(),
            ),*/
        )
    }
}