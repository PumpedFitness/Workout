package org.pumped.io.secret.adapters

import org.pumped.io.secret.SecretAdapter

class SystemEnvAdapter(prefix: String): SecretAdapter(prefix) {

    override fun get(key: String): String? {
        return System.getProperty("${prefix}_$key")
    }

    override fun getAllKeys(): Set<String> {
        return System.getenv().map { it.key }.toSet()
    }

    override fun getAll(): Map<String, String> {
        return System.getenv()
    }
}