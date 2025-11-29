package chat.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class Property (
    val id: Int,
    val address: String,
    val latitude: String,
    val longitude: String,
    val lgaId:Int,
    val cityId:Int,
    val stateId: Int,
    val title: String,    //   Title
    val description: String,
    val propTypeId: Int,    // * Rental, Sale, Lease
    val propUseId: Int,     // * Residential, Commercial, Agriculture, Industry
    val propSubtypeId: Int, // * Apartment, Flat, House, Shop, Commercial, Farm
    val bedroom: Int,
    val toilets: Int,
    val livingRoom: Int,
    val bathroom: Int,
    val parking: Int,
    val priceMain: Long,
    val pricePrecision: Int,
    val priceCurrency: String,
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
    val img6: String,
    val img7: String,
    val img8: String,
    val img9: String,
    val img10: String,
    val video1: String,
    val video2: String,
    val marketedByImage: String,
    val rowId: String
)
