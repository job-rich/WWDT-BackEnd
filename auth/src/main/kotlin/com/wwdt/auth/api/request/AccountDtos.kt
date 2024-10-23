package com.wwdt.auth.api.request

import com.wwdt.auth.domain.RegistrationUser
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
