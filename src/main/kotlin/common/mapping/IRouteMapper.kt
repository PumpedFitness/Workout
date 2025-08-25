package org.pumped.common.mapping

interface IRouteMapper<Request, Response, Model> {

    fun toDomain(request: Request): Model
    fun toResponse(model: Model): Response

}