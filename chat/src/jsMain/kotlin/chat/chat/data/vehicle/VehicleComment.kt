package chat.chat.data.vehicle

import kotlinx.serialization.Serializable

@Serializable
data class VehicleComment (
    val id: Int,
    val vehicleId: Int,
    val userId: Int,
    val comment: String,
    val enteredDate: String
)

