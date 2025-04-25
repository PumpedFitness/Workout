package ord.pumped.configuration.database.adapters

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import ord.pumped.configuration.database.DBAdapter
import javax.sql.DataSource

class H2Adapter: DBAdapter {

    override fun asDataSource(application: Application): DataSource {
        val config = HikariConfig().apply {
            driverClassName = "org.h2.Driver"
            jdbcUrl  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MariaDB;"
        }

        return HikariDataSource(config)
    }
}