package org.pumped.common.security.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import kotlinx.datetime.Clock
import kotlinx.datetime.toJavaInstant
import org.pumped.common.IRepository
import org.pumped.common.mapping.IModelMapper
import org.pumped.common.security.domain.mapper.TokenModelMapper
import org.pumped.common.security.domain.model.Token
import org.pumped.common.security.persistance.dto.TokenDTO
import org.pumped.common.security.persistance.repository.TokenRepository
import org.pumped.configuration.secrets
import org.pumped.io.env.EnvVariables
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.util.UUID
import kotlin.time.Duration.Companion.seconds

class SecurityServiceAdapter: ISecurityService, KoinComponent {

    private val tokenRepository: TokenRepository by inject()
    private val tokenModelMapper: TokenModelMapper by inject()

    override fun createJWTToken(application: Application, userID: UUID): Token {
        return with(application) {
            val jwtAudience = secrets[EnvVariables.BB_JWT_AUDIENCE]
            val jwtSecret = secrets[EnvVariables.BB_JWT_SECRET]
            val jwtDomain = secrets[EnvVariables.BB_JWT_DOMAIN]

            val expiresAt = Clock.System.now().plus(secrets[EnvVariables.BB_JWT_EXPIRY].toInt().seconds).toJavaInstant()
            val savedToken = tokenRepository.save(Token(UUID.randomUUID(), false))
            val tokenModel = tokenModelMapper.toDomain(savedToken)

            val jwt = JWT
                .create()
                .withIssuer(jwtDomain)
                .withAudience(jwtAudience)
                .withClaim("user_id", userID.toString())
                .withClaim("token_id", tokenModel.id.toString())
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(jwtSecret))

            tokenModel.copy(jwt = jwt)
        }
    }

    override fun blacklistToken(tokenID: UUID) {
        val token = Token(tokenID, true)
        tokenRepository.update(token)
    }
}

val securityModule = module {
    singleOf(::SecurityServiceAdapter) { bind<ISecurityService>() }
    singleOf(::TokenRepository) { bind<IRepository<Token, TokenDTO>>() }
    singleOf(::TokenModelMapper) { bind<IModelMapper<Token, TokenDTO>>() }
    singleOf(::JWTServiceAdapter) { bind<IJWTService>() }
}