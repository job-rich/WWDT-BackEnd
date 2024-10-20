package com.wwdt.auth.domain

interface AuthService {
    fun authenticate(authenticationVo: ValidationUser): Boolean

    fun registerUser(registerVo: RegistrationUser): Boolean
}