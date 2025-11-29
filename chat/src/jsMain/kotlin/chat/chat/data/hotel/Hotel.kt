package chat.chat.data.hotel

import kotlinx.serialization.Serializable

@Serializable
data class Hotel (
    val id: Int,
    val address: String,
    val latitude: String,
    val longitude: String,
    val lgaId:Int,
    val cityId:Int,
    val definiteAmenities: String,
    val premiumAmenities: String,
    val bar: Int,
    val pool:Int,
    val gym: Int,
    val casino: Int,
    val spa: Int,
    val petsAllowed: Int,
    val breakfast: Int,
    val restaurant: Int,
    val otherAmenities: String,
    val stateId: Int,
    val title: String,    //   Title
    val description: String,
    val hotelTypeId: Int,    // * Rental, Sale, Lease
    val hotelUseId: Int,     // * Residential, Commercial, Agriculture, Industry
    val hotelSubTypeId: Int, // * Apartment, Flat, House, Shop, Commercial, Farm
    val parking: Int,
    val typeId: Int,
    val enteredDate: String,
    val expiryDate: String,
    val userId: Int,
    val marketedByUserId: Int,
    val marketedByPhone: String,
    val marketedByEmail: String,
    val statusId: Int,
    val orderId: Int,
    val img1: String,
    val img2: String,
    val img3: String,
    val img4: String,
    val img5: String,
    val video1: String,
    val video2: String,
    val rowId: String,
    val marketedByImage: String,
    val thumbnail: String,
    val views: Long,
    val favorites: Long,
    val likes: Long,
    val marketedByName: String,
    val hashtag: String,
    val comments: Long
)