package ord.pumped.io.env

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.Application
import io.ktor.server.application.log
import io.ktor.util.AttributeKey

val mode: ProgramMode = ProgramMode.valueOf(System.getenv(EnvVariables.BB_MODE.name) ?: EnvVariables.BB_MODE.default!!)

fun Application.configureEnv() {
    class Env: EnvAccessor {
        private var dotenv: Dotenv? = null

        init {
            if (mode == ProgramMode.DEV) {
                dotenv = dotenv()
            }

            val entries = EnvVariables.entries
            log.debug("${entries.size} env entries were supplied")

            val setEntries = getAllKeys()

            if (setEntries.size > entries.size) {
                log.warn("${setEntries.size} were entries found, but only ${entries.size} is required")

                for (setEntry in setEntries) {
                    if (entries.firstOrNull { it.name == setEntry } == null) {
                        log.warn("$setEntry is set but not required by local definitions!")
                    }
                }
            }

            for (value in entries) {
                val envValue =
                    value.default
                        ?: (getOrNull(value.name) ?: error("Key ${value.name} not found in environment"))

                when (value.type) {
                    EnvType.INT -> {
                        if (envValue.toIntOrNull() == null) error("Can't cast ${value.name} to INT")
                    }
                    EnvType.LONG -> {
                        if (envValue.toLongOrNull() == null) error("Can't cast ${value.name} to LONG")
                    }
                    EnvType.NUMBER -> {
                        if (envValue.toDoubleOrNull() == null) error("Can't cast ${value.name} to NUMBER")
                    }
                    EnvType.BOOLEAN -> {
                        if (envValue.toBooleanStrictOrNull() == null) error("Can't cast ${value.name} to BOOLEAN")
                    }
                    else -> {}
                }

                if (value.requiresNonEmpty && envValue.isEmpty()) {
                    error("Value ${value.name} requires non empty value, but a empty value has been supplied!")
                }
            }
        }

        private fun getAllKeys(): List<String> =
            if (mode == ProgramMode.DEV) {
                dotenv!!.entries().map { it.key }
            } else {
                System.getenv().map { it.key }
            }

        override fun getOrNull(s: String): String? {
            return if (mode == ProgramMode.DEV) {
                if (dotenv!!.entries().none { it.key == s }) {
                    return null
                }

                dotenv!!.get(s)
            } else {
                if (System.getenv(s) == null) {
                    return null
                }
                System.getenv(s)
            }
        }


        override fun requiredDev(variable: EnvVariables): Boolean {
            if (get(EnvVariables.BB_MODE) != ProgramMode.DEV.name) return false
            if (variable.type != EnvType.BOOLEAN) return false

            return getOrNull(variable.name)?.toBooleanStrict() ?: run {
                if (variable.default != null) {
                    return variable.default.toBooleanStrict()
                }
                error("${variable.name} no found in .env")
            }
        }

        override operator fun get(variable: EnvVariables): String {
            return getOrNull(variable.name) ?: run {
                if (variable.default != null) {
                    return variable.default
                }
                error("${variable.name} no found in .env")
            }
        }
    }
    
    val env = Env()
    attributes.put(BBEnvAccessor, env)
}

val BBEnvAccessor = AttributeKey<EnvAccessor>("BB_ENV")

private fun Application.env(): EnvAccessor = attributes[BBEnvAccessor]
val Application.env: EnvAccessor get() = env()

enum class ProgramMode {
    PROD,
    DEV,
    STAGE,
}
