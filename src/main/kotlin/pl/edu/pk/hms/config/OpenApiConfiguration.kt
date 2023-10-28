package pl.edu.pk.hms.config

import io.swagger.v3.oas.models.OpenAPI
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@ConditionalOnClass({ WebMvcConfigurer, OpenApiCustomizer })
class OpenApiConfiguration : WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        val swaggerHomePage = "swagger-ui/index.html"
        registry.addRedirectViewController("/", swaggerHomePage)
        registry.addRedirectViewController("/docs", swaggerHomePage)
        registry.addRedirectViewController("/swagger-ui.html", swaggerHomePage)
    }

    @Bean
    fun openApi(@Value("\${spring.application.name}") applicationName: String?): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title(applicationName)
                    .contact(
                        Contact()
                            .name("Team mEXto")
                            .email("tmalinowski247@gmail.com")
                    )
            )
    }
}