package com.wwdt.auth.domain

interface AuthService {
    fun authenticate(authenticationVo: ValidationUser): Boolean

    fun registerUser(registerVo: RegistrationUser): Boolean

    fun changePassword(changeVo: PasswordChange): Boolean

    fun resetPassword(email: String): Boolean

    fun changeName(changeVo: NameChange): Boolean
}