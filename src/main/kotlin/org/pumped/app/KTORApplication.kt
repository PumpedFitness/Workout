package org.pumped.app

import io.ktor.server.application.Application
import org.pumped.configuration.*
import org.pumped.routes.configureRoutes

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

    configureOpenAPI()
    configureSwagger()
}
