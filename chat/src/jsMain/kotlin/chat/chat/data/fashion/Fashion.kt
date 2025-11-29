package chat.chat.data.fashion


import kotlinx.serialization.Serializable


@Serializable
data class Fashion (
    val id: Int,
    val address: String,
    val latitude: String,
    val longitude: String,
    val lgaId:Int,
    val cityId:Int,
    val stateId: Int,
    val title: String,    //   Title
    val description: String,
    val fashionTypeId: Int,
    val fashionUseId: Int,
    val fashionSubtypeId: Int,
    val category: String,
    val subCategory: String,
    val size: String,
    val color: String,
    val gender: String,
    val brand: String,
    val priceMain: Int,
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
    val video1: String,
    val video2: String,
    val marketedByImage: String,
    val rowId: String,
    val thumbnail: String,
    val views: Long,
    val favorites: Long,
    val likes: Long,
    val marketedByName: String,
    val hashtag: String,
    val comments: Long
)
