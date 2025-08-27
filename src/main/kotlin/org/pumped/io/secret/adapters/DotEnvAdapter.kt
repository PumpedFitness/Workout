package org.pumped.io.secret.adapters

import io.github.cdimascio.dotenv.dotenv
import org.pumped.io.secret.SecretAdapter

class DotEnvAdapter(prefix: String): SecretAdapter(prefix) {
    private val dotenv = dotenv()

    override fun get(key: String): String? {
        if (!dotenv.entries().any { it.key == "${prefix}_$key" }) return null
        return dotenv["${prefix}_$key"]
    }

    override fun getAllKeys(): Set<String> {
        return dotenv.entries().map { it.key }.toSet()
    }

    override fun getAll(): Map<String, String> {
        return dotenv.entries().associate { it.key to it.value }
    }
}