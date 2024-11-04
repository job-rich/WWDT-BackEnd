package com.wwdt.auth.api.request

import com.wwdt.auth.domain.ChangePassword
import com.wwdt.auth.domain.RegistrationUser
import com.wwdt.auth.domain.ResetPassword
import com.wwdt.auth.domain.ValidationUser
import com.wwdt.shared_kernel.utils.ValidationUtils

data class EmailDto(
    val email: String,
) {
    init {
        require(ValidationUtils.isEmailValid(email)) { "Email must be a valid email address" }
    }
}

data class LoginUserDto(
    val email: String,
    val password: String,
) {
    init {
        require(ValidationUtils.isEmailValid(email)) { "Email must be a valid email address" }
        require(password.isNotBlank()) { "Password must not be blank" }
    }

    fun toValidationUser() = ValidationUser(
        email = email,
        password = password,
    )
}


data class RegisterUserDto(
    val email: String,
    val password: String,
    val name: String,
) {
    init {
        require(ValidationUtils.isEmailValid(email)) { "Email must be a valid email address" }
        require(password.isNotBlank()) { "Password must not be blank" }
        require(name.isNotBlank()) { "Name must not be blank" }
    }

    fun toRegistrationUser() = RegistrationUser(
        email = email,
        password = password,
        name = name,
    )
}

data class ChangePasswordDto(
    val email: String,
    val oldPassword: String,
    val newPassword: String,
) {
    init {
        require(ValidationUtils.isEmailValid(email)) { "Email must be a valid email address" }
        require(oldPassword.isNotBlank()) { "Old password must not be blank" }
        require(newPassword.isNotBlank()) { "New password must not be blank" }
    }

    fun toPasswordChange() = ChangePassword(
        email = email,
        oldPassword = oldPassword,
        newPassword = newPassword,
    )
}

data class ResetPasswordDto(
    val email: String,
    val name: String,
) {
    init {
        require(ValidationUtils.isEmailValid(email)) { "Email must be a valid email address" }
        require(name.isNotBlank()) { "Name must not be blank" }
    }
    fun toResetPassword() = ResetPassword(
        email = email,
        name = name,
    )
}