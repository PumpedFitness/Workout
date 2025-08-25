package org.pumped.configuration

import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import org.pumped.common.APIException

fun Application.configureRouting() {
    install(RequestValidation) {
    }
    install(Resources)

    install(StatusPages) {
        exception<APIException> { call, cause ->
            cause.handle(call)
        }
    }
}
