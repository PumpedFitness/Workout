package ord.pumped.configuration.database

import ord.pumped.configuration.database.adapters.H2Adapter
import ord.pumped.configuration.database.adapters.MariaDBAdapter

object DatabaseAdapterFetcher {

    fun fetchAdapterForDB(type: String): DBAdapter {
        return when (type) {
            "mariadb" -> MariaDBAdapter()
            "h2" -> H2Adapter()
            else -> error("Unsupported database type: $type")
        }
    }
}