package chat.chat.data

import chat.chat.data.property.Property
import chat.chat.data.vehicle.Vehicle
import chat.chat.models.ShortThumbnailDetails
import chat.chat.utils.convertPropertyJsonStringToList
import chat.chat.utils.convertVehicleJsonStringToList
import chat.chat.data.propertyJsonString
import chat.chat.data.vehicleJsonString


class ShortsDataProvider {
    fun provideAllShorts(): List<ShortThumbnailDetails> {
        //return List(10) { IN_MEMORY_SHORTS_DETAILS }.flatten()
        return listOf()
    }

    fun getOnePremiumVehicle(vehicleId: String): List<Vehicle> {
        return convertVehicleJsonStringToList( vehicleJsonString.toString() ).filter{ it.id == vehicleId.toInt() }
    }


    fun getPremiumVehicleFeed(): List<Vehicle> {
        return convertVehicleJsonStringToList( vehicleJsonString.toString() )
    }

    fun getAllPropertiesFeed(): List<Property> {
        return convertPropertyJsonStringToList( propertyJsonString.toString() ).subList(0,10)
    }


    fun getShortSuggestionsForId(id: String): List<ShortThumbnailDetails> {
        return listOf()
        //return IN_MEMORY_SHORTS_DETAILS.filterNot { it.id == id }.plus(IN_MEMORY_SHORTS_DETAILS)
    }
}
