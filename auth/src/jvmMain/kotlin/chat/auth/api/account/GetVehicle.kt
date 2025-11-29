package chat.auth.api.account

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.HttpMethod
import java.io.File
import java.io.IOException

@Api
suspend fun getVehicle(ctx: ApiContext) {
    if (ctx.req.method != HttpMethod.GET) return
    val response = readJsonFromFile()
}

private fun readJsonFromFile( ): String? {
    return try {
        File("/file/vehicle.json").readText(Charsets.UTF_8)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

/*
private val people = listOf(
    Person(name = "Stefan", age = 25),
    Person(name = "John", age = 24),
    Person(name = "Mary", age = 23),
    Person(name = "Ana", age = 22),
)

@Api
suspend fun getPeople(context: ApiContext) {
    try {
        val number = context.req.params.getValue("count").toInt()
        context.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.Success(data = people.take(number))
            )
        )
    } catch (e: Exception) {
        context.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.Error(errorMessage = e.message.toString())
            )
        )
    }
}
*/