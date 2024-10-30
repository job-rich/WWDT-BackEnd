package com.wwdt.shared_kernel.core

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class AllowedConfig {
    private final val allowedUrls = arrayOf(
        "/swagger-ui/**", "/v3/api-docs/**",
        "/swagger-resources/**", "/v1/api/account/register",
        "/v1/api/account/login", "/v1/api/account/check-email"
    )
    val urls
        get() = allowedUrls

    private final val allowedSecurityHeaderName = "Authorization"
    val securityHeaderName
        get() = allowedSecurityHeaderName

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            allowedOrigins = listOf("*") // TODO: Change to the actual domain
            allowedMethods = listOf("GET", "POST", "PATCH", "DELETE", "HEAD")
            allowedHeaders = listOf(
                "Authorization",
                "Content-Type",
                "X-Requested-With",
                "Accept",
                "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"
            )
            exposedHeaders = listOf(
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials",
                "Authorization",
                "Content-Disposition"
            )
            maxAge = 3600
        }
        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }
        return source
    }
}