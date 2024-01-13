package testutils

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
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

        init {
            postgres.start()
        }
    }
}