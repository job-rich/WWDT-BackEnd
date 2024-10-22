package com.wwdt.auth.application

import com.wwdt.auth.api.request.RegisterUserDto
import com.wwdt.auth.domain.AccountService
import com.wwdt.auth.domain.EditService
import com.wwdt.shared_kernel.model.CommonResponse
import org.springframework.stereotype.Service

@Service
class UserApplication(
    private val authModule: AccountService,
    private val editModule: EditService
) {
    fun processRegisterUser(registerUser: RegisterUserDto): CommonResponse {
        val isRegister = authModule.registerUser(registerVo = registerUser.toRegistrationUser())
        return CommonResponse(
            message = "Register success",
            result = isRegister
        )
    }
}