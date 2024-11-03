package com.wwdt.workspace.application

import com.wwdt.workspace.api.request.*
import com.wwdt.workspace.domain.AccountService
import com.wwdt.workspace.domain.EditService
import com.wwdt.shared_kernel.infra.TokenProvider
import com.wwdt.shared_kernel.model.CommonResponse
import org.springframework.stereotype.Service

@Service
class UserApplication(
    private val authModule: AccountService,
    private val editModule: EditService,
    private val tokenProvider: TokenProvider
) {
    fun processCheckEmail(validationEmail: EmailDto): CommonResponse {
        val isExist = authModule.isExistEmail(email = validationEmail.email)
        return CommonResponse(
            message = "Email is not exist Continue to register",
            result = isExist
        )
    }

    fun processRegisterUser(registerUser: RegisterUserDto): CommonResponse {
        check(authModule.isExistEmail(registerUser.email))
        val isRegister = authModule.registerUser(registerVo = registerUser.toRegistrationUser())
        return CommonResponse(
            message = "Register success",
            result = isRegister
        )
    }

    fun processLogin(loginUser: LoginUserDto): CommonResponse {
        val user = authModule.authenticate(authenticationVo = loginUser.toValidationUser())
        val token = tokenProvider.generateToken(user.email)
        return CommonResponse(
            message = "Login success",
            result = token
        )
    }

    fun processChangePassword(changePassword: ChangePasswordDto): CommonResponse {
        val isChange = editModule.changePassword(changeVo = changePassword.toPasswordChange())
        return CommonResponse(
            message = "Change password success",
            result = isChange
        )
    }

    fun processResetPassword(resetPassword: ResetPasswordDto): CommonResponse {
        val newPassword = editModule.resetPassword(resetVo = resetPassword.toResetPassword())
        return CommonResponse(
            message = "Reset password success",
            result = newPassword
        )
    }
}