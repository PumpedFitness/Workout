package org.pumped.configuration

import io.ktor.server.application.*
import org.pumped.configuration.database.DatabaseAdapterFetcher
import org.pumped.io.env.EnvVariables
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    log.info("Initializing database...")

    val adapter = DatabaseAdapterFetcher.fetchAdapterForDB(secrets[EnvVariables.DB_TYPE])

    val datasource = adapter.asDataSource(this)

    Database.connect(datasource)
    configureMigrations(datasource)
}
