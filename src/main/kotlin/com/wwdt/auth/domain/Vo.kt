package com.wwdt.auth.domain

data class ValidationUser(
    val email: String,
    val password: String,
)

data class RegistrationUser(
    val email: String,
    val password: String,
    val name: String,
)

data class PasswordChange(
    val email: String,
    val oldPassword: String,
    val newPassword: String,
)

data class NameChange(
    val email: String,
    val name: String,
)