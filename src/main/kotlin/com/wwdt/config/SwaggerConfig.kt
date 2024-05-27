package com.wwdt.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
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
class SwaggerConfig {
    private final val paths = "/v1/api/**"
    @Bean
    fun openApi(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("WWDT")
        .pathsToMatch(paths)
        .build()
}