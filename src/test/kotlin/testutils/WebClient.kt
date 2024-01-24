package testutils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import pl.edu.pk.hms.users.Role
import pl.edu.pk.hms.users.User
import pl.edu.pk.hms.users.authentiation.dao.UserRepository
import pl.edu.pk.hms.users.details.dao.UserPreferences
import pl.edu.pk.hms.users.details.dao.UserProfile
import pl.edu.pk.hms.users.details.dao.UserProfilesRepository

@Lazy
@Component
class WebClient(
    @Autowired val restClient: RestClient,
    @Autowired val passwordEncoder: PasswordEncoder,
    @Autowired val userRepository: UserRepository,
    @Autowired val userProfilesRepository: UserProfilesRepository
) {

    private val authenticationApi = AuthenticationApi(restClient)

    fun removeAllUsers() {
        userRepository.deleteAll()
    }

    fun createUser(role: Role = Role.USER): Pair<User, String> {
        val password = "password"
        val userName = role.name.lowercase()
        var user = User(
            encryptedPassword = passwordEncoder.encode(password),
            role = role,
            profile = null
        )
        val userProfile = UserProfile(
            user = user,
            phoneNumber = "123456789",
            email = "$userName@$userName.com",
            preferences = UserPreferences(),
            districts = emptySet()
        )
        user.profile = userProfile
        user = userRepository.save(user)
        return Pair(user, password)
    }

    fun <T> get(
        token: String,
        endpoint: String,
        responseType: Class<T>
    ): ResponseEntity<T> {
        return restClient.get()
            .uri(endpoint)
            .headers { it.set(HttpHeaders.AUTHORIZATION, "Bearer $token") }
            .retrieve()
            .toEntity(responseType)
    }

    fun <T> get(
        token: String,
        endpoint: String,
        responseType: ParameterizedTypeReference<T>
    ): ResponseEntity<T> {
        return restClient.get()
            .uri(endpoint)
            .headers { it.set(HttpHeaders.AUTHORIZATION, "Bearer $token") }
            .retrieve()
            .toEntity(responseType)
    }

    fun <T> post(
        token: String,
        endpoint: String,
        body: Any,
        responseType: Class<T>
    ): ResponseEntity<T> {
        return restClient.post()
            .uri(endpoint)
            .body(body)
            .headers { it.set(HttpHeaders.AUTHORIZATION, "Bearer $token") }
            .retrieve()
            .toEntity(responseType)
    }

    fun <T> put(
        token: String,
        endpoint: String,
        body: Any,
        responseType: Class<T>
    ): ResponseEntity<T> {
        return restClient.put()
            .uri(endpoint)
            .body(body)
            .headers { it.set(HttpHeaders.AUTHORIZATION, "Bearer $token") }
            .retrieve()
            .toEntity(responseType)
    }

    fun <T> patch(
        token: String,
        endpoint: String,
        body: Any,
        responseType: Class<T>
    ): ResponseEntity<T> {
        return restClient.patch()
            .uri(endpoint)
            .body(body)
            .headers { it.set(HttpHeaders.AUTHORIZATION, "Bearer $token") }
            .retrieve()
            .toEntity(responseType)
    }

    fun asAuthenticatedUser(
        login: String,
        password: String,
        action: (String) -> Any
    ): Any {
        val token: String = login(login, password)
        return action(token)
    }

    private fun login(login: String, password: String): String {
        val loginResponse = authenticationApi.login(login, password)
        if (loginResponse.statusCode.isError || loginResponse.body == null)
            throw Exception("Login failed")
        return loginResponse.body!!.token
    }
}