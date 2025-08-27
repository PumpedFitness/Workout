package org.pumped.events.application

import net.ormr.eventbus.Event
import org.pumped.app.MiniService


data class ApplicationBootedEvent(val application: MiniService): Event