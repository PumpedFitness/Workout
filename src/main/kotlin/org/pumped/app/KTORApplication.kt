package org.pumped.app

import io.ktor.server.application.Application
import org.pumped.configuration.*
import org.pumped.routes.configureRoutes

fun Application.module(name: String) {
    configureSecrets()

    configureDatabases()
    configureRabbitMQ(name)

    configureSerialization()
    configureAdministration()
    configureAkkurate()
    configureRouting()
    configureRoutes()

    configureOpenAPI()
    configureSwagger()
}
