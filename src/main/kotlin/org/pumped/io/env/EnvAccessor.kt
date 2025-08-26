package org.pumped.io.env

fun interface EnvAccessor {

    /**
     * Reads the given variable from the environment and tries to parse it to the given type
     * @see EnvVariable
     * @param variable the variable to read
     * @return the value read
     */
    operator fun get(variable: EnvVariable): String
}