package ord.pumped.configuration.middlewares

import io.ktor.server.application.Application
import io.ktor.server.application.install
import statix.org.*

fun Application.configureMiddlewares() {
    install(Middlewares) {
        middleware = globalMiddleware
    }
}