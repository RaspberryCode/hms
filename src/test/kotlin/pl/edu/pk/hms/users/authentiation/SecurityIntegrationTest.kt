package pl.edu.pk.hms.users.authentiation

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import pl.edu.pk.hms.users.authentiation.api.AuthenticationRequest
import pl.edu.pk.hms.users.authentiation.api.AuthenticationResponse

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityIntegrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    companion object {
        @Container
        @ServiceConnection
        val postgres = PostgreSQLContainer<Nothing>("postgres:16.0-alpine")
    }

    @Test
    fun `should not allow access to admin endpoint without token`() {
        // when
        val response = restTemplate.getForEntity("/admin-endpoint", String::class.java)
        // then
        assert(response.statusCode.is4xxClientError)
    }

    @Test
    fun `should not allow access to user endpoint without token`() {
        // when
        val response = restTemplate.getForEntity("/user-endpoint", String::class.java)
        // then
        assert(response.statusCode.is4xxClientError)
    }

    @ParameterizedTest(name = "should allow access to {3} endpoint with {4} token")
    @CsvSource(
        "admin@admin.com, admin, /admin-endpoint, Admin, Admin",
        "admin@admin.com, admin, /user-endpoint, Admin, User",
        "user@user.com, user, /user-endpoint, User, User"
    )
    fun `should allow access to admin endpoint with admin token`(
        email: String,
        password: String,
        endpoint: String
    ) {
        // when
        val token = login(email, password)
        val response = performGetOnEndpoint(token, endpoint)

        // then
        assert(response.statusCode.is2xxSuccessful)
    }

    private fun performGetOnEndpoint(
        token: String?,
        endpoint: String
    ): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer $token")
        val entity = HttpEntity<String>(headers)
        return restTemplate.exchange(endpoint, HttpMethod.GET, entity, String::class.java)
    }

    private fun login(email: String, password: String): String =
        restTemplate.postForEntity(
            "/api/login",
            AuthenticationRequest(email, password),
            AuthenticationResponse::class.java
        ).body!!.token
}