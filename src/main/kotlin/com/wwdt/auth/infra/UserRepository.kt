package com.wwdt.auth.infra

import com.wwdt.auth.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, UUID> {
    fun existsByEmail(email: String): Boolean
}