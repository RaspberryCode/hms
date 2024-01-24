package pl.edu.pk.hms.notifications.sender.push

import org.springframework.stereotype.Component
import pl.edu.pk.hms.notifications.CommunicationChannel
import pl.edu.pk.hms.notifications.Notification
import pl.edu.pk.hms.notifications.sender.NotificationSender
import pl.edu.pk.hms.users.details.dao.UserProfile

@Component
class PushSender : NotificationSender {

    override fun communicationChannel(): CommunicationChannel =
        CommunicationChannel.PUSH

    override fun send(notification: Notification, user: UserProfile) {
        TODO()
    }
}