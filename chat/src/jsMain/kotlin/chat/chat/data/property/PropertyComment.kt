package chat.chat.data.property

import kotlinx.serialization.Serializable

@Serializable
data class PropertyComment (
    val id: Int,
    val propertyId: Int,
    val userId: Int,
    val comment: String,
    val enteredDate: String
)