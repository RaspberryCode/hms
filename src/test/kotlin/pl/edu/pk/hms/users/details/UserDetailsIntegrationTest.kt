package pl.edu.pk.hms.users.details

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.client.RestClient
import pl.edu.pk.hms.users.Role
import pl.edu.pk.hms.users.User
import pl.edu.pk.hms.users.authentiation.dao.UserRepository
import pl.edu.pk.hms.users.details.api.dto.UserDetailsPatchRequest
import pl.edu.pk.hms.users.details.api.dto.UserDetailsResponse
import utils.IntegrationTest
import utils.WebClient


class UserDetailsIntegrationTest(
    @Autowired val userRepository: UserRepository,
    @Autowired passwordEncoder: PasswordEncoder,
    @Autowired restClient: RestClient
) : IntegrationTest() {

    val webClient: WebClient = WebClient(restClient)

    private final var password = "password"
    var user =
        User(
            email = "user@user.com",
            _password = passwordEncoder.encode(password),
            phoneNumber = "+48987654321",
            role = Role.USER
        )

    @BeforeEach
    fun setUp() {
        userRepository.deleteAll()
        user = userRepository.save(user)
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