package com.wwdt.workspace.infra

import com.wwdt.workspace.domain.Permission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PermissionRepository : JpaRepository<Permission, UUID> {
    fun existsByUserIdAndWorkspaceIdAndRoleSeq(userId: UUID, workspaceId: UUID, roleSeq: Long): Boolean
}

fun PermissionRepository.validatePermissionExistence(userId: UUID, workspaceId: UUID, roleSeq: Long): Boolean {
    // Check if permission already exists
    return existsByUserIdAndWorkspaceIdAndRoleSeq(
        userId = userId,
        workspaceId = workspaceId,
        roleSeq = roleSeq
    )

}
