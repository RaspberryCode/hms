package pl.edu.pk.hms.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class OpenApiConfiguration : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        val swaggerHomePage = "swagger-ui/index.html"
        registry.addRedirectViewController("/", swaggerHomePage)
        registry.addRedirectViewController("/docs", swaggerHomePage)
        registry.addRedirectViewController("/swagger-ui.html", swaggerHomePage)
    }

    private val securitySchemeName = "Bearer Authentication"

    @Bean
    fun openApi(@Value("\${spring.application.name}") applicationName: String?): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title(applicationName ?: "Spring Boot Application")
                    .version("1.0.0")
                    .contact(
                        Contact()
                            .name("Software Kitchen team")
                            .email("tmalinowski@softwarekitchen.com")
                    )
            )
            .addSecurityItem(SecurityRequirement().addList(securitySchemeName))
            .components(Components().addSecuritySchemes(securitySchemeName, createAPIKeyScheme()))
    }

    private fun createAPIKeyScheme(): SecurityScheme {
        return SecurityScheme()
            .name(securitySchemeName)
            .description("JWT Authorization Token")
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer")
            .`in`(SecurityScheme.In.HEADER)
    }
}


