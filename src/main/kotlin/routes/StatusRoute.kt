package ord.pumped.routes

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.statusRouting() {
    get("/status") {
        call.respondText("OK")
    }
}