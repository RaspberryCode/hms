package pl.edu.pk.hms.users.authentiation.api


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpStatus
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest {
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    companion object {
        @Container
        @ServiceConnection
        val postgres = PostgreSQLContainer<Nothing>("postgres:16.0-alpine")
    }

    @Test
    fun `should register user`() {
        // given
        val request = RegisterRequest(
            email = "test@test.com", password = "test1234", phoneNumber = "+48321654987"
        )

        // when
        val response = restTemplate.postForEntity("/api/register", request, AuthenticationResponse::class.java)
        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
    }

    @Test
    fun `should login admin`() {
        // given
        val request = AuthenticationRequest(email = "admin@admin.com", password = "admin")

        // when
        val response = restTemplate.postForEntity("/api/login", request, AuthenticationResponse::class.java)
        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body?.token).isNotNull()
    }

    @Test
    fun `should login user`() {
        // given
        val request = AuthenticationRequest(email = "user@user.com", password = "user")

        // when
        val response = restTemplate.postForEntity("/api/login", request, AuthenticationResponse::class.java)
        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body?.token).isNotNull()
    }
}