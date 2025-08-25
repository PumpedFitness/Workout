package org.pumped.common.security.domain.mapper

import org.pumped.common.mapping.IModelMapper
import org.pumped.common.security.domain.model.Token
import org.pumped.common.security.persistance.dto.TokenDTO
import org.jetbrains.exposed.sql.transactions.transaction

class TokenModelMapper: IModelMapper<Token, TokenDTO> {
    override fun toDomain(dto: TokenDTO): Token {
        return transaction {
            Token(
                dto.id.value,
                dto.isBlacklisted
            )
        }
    }
}