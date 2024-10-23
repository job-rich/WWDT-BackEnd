package com.wwdt.auth.api

import com.wwdt.auth.api.request.EmailDto
import com.wwdt.auth.api.request.LoginUserDto
import com.wwdt.auth.api.request.RegisterUserDto
import com.wwdt.auth.application.UserApplication
import com.wwdt.shared_kernel.model.CommonResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/account")
class AccountController(
    private val userApplication: UserApplication
) {
    @PostMapping("/check-email")
    fun checkEmail(@RequestBody emailReq: EmailDto): ResponseEntity<CommonResponse> {
        val result: CommonResponse = userApplication.processCheckEmail(validationEmail = emailReq)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody registerReq: RegisterUserDto): ResponseEntity<CommonResponse> {
        val result: CommonResponse = userApplication.processRegisterUser(registerUser = registerReq)
        return ResponseEntity(result, HttpStatus.CREATED)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginReq: LoginUserDto): ResponseEntity<CommonResponse> {
        val result: CommonResponse = userApplication.processLogin(loginUser = loginReq)
        return ResponseEntity(result, HttpStatus.OK)
    }
}