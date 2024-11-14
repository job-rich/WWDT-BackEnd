package com.wwdt.workspace.infra

import com.wwdt.workspace.domain.Role
import com.wwdt.workspace.domain.enums.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository: JpaRepository<Role, UUID> {
    fun findByAuthority(authority: Authority): Role
}

