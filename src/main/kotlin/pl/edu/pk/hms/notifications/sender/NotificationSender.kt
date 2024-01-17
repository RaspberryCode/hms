package pl.edu.pk.hms.notifications.sender

import pl.edu.pk.hms.happenings.District
import pl.edu.pk.hms.notifications.Notification

interface NotificationSender {

    fun sendToUser(notification: Notification, userId: Long)
    fun sendToUsers(notification: Notification, usersId: Set<Long>)
    fun broadcastToDistrict(notification: Notification, district: District)
}
