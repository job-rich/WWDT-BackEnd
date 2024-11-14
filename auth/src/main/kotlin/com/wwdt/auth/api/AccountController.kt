package com.wwdt.auth.api

import com.wwdt.auth.api.request.*
import com.wwdt.auth.application.AccountApplication
import com.wwdt.shared_kernel.model.CommonResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/accounts")
class AccountController(
    private val accountApplication: AccountApplication
) {
    @GetMapping("/check-email")
    fun checkEmail(emailReq: EmailDto): ResponseEntity<CommonResponse> {
        val result: CommonResponse = accountApplication.processCheckEmail(validationEmail = emailReq)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody registerReq: RegisterUserDto): ResponseEntity<CommonResponse> {
        val result: CommonResponse = accountApplication.processRegisterUser(registerUser = registerReq)
        return ResponseEntity(result, HttpStatus.CREATED)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginReq: LoginUserDto): ResponseEntity<CommonResponse> {
        val result: CommonResponse = accountApplication.processLogin(loginUser = loginReq)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PatchMapping("/password")
    fun changePassword(@RequestBody changePasswordReq: ChangePasswordDto): ResponseEntity<CommonResponse> {
        val result: CommonResponse = accountApplication.processChangePassword(changePassword = changePasswordReq)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PatchMapping("/reset-password")
    fun resetPassword(@RequestBody resetPasswordReq: ResetPasswordDto): ResponseEntity<CommonResponse> {
        val result: CommonResponse = accountApplication.processResetPassword(resetPassword = resetPasswordReq)
        return ResponseEntity(result, HttpStatus.OK)
    }
}