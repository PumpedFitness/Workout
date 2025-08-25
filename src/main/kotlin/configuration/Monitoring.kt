package org.pumped.configuration

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.pingDatabase(): Boolean {
    log.info("Pinging database...")
    return try {
        transaction {
        }
        true
    } catch (_: Throwable) {
        false
    }
}