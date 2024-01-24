package pl.edu.pk.hms.notifications.handlers.events

import org.springframework.context.ApplicationEvent

open class HappeningUpdated(source: Any, val happeningId: Long) : ApplicationEvent(source)
