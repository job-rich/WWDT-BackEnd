package com.wwdt.workspace.infra

import com.wwdt.workspace.domain.Permission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PermissionRepository : JpaRepository<Permission, UUID> {
    fun existsByUserIdAndWorkspaceIdAndRoleSeq(userId: UUID, workspaceId: UUID, roleSeq: Long): Boolean
}

fun PermissionRepository.validatePermissionExistence(userId: UUID, workspaceId: UUID, roleSeq: Long) {
    // Check if permission already exists
    check(
        !existsByUserIdAndWorkspaceIdAndRoleSeq(
            userId = userId,
            workspaceId = workspaceId,
            roleSeq = roleSeq
        )
    ) { "Permission already exists" }
}