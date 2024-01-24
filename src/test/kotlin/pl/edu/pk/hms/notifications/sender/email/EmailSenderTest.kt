package pl.edu.pk.hms.notifications.sender.email

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import pl.edu.pk.hms.notifications.Notification
import pl.edu.pk.hms.notifications.NotificationImportance
import pl.edu.pk.hms.users.details.dao.UserProfile

@ExtendWith(MockitoExtension::class)
class EmailSenderTest {

    @Mock
    private lateinit var emailGateway: EmailGateway
    @InjectMocks
    private lateinit var emailSender: EmailSender

    @Test
    fun testSendToUser() {
        // given
        val userId = 1L
        val notification = Notification("Test Title", "Test Message", NotificationImportance.ESSENTIAL)
        val userProfile = UserProfile().apply {
            id = userId
            email = "test@example.com"
        }

        // when
        emailSender.send(notification, userProfile)

        //then
        verify(emailGateway).send(userProfile.email, notification.title, notification.message)
    }
}