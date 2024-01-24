package pl.edu.pk.hms.notifications.handlers.events

import org.springframework.context.ApplicationEvent
import pl.edu.pk.hms.notifications.Notification
import pl.edu.pk.hms.notifications.NotificationImportance

open class UserRegistered(
    source: Any,
    val userId: Long,
    private val importance: NotificationImportance = NotificationImportance.ESSENTIAL
) : ApplicationEvent(source) {
    fun getNotification(): Notification {
        return Notification(
            title = "Welcome to HMS",
            message =
            """Welcome to HMS - Your Happenings Management System! 
Please complete your registration via the link below to start managing and enjoying events seamlessly. 
Questions or need help? Contact us at support@hms.com. Explore, Engage, Enjoy - The HMS Team.""",
            type = importance
        )
    }
}
