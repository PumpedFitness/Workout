package org.pumped.io.env

/**
 * Register all env variables inside of this class to make sure, they are loaded correctly
 */ 
enum class EnvVariables(
    override val type: EnvType,
    override val default: String? = null,
    override val requiresNonEmpty: Boolean = true,
) : EnvVariable {
    MODE(EnvType.STRING, "DEV"),

    DB_TYPE(EnvType.STRING),
    DB_HOST(EnvType.STRING),
    DB_PORT(EnvType.INT),
    DB_DATABASE(EnvType.STRING),
    DB_USER(EnvType.STRING),
    DB_PASSWORD(EnvType.STRING),

    REDIS_HOST(EnvType.STRING),
    REDIS_PORT(EnvType.STRING),

    RABBITMQ_USER(EnvType.STRING),
    RABBITMQ_PASSWORD(EnvType.STRING),
    RABBITMQ_PORT(EnvType.NUMBER),
    RABBITMQ_HOST(EnvType.STRING),
}

enum class EnvType(val cast: (value: String) -> Boolean) {
    STRING({ true }),
    LONG({ it.toLongOrNull() != null }),
    INT({ it.toIntOrNull() != null }),
    NUMBER({ it.toDoubleOrNull() != null }),
    BOOLEAN({ it.toBooleanStrictOrNull() != null }),
}