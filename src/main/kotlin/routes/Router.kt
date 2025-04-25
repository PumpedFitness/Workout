package ord.pumped.routes

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import ord.pumped.routes.api.apiRouting

fun Application.configureRoutes() {
    routing {
        statusRouting()
        apiRouting()
    }
}