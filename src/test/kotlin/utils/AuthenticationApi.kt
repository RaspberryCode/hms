package utils

import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClient
import pl.edu.pk.hms.users.authentiation.api.AuthenticationRequest
import pl.edu.pk.hms.users.authentiation.api.AuthenticationResponse
import pl.edu.pk.hms.users.authentiation.api.RegisterRequest

class AuthenticationApi(private val restClient: RestClient) {

    fun login(email: String, password: String): ResponseEntity<AuthenticationResponse> =
        restClient.post()
            .uri("/api/login")
            .body(AuthenticationRequest(email, password))
            .retrieve()
            .toEntity(AuthenticationResponse::class.java)

    fun register(email: String, password: String, phoneNumber: String): ResponseEntity<AuthenticationResponse> =
        restClient.post()
            .uri("/api/register")
            .body(RegisterRequest(email, password, phoneNumber))
            .retrieve()
            .toEntity(AuthenticationResponse::class.java)
}
