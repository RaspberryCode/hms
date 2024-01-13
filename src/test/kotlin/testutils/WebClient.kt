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

@Lazy
@Component
class WebClient(
    @Autowired val restClient: RestClient,
    @Autowired val passwordEncoder: PasswordEncoder,
    @Autowired val userRepository: UserRepository
) {

    private val authenticationApi = AuthenticationApi(restClient)

    fun removeAllUsers() {
        userRepository.deleteAll()
    }

    fun createUser(role: Role = Role.USER): Pair<User, String> {
        val password = "password"
        val userName = role.name.lowercase()
        val user = userRepository.save(
            User(
                email = "$userName@$userName.com",
                _password = passwordEncoder.encode(password),
                phoneNumber = "+48987654321",
                role = role
            )
        )
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
        email: String,
        password: String,
        action: (String) -> Any
    ): Any {
        val token: String = login(email, password)
        return action(token)
    }

    private fun login(email: String, password: String): String {
        val loginResponse = authenticationApi.login(email, password)
        if(loginResponse.statusCode.isError || loginResponse.body == null)
            throw Exception("Login failed")
        return loginResponse.body!!.token
    }
}