package chat.chat.data.repository

import chat.chat.data.property.Property
import chat.chat.utils.Asset
import chat.chat.utils.convertPropertyJsonStringToList
import chat.chat.utils.convertVehicleJsonStringToList
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import chat.chat.data.vehicle.Vehicle

interface DataRepository {
    suspend fun runVehicles(theme: Int)
    suspend fun runProperties(theme: Int)
    suspend fun getVehiclesFlow( ) : Flow<List<Vehicle>>
    fun getVehiclesList( ) : List<Vehicle>
    suspend fun runPremiumVehiclesListString( )
    suspend fun loadProperty()
}

class DataRepositoryImpl(
) : DataRepository {

    var cachedVehicleData: MutableList<Vehicle> = mutableListOf()
    var cachedPremiumVehicleData: MutableList<Vehicle> = mutableListOf()
    var cachedPropertyData: MutableList<Property> = mutableListOf()
    var cachedPremiumPropertyData: MutableList<Property> = mutableListOf()

    override suspend fun runVehicles(theme: Int)  {
        console.info("Info : runVehicles() Begin")
        if (cachedVehicleData.isEmpty()) {
            console.info("Info : runVehicles() cachedVehicleData.size = "+cachedVehicleData.size)
            loadVehicle()
        }
    }

    override suspend fun runProperties(theme: Int)  {
        console.info("Info : loadProperty() Begin")
        if (cachedVehicleData.isEmpty()) {
            console.info("Info : loadProperty() cachedVehicleData.size = "+cachedVehicleData.size)
            loadProperty()
        }
    }

    override fun getVehiclesList( ) : List<Vehicle>  {
        return cachedVehicleData
    }

    override suspend fun runPremiumVehiclesListString( ) {
        try {
            val response = window.fetch(Asset.Thumbnail.File.PREMIUM_VEHICLE_DATA).await()
            val jsonString = response.toString().await()
            console.error("info : getPremiumVehiclesList() $jsonString")
            cachedPremiumVehicleData =  convertVehicleJsonStringToList(jsonString.toString()) as MutableList<Vehicle>
            console.error("info : getPremiumVehiclesList() cachedPremiumVehicleData.size $cachedPremiumVehicleData.size")
        } catch ( e: Exception ) {
            console.error("ERROR : getPremiumVehiclesList() $e")
        }
    }

    override suspend fun getVehiclesFlow( ) : Flow<List<Vehicle>> = flow {
        emit( cachedVehicleData )
    }

    override suspend fun loadProperty() {
        console.info("Info : loadProperty() Begin cachedPropertyData.size = "+cachedVehicleData.size)
        try {
            //val jsonString = Res.readBytes("files/data.json").decodeToString()

            val response = window.fetch(Asset.Thumbnail.File.VEHICLE_DATA)
            val jsonString = response
            //console.info("info : loadVehicle jsonString response = $jsonString")
            cachedPropertyData = convertPropertyJsonStringToList(jsonString.toString()) as MutableList<Property>
            console.info("info : loadProperty cachedVehicleList = ${cachedVehicleData.size}")
            // Process the JSON string
            cachedPremiumPropertyData = cachedPropertyData.filter{ (it.typeId == 2) }.toList()
                    as MutableList<Property>

            //val IN_MEMORY_THUMBNAIL_DETAILS = mutableListOf<VideoThumbnailDetails>()
            console.info("Info : loadProperty() Begin cachedPropertyData.size = "+cachedVehicleData.size)
        } catch (e: Exception) {
            console.info("Info : loadProperty() ERROR cachedPropertyData.size = "+cachedPropertyData.size)
            println("AXA - Load Property error")
        }
    }

    suspend fun loadVehicle() {
        console.info("Info : loadVehicle() Begin cachedVehicleData.size = "+cachedVehicleData.size)
        try {
            //val jsonString = Res.readBytes("files/data.json").decodeToString()

            val response = window.fetch(Asset.Thumbnail.File.VEHICLE_DATA).await()
            val jsonString = response.text().await()
            //console.info("info : loadVehicle jsonString response = $jsonString")
            cachedVehicleData = convertVehicleJsonStringToList(jsonString) as MutableList<Vehicle>
            console.info("info : loadVehicle cachedVehicleList = ${cachedVehicleData.size}")
            // Process the JSON string
            //val IN_MEMORY_THUMBNAIL_DETAILS = mutableListOf<VideoThumbnailDetails>()

        } catch (e: Exception) {
            console.info("Info : loadVehicle() ERROR cachedVehicleData.size = "+cachedVehicleData.size)
            println("AXA - Load Vehicle error")
        }
    }
}

private fun String.await() {
    TODO("Not yet implemented")
}
