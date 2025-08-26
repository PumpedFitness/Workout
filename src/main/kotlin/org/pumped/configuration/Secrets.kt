package org.pumped.configuration

import io.ktor.server.application.Application
import io.ktor.util.AttributeKey
import org.pumped.io.secret.SecretAdapter

fun Application.configureSecrets(testing: Boolean = false, secretsName: String) {
    val adapterType = if (testing)
        "env"
    else
        System.getenv("${secretsName}_SECRET_ADAPTER") ?: error("${secretsName}_SECRET_ADAPTER is not defined")

    val secretAdapter = SecretAdapter.getSecretAdapter(adapterType, secretsName)
    secretAdapter.validate()

    attributes.put(BBSecretAccessor, secretAdapter)
}

val BBSecretAccessor = AttributeKey<SecretAdapter>("KTOR_SECRETS")

private fun Application.secrets(): SecretAdapter = attributes[BBSecretAccessor]
val Application.secrets: SecretAdapter get() = secrets()