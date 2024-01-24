package pl.edu.pk.hms.notifications.sender.sms

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import pl.edu.pk.hms.notifications.CommunicationChannel
import pl.edu.pk.hms.notifications.Notification
import pl.edu.pk.hms.notifications.sender.NotificationSender
import pl.edu.pk.hms.users.details.dao.UserProfile

@Component
class SmsSender(
    private val smsGateway: SmsGateway,
    @Value("\${sms.gateway.credentials.username}")
    private val login: String,
    @Value("\${sms.gateway.credentials.password}")
    private val password: String
) : NotificationSender {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun communicationChannel(): CommunicationChannel =
        CommunicationChannel.SMS

    override fun send(notification: Notification, user: UserProfile) {
        user.phoneNumber?.let { phoneNumber ->
            smsGateway.send(
                login = login,
                password = password,
                number = phoneNumber,
                text = joinNotificationToSms(notification)
            )
        } ?: run {
            logger.error("User ${user.id} has no phone number, while sending SMS notification")
        }
    }

    private fun joinNotificationToSms(notification: Notification) =
        "${notification.title}\n${notification.message}"
}