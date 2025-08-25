package org.pumped.common.mapping

fun interface IModelMapper<Model, DTO> {

    fun toDomain(dto: DTO): Model

}