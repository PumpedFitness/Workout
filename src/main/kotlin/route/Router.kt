package org.pumped.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.pumped.routes.statusRouting

fun Application.configureRoutes() {
    routing {
        statusRouting()
    }
}