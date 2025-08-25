package org.pumped.app

import io.ktor.server.application.Application
import io.ktor.server.cio.*
import io.ktor.server.engine.*

/**
 * MiniService is an abstract base class for creating Ktor-based services.
 * 
 * It automatically initializes and starts a Ktor server with the standard application
 * module configuration, and then calls the onBoot function to allow for additional
 * service-specific initialization.
 * 
 * Usage:
 * ```
 * class MyService : MiniService() {
 *     override fun Application.onBoot() {
 *         // Perform service-specific initialization here
 *     }
 * }
 * 
 * fun main() {
 *     MyService() // This will start the server
 * }
 * ```
 * 
 * The onBoot function is called during server initialization, after the standard
 * module configuration. You can use it to perform additional initialization steps,
 * register routes, or configure service-specific components.
 */
abstract class MiniService {

    init {
        embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
            module()
            onBoot()
        }.start(wait = true).addShutdownHook(::onShutdown)
    }

    /**
     * This function is called during server initialization, after the standard
     * module configuration.
     * 
     * Override this function to perform additional initialization steps, register
     * routes, or configure service-specific components.
     */
    abstract fun Application.onBoot()

    abstract fun onShutdown()

}