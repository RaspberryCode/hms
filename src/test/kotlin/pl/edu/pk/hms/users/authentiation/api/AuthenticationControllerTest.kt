package pl.edu.pk.hms.users.authentiation.api


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import utils.AuthenticationApi
import utils.IntegrationTest

class AuthenticationControllerTest @Autowired constructor(restTemplate: TestRestTemplate) : IntegrationTest() {
    val authenticationApi: AuthenticationApi = AuthenticationApi(restTemplate)

    @Test
    fun `should register user`() {
        // when
        val response = authenticationApi.register("test@test.com", "test1234", "+48321654987")
        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
    }

    @Test
    fun `should login admin`() {
        // when
        val response = authenticationApi.login("admin@admin.com", "admin")
        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body?.token).isNotNull()
    }

    @Test
    fun `should login user`() {
        // when
        val response = authenticationApi.login("user@user.com", "user")
        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body?.token).isNotNull()
    }
}