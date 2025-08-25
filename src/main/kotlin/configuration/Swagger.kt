package org.pumped.configuration

import io.github.smiley4.ktorswaggerui.swaggerUI
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureSwagger() {
    routing {
        swaggerUI("/swagger")
    }
}