package ord.pumped.configuration.database

import io.ktor.server.application.Application
import javax.sql.DataSource

interface DBAdapter {

    fun asDataSource(application: Application): DataSource

}