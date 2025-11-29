package chat.chat.data

import chat.chat.data.property.Property
import chat.chat.utils.convertPropertyJsonStringToList
import ishowcase.data.allPropertyJsonString


class SearchDataProvider {
    fun getSearchedVideosForQuery(@Suppress("UNUSED_PARAMETER") query: String): List<Property> {

            //console.info("Getting data from getAllPropertiesFeed ")
            val properties = convertPropertyJsonStringToList( allPropertyJsonString.toString() )
                .subList(0, 35)
            //console.info("Getting data from getAllPropertiesFeed vehicles.size = "+vehicles.size )
            return properties

    }
}
