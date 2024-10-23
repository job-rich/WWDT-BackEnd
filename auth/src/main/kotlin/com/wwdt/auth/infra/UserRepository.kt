package com.wwdt.auth.infra

import com.wwdt.auth.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, UUID> {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): User?
}

fun UserRepository.validateExistByEmail(email: String) {
    // Check if email already exists
    check (!existsByEmail(email = email)) { "Email already exists" }
}

fun UserRepository.findUserByEmail(email: String): User {
    // Check User by email if not found throw exception
    return findByEmail(email = email) ?: throw IllegalStateException("Email not found")
}