package org.pumped.io.env

/**
 * Interface representing an environment variable.
 * This interface can be implemented by enums to allow third-party applications
 * to define their own environment variables.
 */
interface EnvVariable {
    /**
     * The name of the environment variable.
     * By default, for enums this will be the name of the enum constant.
     */
    val name: String
    
    /**
     * The type of the environment variable.
     */
    val type: EnvType
    
    /**
     * The default value of the environment variable, if any.
     */
    val default: String?
    
    /**
     * Whether the environment variable requires a non-empty value.
     */
    val requiresNonEmpty: Boolean
}