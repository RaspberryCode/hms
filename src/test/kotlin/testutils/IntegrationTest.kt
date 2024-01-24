package testutils

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestConfig::class, WebClient::class)
@ActiveProfiles("integration")
abstract class IntegrationTest {

    companion object {
        @JvmStatic
        @Container
        @ServiceConnection
        protected val postgres: PostgreSQLContainer<Nothing> = PostgreSQLContainer("postgres:latest")

        @JvmStatic
        @Container
        protected val smtpServer: GenericContainer<Nothing> = GenericContainer<Nothing>("mailhog/mailhog:v1.0.0")
            .withExposedPorts(1025, 8025)

        @DynamicPropertySource
        @JvmStatic
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            val smtpPort = smtpServer.getMappedPort(1025)
            registry.add("spring.mail.host") { smtpServer.host }
            registry.add("spring.mail.port"){smtpPort.toString()}
            registry.add("spring.mail.username") { "" }
            registry.add("spring.mail.password") { "" }
            registry.add("spring.mail.properties.mail.smtp.auth") { "false" }
            registry.add("spring.mail.properties.mail.smtp.starttls.enable") { "false" }
        }

        init {
            postgres.start()
            smtpServer.start()
        }
    }
}