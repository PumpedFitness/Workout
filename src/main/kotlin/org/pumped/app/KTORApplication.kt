package org.pumped.app

import io.ktor.server.application.Application
import org.pumped.configuration.*
import org.pumped.routes.configureRoutes

fun Application.module(config: MiniServiceConfig) {
    configureSecrets(false,config.secretsPrefix)

    configureDatabases()
    configureRabbitMQ(config.name)

    configureSerialization()
    configureAdministration()
    configureAkkurate()
    configureRouting()
    configureRoutes()

    configureOpenAPI()
    configureSwagger()
}
