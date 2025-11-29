package ishowcase.data

import androidx.compose.runtime.Composable
import chat.chat.data.allVehiclesCommentsJsonString
import chat.core.model.VideoThumbnailDetails
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.margin
import chat.chat.components.widgets.FashionThumbnailCard
import chat.chat.components.widgets.PropertyThumbnailCard
import chat.chat.data.electronic.Electronic
import chat.chat.data.electronicsJsonString
import chat.chat.data.fashion.Fashion
import chat.chat.data.fashionJsonString
import chat.chat.data.furniture.Furniture
import chat.chat.data.furnitureJsonString
import chat.chat.data.jobSeekersJsonString
import chat.chat.data.jobseeker.JobSeeker
import chat.chat.data.property.Property
import chat.chat.data.property.PropertyComment
import chat.chat.data.propertyJsonString
import chat.chat.utils.convertPropertyJsonStringToList
import chat.chat.utils.convertVehicleJsonStringToList
import chat.chat.data.vehicle.Vehicle
import chat.chat.data.vehicleJsonString
import chat.chat.utils.convertElectronicJsonStringToList
import chat.chat.utils.convertFashionJsonStringToList
import chat.chat.utils.convertFurnitureJsonStringToList
import chat.chat.utils.convertJobSeekerJsonStringToList
import chat.chat.utils.convertPropertyCommentsJsonStringToList
import chat.chat.utils.convertVehicleCommentsJsonStringToList
import org.jetbrains.compose.web.css.px

class FeedProvider {

    fun getPremiumVehicleFeed(): List<Vehicle> {
        return convertVehicleJsonStringToList( vehicleJsonString.toString() ).subList(0,10)
    }

    fun getAllVehicleFeed(): List<Vehicle> {
        return convertVehicleJsonStringToList( allVehiclesJsonString.toString() ).subList(0,80)
    }

    fun getTrendingNowFeed(): List<VideoThumbnailDetails> {
        return listOf()
        //convertPropertyJsonStringToList( propertyJsonString.toString() ).subList(0,10)
    }

    fun getNewVideoFeed(): List<VideoThumbnailDetails> {
        //return convertPropertyJsonStringToList( propertyJsonString.toString() ).subList(0,10)
        return listOf()
    }

    fun getAllPropertiesFeed(): List<Property> {
        //console.info("Getting data from getAllPropertiesFeed ")
        val properties = convertPropertyJsonStringToList( propertyJsonString.toString() )
            .subList(0,10)
        //console.info("Getting data from getAllPropertiesFeed vehicles.size = "+vehicles.size )
        return properties
    }

    fun getAllFashionFeed(): List<Fashion> {
        //console.info("Getting data from getAllPropertiesFeed ")
        val fashions = convertFashionJsonStringToList( fashionJsonString.toString() )
            .subList(0,10)
        //console.info("Getting data from getAllPropertiesFeed vehicles.size = "+vehicles.size )
        return fashions
    }

    fun getAllElectronicsFeed(): List<Electronic> {
        //console.info("Getting data from getAllPropertiesFeed ")
        val electronics = convertElectronicJsonStringToList( electronicsJsonString.toString() )
            .subList(0,24)
        //console.info("Getting data from electronics electronics.size = "+electronics.size )
        return electronics
    }

    fun getAllFurnitureFeed(): List<Furniture> {
        //console.info("Getting data from getAllPropertiesFeed ")
        val electronics = convertFurnitureJsonStringToList( furnitureJsonString.toString() )
            .subList(0,17)
        console.info("Getting data from electronics electronics.size = "+electronics.size )
        return electronics
    }

    fun getAllJobSeekersFeed(): List<JobSeeker> {
        //console.info("Getting data from getAllPropertiesFeed ")
        val jobSeekers = convertJobSeekerJsonStringToList( jobSeekersJsonString.toString() )
            .subList(0,20)
        console.info("Getting data from jobSeekers jobSeekers.size = "+jobSeekers.size )
        return jobSeekers
    }

    /*fun getAllVehicleCommentsFeed(): List<VehicleComment> {
        //console.info("Getting data from getAllPropertiesFeed ")
        val vehicleComments = convertVehicleCommentsJsonStringToList( allVehiclesCommentsJsonString.toString() )
            .subList(0,20)
        console.info("Getting data from vehicleComments vehicleComments.size = "+vehicleComments.size )
        return vehicleComments
    }*/

    fun getAllPropertyCommentsFeed(): List<PropertyComment> {
        //console.info("Getting data from getAllPropertiesFeed ")
        val propertyComments = convertPropertyCommentsJsonStringToList( allVehiclesCommentsJsonString.toString() )
            .subList(0,20)
        console.info("Getting data from propertyComments propertyComments.size = "+propertyComments.size )
        return propertyComments
    }

}
