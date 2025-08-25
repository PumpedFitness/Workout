package org.pumped.common.request.actions

import kotlinx.serialization.Serializable
import org.pumped.io.websocket.routing.messaging.IWebsocketAction

@Serializable
data class EmptyAction(val empty: String = ""): IWebsocketAction
