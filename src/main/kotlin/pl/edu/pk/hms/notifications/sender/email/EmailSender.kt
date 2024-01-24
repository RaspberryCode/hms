package pl.edu.pk.hms.notifications.sender.email

import org.springframework.stereotype.Component
import pl.edu.pk.hms.notifications.CommunicationChannel
import pl.edu.pk.hms.notifications.Notification
import pl.edu.pk.hms.notifications.sender.NotificationSender
import pl.edu.pk.hms.users.details.dao.UserProfile

@Component
class EmailSender(
    val emailGateway: EmailGateway,
) : NotificationSender {
    override fun communicationChannel(): CommunicationChannel =
        CommunicationChannel.EMAIL

    override fun send(notification: Notification, user: UserProfile) {
        emailGateway.send(user.email, notification.title, notification.message)
    }
}