package ord.pumped.configuration.database.adapters

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import ord.pumped.configuration.database.DBAdapter
import ord.pumped.io.env.EnvVariables
import ord.pumped.io.env.env
import javax.sql.DataSource

class MariaDBAdapter: DBAdapter {

    override fun asDataSource(application: Application): DataSource {
        val env = application.env

        val config = HikariConfig().apply {
            username = env[EnvVariables.BB_DB_USER]
            password = env[EnvVariables.BB_DB_PASSWORD]
            jdbcUrl = "jdbc:mariadb://${env[EnvVariables.BB_DB_HOST]}:${env[EnvVariables.BB_DB_PORT]}/${env[EnvVariables.BB_DB_DATABASE]}"
        }

        return HikariDataSource(config)
    }
}