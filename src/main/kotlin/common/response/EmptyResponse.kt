package org.pumped.common.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import org.pumped.io.defaultJson
import org.pumped.io.websocket.routing.messaging.IWebsocketResponse

@Serializable
data class EmptyResponse(override val message: String = "",
                         override val shouldNotify: Boolean = false,
                         override val status: Boolean = true
): IWebsocketResponse {
    override fun asJson(): JsonElement {
        return defaultJson.encodeToJsonElement(serializer(), this)
    }
}
