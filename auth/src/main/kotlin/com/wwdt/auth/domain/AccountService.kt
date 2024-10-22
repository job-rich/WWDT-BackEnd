package com.wwdt.auth.domain

interface AccountService {
    fun authenticate(authenticationVo: ValidationUser): Boolean

    fun registerUser(registerVo: RegistrationUser): Boolean
}