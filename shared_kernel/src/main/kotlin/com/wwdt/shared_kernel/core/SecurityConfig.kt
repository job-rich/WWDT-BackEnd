package com.wwdt.shared_kernel.core

import com.wwdt.shared_kernel.infra.JWTAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val allowedConfig: AllowedConfig,
    private val jwtAuthorizationFilter: JWTAuthorizationFilter
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(15)

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain = http
        .csrf { it.disable() }
        .authorizeHttpRequests {
            it.requestMatchers(
                *allowedConfig.urls.map { allowedUrl -> AntPathRequestMatcher(allowedUrl) }.toTypedArray()
            ).permitAll().anyRequest().authenticated()
        }
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .cors { allowedConfig.corsConfigurationSource() }
        .httpBasic { it.disable() }
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter::class.java) // jwtAuthorizationFilter is added before UsernamePasswordAuthenticationFilter
        .build()
}
