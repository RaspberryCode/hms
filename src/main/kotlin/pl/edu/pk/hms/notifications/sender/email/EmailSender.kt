package pl.edu.pk.hms.notifications.sender.email

import org.springframework.stereotype.Component
import pl.edu.pk.hms.happenings.District
import pl.edu.pk.hms.notifications.Notification
import pl.edu.pk.hms.notifications.sender.NotificationSender

@Component
class EmailSender : NotificationSender {
    override fun sendToUser(notification: Notification, userId: Long) {
        TODO("Not yet implemented")
    }

    override fun sendToUsers(notification: Notification, usersId: Set<Long>) {
        TODO("Not yet implemented")
    }

    override fun broadcastToDistrict(notification: Notification, district: District) {
        TODO("Not yet implemented")
    }
}