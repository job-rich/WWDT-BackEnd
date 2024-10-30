package com.wwdt.shared_kernel.core

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
    info = Info(
        title = "WWDT API",
        version = "v1",
        description = "WWDT Service API-Docs"
    )
)
@Configuration
class SwaggerConfig(
    private val allowedConfig: AllowedConfig,
) {
    private final val paths = "/v1/api/**"

    @Bean
    fun openApi(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("WWDT")
        .pathsToMatch(paths)
        .addOpenApiCustomizer { openApi ->
            openApi.components.securitySchemes(
                mapOf(allowedConfig.securityHeaderName to securityScheme())
            )
            openApi.addSecurityItem(SecurityRequirement().addList(allowedConfig.securityHeaderName))
        }
        .build()

    private fun securityScheme(): SecurityScheme {
        return SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .name(allowedConfig.securityHeaderName)
            .scheme("bearer")
            .bearerFormat("JWT")
    }
}