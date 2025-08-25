package org.pumped.io.validation

import io.ktor.server.application.ApplicationCall
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.request.receiveNullable
import io.ktor.server.request.receiveText
import io.validation.APIRequest

suspend inline fun <reified T: APIRequest> ApplicationCall.receiveAPIRequest(): T {
    val request = receiveNullable<T>() ?: throw RequestValidationException(receiveText(), listOf("Failed to parse api request"))

    val validationResult = request.validate()

    if (validationResult is ValidationResult.Invalid) {
        throw RequestValidationException(receiveText(), validationResult.reasons)
    }

    return request
}