package com.wwdt.shared_kernel.infra

import com.wwdt.shared_kernel.utils.EnvExtension
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import java.util.*


@Component
class TokenProvider {
    private val secret: String = EnvExtension.getEnv("jwt-secret-key")
        ?: throw IllegalStateException("Missing jwt-secret-key")
    private val accessExpirationHours: Long = EnvExtension.getEnv("jwt-access-expiration-hours")?.toLong()
        ?: throw IllegalStateException("Missing jwt-access-expiration-hours")
    private val issuer: String = EnvExtension.getEnv("jwt-issuer")
        ?: throw IllegalStateException("Missing jwt-issuer")

    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(userId: String): String {
        val claim = Jwts.claims().apply { put("type", "access") }
        val token = Jwts.builder()
            .setClaims(claim)
            .setSubject(userId)
            .setIssuer(issuer)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + accessExpirationHours * 60 * 60 * 1000))
            .signWith(key)
            .compact()
        return token
    }
    fun validateToken(token: String): Boolean = try {
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
        true
    } catch (e: Exception) { false }

    fun authenticateToken(token: String): UsernamePasswordAuthenticationToken {
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
        return UsernamePasswordAuthenticationToken(claims.body.subject, null, null)
    }
}
