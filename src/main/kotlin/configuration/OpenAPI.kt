package ord.pumped.configuration

import io.github.smiley4.ktoropenapi.OpenApi
import io.ktor.server.application.Application
import io.ktor.server.application.install

fun Application.configureOpenAPI() {
    install(OpenApi)
}