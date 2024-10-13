package com.wwdt.auth.api.request

import jakarta.validation.constraints.Email

data class RegisterUserDto(
    @field:Email val email: String,
    val password: String,
    val name: String,
)
