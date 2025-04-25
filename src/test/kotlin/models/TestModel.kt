package models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object TestModels: IntIdTable("test_entities") {
    val example_string = text("example_string")
}

class TestModel(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<TestModel>(TestModels)

    var example_string by TestModels.example_string
}