package chat.chat.utils

import chat.chat.data.electronic.Electronic
import chat.chat.data.fashion.Fashion
import chat.chat.data.furniture.Furniture
import chat.chat.data.hotel.Hotel
import chat.chat.data.jobseeker.JobSeeker
import chat.chat.data.property.Property
import chat.chat.data.property.PropertyComment
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import chat.chat.data.vehicle.Vehicle
import chat.chat.data.vehicle.VehicleComment
import kotlinx.serialization.builtins.serializer


val displayLogs = true

fun log( logType:Int, message: String ) {
    if (displayLogs) {
        if (logType==0) {
            console.info(message)
        } else if (logType==1) {
            console.warn(message)
        } else if (logType==2) {
            console.error(message)
        }
    }
}

fun convertVehicleJsonStringToList(jsonString: String): List<Vehicle> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(Vehicle.serializer()), jsonString)
}

fun convertPropertyJsonStringToList(jsonString: String): List<Property> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(Property.serializer()), jsonString)
}

fun convertFashionJsonStringToList(jsonString: String): List<Fashion> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(Fashion.serializer()), jsonString)
}

fun convertElectronicJsonStringToList(jsonString: String): List<Electronic> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(Electronic.serializer()), jsonString)
}

fun convertFurnitureJsonStringToList(jsonString: String): List<Furniture> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(Furniture.serializer()), jsonString)
}

fun convertHotelJsonStringToList(jsonString: String): List<Hotel> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(Hotel.serializer()), jsonString)
}


fun convertJobSeekerJsonStringToList(jsonString: String): List<JobSeeker> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(JobSeeker.serializer()), jsonString)
}

fun convertVehicleCommentsJsonStringToList(jsonString: String): List<VehicleComment> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(VehicleComment.serializer()), jsonString)
}

fun convertPropertyCommentsJsonStringToList(jsonString: String): List<PropertyComment> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(PropertyComment.serializer()), jsonString)
}


fun addComasToLong (value:Long ): String {
    val stringValue = value.toString()
    val length = stringValue.toString().length - 1
    val valueReversed  = value.toString()
    var counter = 1
    var finalResponse:String = ""
    for (n in length  downTo 0) {
        finalResponse = finalResponse + valueReversed[n]
        if ( counter == 3) {
            if (n!= 0) {
                finalResponse = "$finalResponse,"
            }
            counter = 1
        } else {
            counter++
        }
    }
    finalResponse = finalResponse.reversed()
    return finalResponse
}

fun addComasToIntegers (value:Int ): String {
    val stringValue = value.toString()
    val length = stringValue.toString().length - 1
    val valueReversed  = value.toString()
    var counter = 1
    var finalResponse:String = ""
    for (n in length  downTo 0) {
        finalResponse = finalResponse + valueReversed[n]
        if ( counter == 3) {
            if (n!= 0) {
                finalResponse = "$finalResponse,"
            }
            counter = 1
        } else {
            counter++
        }
    }
    finalResponse = finalResponse.reversed()
    return finalResponse
}

fun numberCalculation(number: Long): String {
    val div = 1000
    //console.info("numberCalculation")
    val res: Int = (number % div).toInt()
    console.info("numberCalculation divide = "+res )
    if (res == 1) {
        val ans = number / div
        val result = number.toString()
        //console.info( "result1 - numberCalculation = $result")
        return result
    } else if ( res == 2 ) {
        val ans = number / 1_000_000
        val result = ans.toString()+"m"
        return result
    } else if (res == 3 ) {
        val ans = number / 1_000_000_000
        val result = ans.toString()+"b"
        return result
    } else if (res == 4 ) {
        val ans = number / 1_000_000_000_000
        val result = ans.toString()+"t"
        return result
    } else {
        return addComasToLong( number )
    }

}

fun numberCalculation(number: Int): String {
    val div = 1000
    //console.info("numberCalculation")
    val res = number % div

    if (res == 1) {
        val ans = number / div
        val result = number.toString()
        //console.info( "result1 - numberCalculation = $result")
        return result
    } else if ( res == 2 ) {
        val ans = number / 1_000_000
        val result = ans.toString()+"m"
        return result
    } else if (res == 3 ) {
        val ans = number / 1_000_000_000
        val result = ans.toString()+"b"
        return result
    } else {
        return addComasToIntegers(number )
    }
}

fun dateFormat (inputDate: String ): String {
    //val date = inputDate.substring(0,7)
    val finalDate = inputDate.substring(6,8) +"/"+inputDate.substring(4,6)+"/"+
            inputDate.substring(0,4)
    return finalDate
}