package testutils

import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClient
import pl.edu.pk.hms.users.authentiation.api.dto.AuthenticationRequest
import pl.edu.pk.hms.users.authentiation.api.dto.AuthenticationResponse
import pl.edu.pk.hms.users.authentiation.api.dto.RegisterRequest

class AuthenticationApi(private val restClient: RestClient) {

    fun login(login: String, password: String): ResponseEntity<AuthenticationResponse> =
        restClient.post()
            .uri("/api/login")
            .body(AuthenticationRequest(login, password))
            .retrieve()
            .toEntity(AuthenticationResponse::class.java)

    fun register(email: String, password: String, phoneNumber: String): ResponseEntity<AuthenticationResponse> =
        restClient.post()
            .uri("/api/register")
            .body(RegisterRequest(email, password, phoneNumber, null))
            .retrieve()
            .toEntity(AuthenticationResponse::class.java)
}
