package com.wwdt.auth.application

import com.wwdt.auth.api.request.EmailDto
import com.wwdt.auth.api.request.LoginUserDto
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
    fun processCheckEmail(validationEmail: EmailDto): CommonResponse {
        val isExist = authModule.isExistEmail(validationEmail.email)
        return CommonResponse(
            message = "Email is not exist Continue to register",
            result = isExist
        )
    }

    fun processRegisterUser(registerUser: RegisterUserDto): CommonResponse {
        val isRegister = authModule.registerUser(registerVo = registerUser.toRegistrationUser())
        return CommonResponse(
            message = "Register success",
            result = isRegister
        )
    }

    fun processLogin(loginUser: LoginUserDto): CommonResponse {
        val user = authModule.authenticate(authenticationVo = loginUser.toValidationUser())
        TODO("토큰 발급 로직 추가")
        return CommonResponse(
            message = "Login success",
            result = user
        )
    }
}