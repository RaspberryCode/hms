package pl.edu.pk.hms.users.details

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import pl.edu.pk.hms.users.User
import pl.edu.pk.hms.users.details.api.dto.UserPreferencesDto
import pl.edu.pk.hms.users.details.api.dto.UserDetailsPatchRequest
import pl.edu.pk.hms.users.details.api.dto.UserDetailsResponse
import testutils.IntegrationTest
import testutils.WebClient


class UserProfileIntegrationTest(
    @Autowired val webClient: WebClient
) : IntegrationTest() {

    private lateinit var userWithPassword: Pair<User, String>
    private val user: User by lazy { userWithPassword.first }
    private val password by lazy { userWithPassword.second }

    @BeforeEach
    fun setUp() {
        webClient.removeAllUsers()
        userWithPassword = webClient.createUser()
    }

    @Test
    fun `should fetch user details`() {
        webClient.asAuthenticatedUser(user.username, password) { token ->
            // when
            val response = webClient.get(token, "/api/user", UserDetailsResponse::class.java)
            // then
            assertTrue(response.statusCode.is2xxSuccessful)
            assertEquals(user.username, response.body!!.email)
            assertEquals(user.profile?.phoneNumber, response.body!!.phoneNumber)
            assertEquals(user.profile?.preferences?.emailNotifications, response.body!!.preferences.email)
            assertEquals(user.profile?.preferences?.smsNotifications, response.body!!.preferences.sms)
        }
    }

    @Test
    fun `should update user details`() {
        webClient.asAuthenticatedUser(user.username, password) { token ->
            // when
            val request = UserDetailsPatchRequest(
                email = "new_address@mail.com",
                phoneNumber = null,
                notificationsPreferences = UserPreferencesDto(email = true, sms = true, push = true)
            )
            val response = webClient.patch(token, "/api/user", request, UserDetailsResponse::class.java)

            // then
            assertTrue(response.statusCode.is2xxSuccessful)
            assertEquals(request.email, response.body!!.email)
            assertEquals(user.profile?.phoneNumber, response.body!!.phoneNumber)
            assertTrue(response.body!!.preferences.push)
            assertTrue(response.body!!.preferences.email)
            assertTrue(response.body!!.preferences.sms)
        }
    }
}