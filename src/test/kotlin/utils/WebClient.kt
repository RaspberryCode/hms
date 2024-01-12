package utils

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClient

class WebClient(
    private val restClient: RestClient,
) {

    private val authenticationApi = AuthenticationApi(restClient)
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