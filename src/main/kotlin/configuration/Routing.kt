package ord.pumped.configuration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectBuilder

fun Application.configureRouting() {
    install(RequestValidation)
    install(Resources)

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        exception<RequestValidationException> { call, cause ->
            @Serializable
            data class ValidationError(val message: String, val errors: List<String>)

            call.respond(status = HttpStatusCode.UnprocessableEntity, ValidationError("Validation failed", cause.reasons))
        }
    }
}
