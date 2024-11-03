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
data class ChangePassword(
    val email: String,
    val oldPassword: String,
    val newPassword: String,
)

data class ResetPassword(
    val email: String,
    val name: String,
)

data class ChangeName(
    val email: String,
    val name: String,
)