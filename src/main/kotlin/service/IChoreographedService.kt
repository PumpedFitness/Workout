package org.pumped.service

fun interface IChoreographedService {
    suspend fun sendEvent(event: IEvent)
}