package ord.pumped.io.env

interface EnvAccessor {

    /**
     * Reads the given value from the local environment
     * @param s the name the env variable
     * @return the value or null if not found
     */
    fun getOrNull(s: String): String?

    /**
     * Returns the given variable if DEV mode is enabled through the env variables
     * The given variable must be type [EnvType.BOOLEAN] to work
     *
     * If the variable is not a boolean, or dev mode is off, this function will always return false
     *
     * @see ProgramMode
     */
    fun requiredDev(variable: EnvVariables): Boolean

    /**
     * Reads the given variable from the environment and tries to parse it to the given type
     * @see EnvVariables
     * @param variable the variable to read
     * @return the value read
     */
    operator fun get(variable: EnvVariables): String
}