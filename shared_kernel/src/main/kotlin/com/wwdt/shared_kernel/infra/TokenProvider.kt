package com.wwdt.shared_kernel.infra

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.stereotype.Component


@PropertySource("classpath:jwt.yml")
@Component
class TokenProvider(
    @Value("\${secret}")
    private val secret: String,
    @Value("\${access-expiration-hours}")
    private val accessExpirationHours: Long,
    @Value("\${issuer}")
    private val issuer: String,
) {

    fun createToken(userId: String): String {
        val header = JwsHeader.with(MacAlgorithm.HS256)
            .type("JWT").build()
        TODO("토큰 구현 필요")
        return ""
    }
}