package common

import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.testApplication
import ord.pumped.module

fun test(block: suspend ApplicationTestBuilder.() -> Unit) {
    testApplication {
        application {
            module(true)
        }

        block()
    }
}