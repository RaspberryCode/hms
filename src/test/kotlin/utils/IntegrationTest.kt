package utils

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container


@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class IntegrationTest {
    companion object {
        //        @JvmStatic
        @Container
        @ServiceConnection
        protected val postgres: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:latest")

        init {
            postgres.start()
        }
    }
}