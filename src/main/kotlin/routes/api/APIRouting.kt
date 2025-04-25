package ord.pumped.routes.api

import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import ord.pumped.routes.api.v1.apiV1Routing

fun Route.apiRouting() {
    route("/api") {
        route("/v1") {
            apiV1Routing()
        }
    }
}