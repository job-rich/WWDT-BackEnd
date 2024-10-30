package com.wwdt.shared_kernel.infra

import com.wwdt.shared_kernel.core.AllowedConfig
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JWTAuthorizationFilter(
    private val allowedConfig: AllowedConfig,
    private val tokenProvider: TokenProvider
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = request.getHeader("Authorization")?.replace("bearer", "", ignoreCase = true)?.trim() ?: ""

        if (request.requestURI in allowedConfig.urls) {
            filterChain.doFilter(request, response)
            return
        }
        if (token.isNotBlank()){
            check(tokenProvider.validateToken(token)) { "Invalid Token" }
            SecurityContextHolder.getContext().authentication = tokenProvider.authenticateToken(token)
            filterChain.doFilter(request, response)
            return
        }

        filterChain.doFilter(request, response)

    }
}