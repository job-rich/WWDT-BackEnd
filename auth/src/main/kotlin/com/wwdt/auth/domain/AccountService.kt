package com.wwdt.auth.domain

interface AccountService {
    fun authenticate(authenticationVo: ValidationUser): User

    fun registerUser(registerVo: RegistrationUser): Boolean

    fun isExistEmail(email: String): Boolean
}