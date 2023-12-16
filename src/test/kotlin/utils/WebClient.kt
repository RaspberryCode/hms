package utils

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity

class WebClient(private val restTemplate: TestRestTemplate) {
    fun performGetOnEndpoint(
        token: String?,
        endpoint: String
    ): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer $token")
        val entity = HttpEntity<String>(headers)
        return restTemplate.exchange(endpoint, HttpMethod.GET, entity, String::class.java)
    }
}