package chat.auth

import chat.auth.model.Hotel
import chat.auth.model.JobSeeker
import chat.auth.model.Property
import chat.auth.model.Vehicle
import chat.auth.okhttp.OkHttp
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.FileInputStream
import java.security.KeyStore
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

interface AuthDataRepository {
    suspend fun getProperties(state: String, country: String,
                              latitude: String, longitude: String) : List<Property>
    suspend fun getVehicles(state: String, country: String,
                            latitude: String, longitude: String) : List<Vehicle>
    suspend fun getJobSeekers(state: String, country: String,
                              latitude: String, longitude: String) : List<JobSeeker>
    suspend fun getHotels(state: String, country: String,
                          latitude: String, longitude: String) : List<Hotel>
}

class AuthDataRepositoryImpl () : AuthDataRepository {

    val okHttpClient = HttpClient(OkHttp) {
        engine {
            config {
                sslSocketFactory(SslSettings.getSslContext()!!.socketFactory, SslSettings.getTrustManager())
            }
        }
    }

    object SslSettings {
        fun getKeyStore(): KeyStore {
            val keyStoreFile = FileInputStream("keystore.jks")
            val keyStorePassword = "foobar".toCharArray()
            val keyStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(keyStoreFile, keyStorePassword)
            return keyStore
        }

        fun getTrustManagerFactory(): TrustManagerFactory? {
            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(getKeyStore())
            return trustManagerFactory
        }

        fun getSslContext(): SSLContext? {
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, getTrustManagerFactory()?.trustManagers, null)
            return sslContext
        }

        fun getTrustManager(): X509TrustManager {
            return getTrustManagerFactory()?.trustManagers?.first { it is X509TrustManager } as X509TrustManager
        }
    }

    override suspend fun getProperties( state:String, country: String,
                                        latitude: String, longitude: String ) : List<Property> {
         try {
            println("AAB -  DataRepository.getProperties()")

            val response = okHttpClient.get(BASE_URL+"properties")
            println("AAB - getProperties() RESPONSE " + response.bodyAsText())

            val properties: List<Property> = convertPropertyJsonStringToList(response.bodyAsText())
            return properties
        } catch ( e:Exception ) {
            println("AAB - Exception getProperties() $e")
            return listOf()
        }
        return listOf()
    }

    override suspend fun getVehicles(state:String, country: String,
                                     latitude: String, longitude: String) : List<Vehicle> {
        /*try {
            println("AAB -  TasksRepositoryImpl.getVehicles()")
            //val client = HttpClient()
            val response = client.get(BASE_URL+"vehicles")
            val responseString = response.bodyAsText().trim()
            println("AAB - getVehicles() RESPONSE $responseString")

            val properties: List<Vehicle> = convertVehicleJsonStringToList(responseString)
            return properties
        } catch ( e:Exception ) {
            println("AAB - Exception getVehicles() $e")
            return listOf()
        }*/
        return listOf()
    }

    override suspend fun getHotels(state: String, country: String,
                                   latitude: String, longitude: String) : List<Hotel> {
        /*try {
            println("AAB -  TasksRepositoryImpl.getHotels()")
            //val client = HttpClient()
            val response = client.get(BASE_URL+"hotels")
            println("AAB - getHotels() RESPONSE " + response.bodyAsText())

            val hotels: List<Hotel> = convertHotelJsonStringToList(response.bodyAsText())
            return hotels
        } catch ( e:Exception ) {
            println("AAB - Exception getHotels() $e")
            return listOf()
        }*/
        return listOf()
    }

    override suspend fun getJobSeekers(state: String, country: String,
                                       latitude: String, longitude: String) : List<JobSeeker> {
        /*try {
            println("AAB -  TasksRepositoryImpl.getJobSeekers()")
            //val client = HttpClient()
            val response = client.get(BASE_URL+"jobSeekers")
            println("AAB - getJobSeekers() RESPONSE " + response.bodyAsText())

            val jobSeekers: List<JobSeeker> = convertJobSeekerJsonStringToList(response.bodyAsText())
            return jobSeekers
        } catch ( e:Exception ) {
            println("AAB - Exception getJobSeekers() $e")
            return listOf()
        }*/
        return listOf()
    }
}


fun convertPropertyJsonStringToList(jsonString: String): List<Property> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(Property.serializer()), jsonString)
}

fun convertHotelJsonStringToList(jsonString: String): List<Hotel> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(ListSerializer(Hotel.serializer()), jsonString)
}