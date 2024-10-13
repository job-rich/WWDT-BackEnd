package com.wwdt.auth.api

import com.wwdt.auth.api.request.RegisterUserDto
import com.wwdt.auth.application.AuthApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/v1/api/auth")
class AuthController(
    private val authApplication: AuthApplication
) {
    @PostMapping("/register")
    fun register(@RequestBody registerReq: RegisterUserDto) =
        authApplication.processSample(
        )
}