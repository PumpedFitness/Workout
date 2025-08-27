package org.pumped.configuration

import io.github.damir.denis.tudor.ktor.server.rabbitmq.RabbitMQ
import io.github.damir.denis.tudor.ktor.server.rabbitmq.dsl.*
import io.ktor.server.application.*
import org.pumped.io.env.EnvVariables

fun Application.configureRabbitMQ(name: String) {
    log.info("Connecting to RabbitMQ")
    install(RabbitMQ) {
        uri = "amqp://${secrets[EnvVariables.RABBITMQ_USER]}:${secrets[EnvVariables.RABBITMQ_PASSWORD]}@${secrets[EnvVariables.RABBITMQ_HOST]}:${secrets[EnvVariables.RABBITMQ_PORT]}"
        defaultConnectionName = "default-connection"
        dispatcherThreadPollSize = 4
        tlsEnabled = false
    }

    val queueName = "$name-queue"
    val exchangeKey = "$name-exchange"
    val routingKey = "$name-routing"

    rabbitmq {
        queueBind {
            queue = queueName
            exchange = exchangeKey
            this.routingKey = routingKey
            queueDeclare {
                queue = queueName
                durable = true
            }
            exchangeDeclare {
                exchange = exchangeKey
                type = "direct"
            }
        }
    }
}