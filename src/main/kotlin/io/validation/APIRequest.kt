package io.validation

import io.ktor.server.plugins.requestvalidation.*

/**
 * @author Devin Fritz
 */
interface APIRequest {

    /**
     * Validates this request
     * @return Validation Result
     */
    fun validate(): ValidationResult

}