package pl.edu.pk.hms.users.authentiation

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import utils.AuthenticationApi
import utils.IntegrationTest
import utils.WebClient

class SecurityIntegrationTest @Autowired constructor(val restTemplate: TestRestTemplate) : IntegrationTest() {
    val authenticationApi: AuthenticationApi = AuthenticationApi(restTemplate)
    val webClient: WebClient = WebClient(restTemplate)
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
        val token = authenticationApi.login(email, password).body!!.token
        val response = webClient.performGetOnEndpoint(token, endpoint)

        // then
        assert(response.statusCode.is2xxSuccessful)
    }
}