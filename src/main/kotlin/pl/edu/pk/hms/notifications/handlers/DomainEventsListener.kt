package pl.edu.pk.hms.notifications.handlers

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import pl.edu.pk.hms.notifications.handlers.events.HappeningCreated
import pl.edu.pk.hms.notifications.handlers.events.HappeningUpdated
import pl.edu.pk.hms.notifications.handlers.events.UserRegistered
import pl.edu.pk.hms.notifications.sender.NotificationSendingService

@Service
class DomainEventsListener(val notificationSendingService: NotificationSendingService) {
    @EventListener
    fun handleUserRegistered(event: UserRegistered) {
        notificationSendingService.sendNotificationToUser(event.getNotification(), event.userId)
    }

    @EventListener
    fun handleHappeningCreated(event: HappeningCreated) {
        println("Happening created: ${event.happeningId}")
    }

    @EventListener
    fun handleHappeningUpdated(event: HappeningUpdated) {
        println("Happening updated: ${event.happeningId}")
    }
}