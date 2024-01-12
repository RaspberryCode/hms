package pl.edu.pk.hms.users.authentiation

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.web.client.RestClient
import utils.IntegrationTest
import utils.SecurityIntegrationTestConfiguration
import utils.WebClient

@Import(SecurityIntegrationTestConfiguration::class)
class SecurityIntegrationTest(@Autowired val restClient: RestClient) : IntegrationTest() {

    val webClient = WebClient(restClient)

    @Test
    fun `should not allow access to admin endpoint without token`() {
        // when
        val response = restClient.get()
            .uri("/admin-endpoint")
            .exchange { _, response -> response }
        // then
        assertTrue(response.statusCode.is4xxClientError)
    }

    @Test
    fun `should not allow access to user endpoint without token`() {
        // when
        val response = restClient.get()
            .uri("/user-endpoint")
            .exchange { _, response -> response }
        // then
        assertTrue(response.statusCode.is4xxClientError)
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
        endpoint: String,
        endpointForUser: String,
        tokenForUser: String
    ) {
        webClient.asAuthenticatedUser(email, password) { token ->
            // when
            val response = webClient.get(token, endpoint, String::class.java)

            // then
            assertNotNull(response)
        }
    }
}