package org.pumped.configuration.database.adapters

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import org.pumped.configuration.database.DBAdapter
import org.pumped.configuration.secrets
import org.pumped.io.env.EnvVariables
import javax.sql.DataSource

class MariaDBAdapter: DBAdapter {

    override fun asDataSource(application: Application): DataSource {
        val secrets = application.secrets

        val config = HikariConfig().apply {
            username = secrets[EnvVariables.BB_DB_USER]
            password = secrets[EnvVariables.BB_DB_PASSWORD]
            jdbcUrl = "jdbc:mariadb://${secrets[EnvVariables.BB_DB_HOST]}:${secrets[EnvVariables.BB_DB_PORT]}/${secrets[EnvVariables.BB_DB_DATABASE]}"
        }

        return HikariDataSource(config)
    }
}