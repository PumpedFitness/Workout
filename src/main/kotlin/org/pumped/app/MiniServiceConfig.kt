package org.pumped.app

data class MiniServiceConfig(
    val name: String,
    val applicationPort: Int,
    val secretsPrefix: String
)