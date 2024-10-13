package com.wwdt.auth.infra

import com.wwdt.auth.domain.Role
import com.wwdt.auth.domain.enums.RoleGrant
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long> {
    fun findRoleByType(type: RoleGrant): Role
}