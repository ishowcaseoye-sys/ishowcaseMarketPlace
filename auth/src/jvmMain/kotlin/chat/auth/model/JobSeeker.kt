package chat.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class JobSeeker (
    val id: Int,
    val firstname: String,
    val surname: String,
    val address: String,
    val age: Int,
    val gender: String,
    val lgaId : Int,
    val cityId:Int,
    val stateId: Int,
    val jobType: Int,
    val jobTypeId: Int,
    val jobSubTypeId:Int,
    val jobCategory: Int,
    val yearsOfExperience: Int,
    val telephone: String,
    val email: String,
    val availId: Int,
    val statusId: Int,
    val img1: String,
    val img2: String,
    val img3: String,
    val img4: String,
    val img5: String,
    val video1: String,
    val video2: String,
    val userId: Int,
    val maritalStatus :Int,
    val qualifications: Int,
    val whenAvailableToStart: String,
    val orderId: Int,
    val typeId: Int,
    val title: String,
    val description: String,
    val expSalaryMain: Int,
    val expSalaryPrecision: Int,
    val expSalaryCurrency: String,
    val rowId: String
)
