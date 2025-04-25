package database

import io.ktor.server.application.Application
import models.TestModel
import models.TestModels
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.test.Test
import kotlin.test.assertSame

class TestDatabaseMigration {

    @Test
    fun testMigration() {
        transaction {
            val model = TestModel.new {
                example_string = "test"
            }

            assertSame(model.id.value, 1)
        }
    }

}