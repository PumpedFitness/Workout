package org.pumped.app

import io.ktor.server.application.Application
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.util.logging.Logger
import net.ormr.eventbus.Event
import net.ormr.eventbus.EventBus
import org.pumped.events.application.ApplicationBootedEvent
import org.slf4j.LoggerFactory

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
abstract class MiniService(val config: MiniServiceConfig) {

    lateinit var logger: Logger
    val eventBus = EventBus.newDefault()

    init {
        configure()
        boot()
    }

    private fun boot() {
        embeddedServer(CIO, port = config.applicationPort, host = "0.0.0.0") {
            logger = LoggerFactory.getLogger(config.name)

            module(config)

            onBoot()
            eventBus.fire(ApplicationBootedEvent(this@MiniService))
        }.start(wait = true).addShutdownHook(::onShutdown)
    }

    /**
     * Configure this application, before it boots
     */
    abstract fun configure()

    /**
     * This function is called during server initialization, after the standard
     * module configuration.
     * 
     * Override this function to perform additional initialization steps, register
     * routes, or configure service-specific components.
     */
    open fun Application.onBoot() {}

    abstract fun onShutdown()
}