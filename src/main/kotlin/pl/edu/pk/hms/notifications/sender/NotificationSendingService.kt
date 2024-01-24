package pl.edu.pk.hms.notifications.sender

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import pl.edu.pk.hms.happenings.District
import pl.edu.pk.hms.notifications.CommunicationChannel.*
import pl.edu.pk.hms.notifications.Notification
import pl.edu.pk.hms.notifications.NotificationImportance.ESSENTIAL
import pl.edu.pk.hms.users.details.UserProfileService
import pl.edu.pk.hms.users.details.dao.UserPreferences
import pl.edu.pk.hms.users.details.dao.UserProfile

@Service
class NotificationSendingService(
    private val notificationSenders: Set<NotificationSender>,
    private val userProfileService: UserProfileService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun sendNotificationToUser(notification: Notification, userId: Long) {
        userProfileService.getUserProfile(userId)
            .ifPresentOrElse(
                { sendNotificationToUser(notification, it) },
                { logger.warn("User $userId not found, while sending notification") }
            )
    }

    fun sendNotificationToDistrict(notification: Notification, district: District) {
        userProfileService.getUsersByDistrict(district)
            .forEach { sendNotificationToUser(notification, it) }
    }

    private fun sendNotificationToUser(notification: Notification, user: UserProfile) {
        getApplicableSenders(notification, user.preferences).forEach {
            it.send(notification, user)
        }
    }

    private fun getApplicableSenders(
        notification: Notification,
        userPreferences: UserPreferences
    ): Set<NotificationSender> {
        return notificationSenders.filter { notificationSender ->
            when (notificationSender.communicationChannel()) {
                EMAIL -> (notification.type == ESSENTIAL) or userPreferences.emailNotifications
                SMS   -> userPreferences.smsNotifications
                PUSH  -> userPreferences.pushNotifications
            }
        }.toSet()
    }
}