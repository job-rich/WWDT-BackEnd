package com.wwdt.shared_kernel.infra

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoderWrapper(
    private val passwordEncoder: PasswordEncoder
) {
    fun encode(password: String): String = passwordEncoder.encode(password)
    
    fun matches(rawPassword: String, encodedPassword: String) = passwordEncoder.matches(rawPassword, encodedPassword)
}