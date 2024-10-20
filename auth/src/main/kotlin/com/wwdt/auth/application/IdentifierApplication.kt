package com.wwdt.auth.application

import com.wwdt.auth.api.request.RegisterUserDto
import com.wwdt.auth.domain.AuthService
import com.wwdt.auth.domain.EditService
import org.springframework.stereotype.Service

@Service
class IdentifierApplication(
    private val authModule: AuthService,
    private val editModule: EditService
) {
    fun processRegisterUser(registerUser: RegisterUserDto) = authModule.registerUser(
        registerVo = registerUser.toRegistrationUser()
    )
}