package org.pumped.configuration

import io.ktor.server.application.*
import org.pumped.common.security.service.securityModule
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(securityModule)
    }
}