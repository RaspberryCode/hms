package pl.edu.pk.hms.notifications.sender

import pl.edu.pk.hms.notifications.CommunicationChannel
import pl.edu.pk.hms.notifications.Notification
import pl.edu.pk.hms.users.details.dao.UserProfile

interface NotificationSender {

    fun communicationChannel(): CommunicationChannel
    fun send(notification: Notification, user: UserProfile)
}
