package pl.edu.pk.hms.notifications.sender.sms

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import pl.edu.pk.hms.notifications.Notification
import pl.edu.pk.hms.notifications.NotificationImportance
import pl.edu.pk.hms.users.details.dao.UserProfile

@ExtendWith(MockitoExtension::class)
class SmsSenderTest {

    @Mock
    private lateinit var smsGateway: SmsGateway
    private lateinit var smsSender: SmsSender

    @BeforeEach
    fun setUp() {
        smsSender = SmsSender(smsGateway, "testLogin", "testPassword")
    }

    @Test
    fun testSendToUser() {
        val userId = 1L
        val notification = Notification("Test Title", "Test Message", NotificationImportance.ESSENTIAL)
        val userProfile = UserProfile().apply {
            id = userId
            email = "test@example.com"
            phoneNumber = "123456789"
        }

        smsSender.send(notification, userProfile)

        verify(smsGateway).send(
            "testLogin", "testPassword", "${notification.title}\n${notification.message}", userProfile.phoneNumber!!
        )
    }
}