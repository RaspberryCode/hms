package pl.edu.pk.hms.notifications.sender.push

import org.springframework.stereotype.Component
import pl.edu.pk.hms.happenings.District
import pl.edu.pk.hms.notifications.Notification
import pl.edu.pk.hms.notifications.sender.NotificationSender

@Component
class PushSender : NotificationSender {
    override fun sendToUser(notification: Notification, userId: Long) {
        TODO()
    }

    override fun sendToUsers(notification: Notification, usersId: Set<Long>) {
        TODO()
    }

    override fun broadcastToDistrict(notification: Notification, district: District) {
        TODO()
    }
}