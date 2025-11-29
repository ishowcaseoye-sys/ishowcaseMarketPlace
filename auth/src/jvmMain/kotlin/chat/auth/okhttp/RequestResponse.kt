package chat.auth.okhttp

import kotlinx.serialization.Serializable

@Serializable
data class MyRequest(val message: String)

@Serializable
data class MyResponse(val echoedMessage: String)