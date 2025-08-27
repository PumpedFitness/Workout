package org.pumped.app

import io.ktor.server.application.Application
import kotlinx.coroutines.runBlocking
import org.pumped.configuration.*
import org.pumped.routes.configureRoutes

fun Application.module(config: MiniServiceConfig) {
    runBlocking {
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
}
