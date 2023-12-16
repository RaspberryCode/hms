package utils

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import pl.edu.pk.hms.users.authentiation.api.AuthenticationRequest
import pl.edu.pk.hms.users.authentiation.api.AuthenticationResponse
import pl.edu.pk.hms.users.authentiation.api.RegisterRequest

class AuthenticationApi(private val restTemplate: TestRestTemplate) {

    fun login(email: String, password: String): ResponseEntity<AuthenticationResponse> =
        restTemplate.postForEntity(
            "/api/login",
            AuthenticationRequest(email, password),
            AuthenticationResponse::class.java
        )

    fun register(email: String, password: String, phoneNumber: String): ResponseEntity<AuthenticationResponse> =
        restTemplate.postForEntity(
            "/api/register",
            RegisterRequest(email, password, phoneNumber),
            AuthenticationResponse::class.java
        )
}
