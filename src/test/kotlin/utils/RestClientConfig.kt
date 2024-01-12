package utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.web.client.RestClient

@Lazy
@TestConfiguration
class RestClientConfig {

    @Bean
    fun restClient(@Value("\${local.server.port}") port: Int): RestClient {
        return RestClient.builder()
            .baseUrl("http://localhost:$port")
            .build()
    }
}