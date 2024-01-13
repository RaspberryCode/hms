package pl.edu.pk.hms.users.details

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import pl.edu.pk.hms.users.User
import pl.edu.pk.hms.users.details.api.dto.UserDetailsPatchRequest
import pl.edu.pk.hms.users.details.api.dto.UserDetailsResponse
import testutils.IntegrationTest
import testutils.WebClient


class UserDetailsIntegrationTest(
    @Autowired val webClient: WebClient
) : IntegrationTest() {

    lateinit var userWithPassword: Pair<User, String>
    val user by lazy { userWithPassword.first }
    val password by lazy { userWithPassword.second }

    @BeforeEach
    fun setUp() {
        webClient.removeAllUsers()
        userWithPassword = webClient.createUser()
    }

    @Test
    fun `should fetch user details`() {
        webClient.asAuthenticatedUser(user.email, password) { token ->
            // when
            val response = webClient.get(token, "/api/user", UserDetailsResponse::class.java)
            // then
            assertTrue(response.statusCode.is2xxSuccessful)
            assertEquals(user.email, response.body!!.email)
            assertEquals(user.phoneNumber, response.body!!.phoneNumber)
        }
    }

    @Test
    fun `should update user details`() {
        webClient.asAuthenticatedUser(user.email, password) { token ->
            // when
            val request = UserDetailsPatchRequest(email = "new_address@mail.com", phoneNumber = null)
            val response = webClient.patch(token, "/api/user", request, UserDetailsResponse::class.java)

            // then
            assertTrue(response.statusCode.is2xxSuccessful)
            assertEquals(request.email, response.body!!.email)
            assertEquals(user.phoneNumber, response.body!!.phoneNumber)
        }
    }
}