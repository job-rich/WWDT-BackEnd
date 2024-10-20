package com.wwdt.auth.api.request

import com.wwdt.auth.domain.RegistrationUser
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class RegisterUserDto(
    @field:Email @field:NotEmpty val email: String,
    @field:NotEmpty val password: String,
    val name: String,
) {
    fun toRegistrationUser() = RegistrationUser(
        email = email,
        password = password,
        name = name,
    )
}
