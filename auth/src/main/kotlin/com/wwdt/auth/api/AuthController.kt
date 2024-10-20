package com.wwdt.auth.api

import com.wwdt.auth.api.request.RegisterUserDto
import com.wwdt.auth.application.IdentifierApplication
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/auth")
class AuthController(
    private val identifierApplication: IdentifierApplication
) {
    @PostMapping("/register")
    fun register(@Valid @RequestBody registerReq: RegisterUserDto): ResponseEntity<Boolean> {
        val result: Boolean = identifierApplication.processRegisterUser(registerUser = registerReq)
        return ResponseEntity(result, HttpStatus.CREATED)
    }

}