package org.pumped.mapping

fun interface IModelMapper<Model, DTO> {

    fun toDomain(dto: DTO): Model

}