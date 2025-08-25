package org.pumped

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.pumped.configuration.*
import org.pumped.configuration.middlewares.configureMiddlewares
import org.pumped.routes.configureRoutes

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module(testing: Boolean = false) {
    configureSecrets(testing)

    configureDatabases()
    configureRabbitMQ()

    configureKoin()

    configureSerialization()
    configureAdministration()
    configureAkkurate()
    configureRouting()
    configureRoutes()
    configureMiddlewares()

    configureOpenAPI()
    configureSwagger()
}
