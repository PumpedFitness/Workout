package org.pumped.io.secret

import org.pumped.io.env.EnvAccessor
import org.pumped.io.env.EnvVariable
import org.pumped.io.env.EnvVariables
import org.pumped.io.secret.adapters.DotEnvAdapter
import org.pumped.io.secret.adapters.SystemEnvAdapter
import org.pumped.io.secret.adapters.VaultAdapter

abstract class SecretAdapter(val prefix: String = ""): EnvAccessor {

    companion object {
        fun getSecretAdapter(type: String, prefix: String = ""): SecretAdapter {
            return when (type) {
                "env" -> DotEnvAdapter(prefix)
                "system" -> SystemEnvAdapter(prefix)
                "vault" -> VaultAdapter(prefix)
                else -> error("Unsupported type: $type")
            }
        }
    }

    abstract fun get(key: String): String?

    override operator fun get(variable: EnvVariable) = get(variable.name)!!

    abstract fun getAllKeys(): Set<String>

    abstract fun getAll(): Map<String, String>

    /**
     * Validates that all required environment variables are present and of the correct type.
     * By default, validates against the EnvVariables enum.
     * 
     * @param envVariables the collection of environment variables to validate against
     * @return true if all variables are valid
     * @throws IllegalStateException if any variable is invalid
     */
    fun validate(envVariables: Collection<EnvVariable> = EnvVariables.entries): Boolean {
        val keys = getAllKeys()

        for (envVariable in envVariables) {

            val transformedKey = "${prefix}_${envVariable.name}"
            val key = envVariable.name

            if (!keys.contains(transformedKey)) {
                error("Missing required environment variable $transformedKey")
            }

            val value = get(key)

            if (value == null) {
                error("Missing required environment variable $transformedKey")
            }

            if (!envVariable.type.cast(value)) {
                error("Failed to cast $transformedKey to ${envVariable.type.cast(value)}")
            }

            if (envVariable.requiresNonEmpty && value.isEmpty()) {
                error("Variable $transformedKey requires non empty value, but a empty value has been supplied!")
            }
        }

        return true
    }
}