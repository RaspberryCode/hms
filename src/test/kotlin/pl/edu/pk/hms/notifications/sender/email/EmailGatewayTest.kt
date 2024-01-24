package pl.edu.pk.hms.notifications.sender.email

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.check
import org.mockito.kotlin.verify
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

@ExtendWith(MockitoExtension::class)
class EmailGatewayTest {

    @Mock
    private lateinit var emailSender: JavaMailSender

    private lateinit var emailGateway: EmailGateway

    @BeforeEach
    fun setUp() {
        emailGateway = EmailGateway(emailSender)
    }

    @Test
    fun testSend() {
        val to = "test@example.com"
        val subject = "Test Subject"
        val text = "Test Text"

        emailGateway.send(to, subject, text)

        verify(emailSender).send(
            check<SimpleMailMessage> { message ->
                assert(message.subject == subject)
                assert(message.text == text)
            }
        )
    }
}