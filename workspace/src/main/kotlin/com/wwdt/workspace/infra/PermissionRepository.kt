package com.wwdt.workspace.infra

import com.wwdt.workspace.domain.Permission
import com.wwdt.workspace.domain.enums.WorkspaceType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface PermissionRepository : JpaRepository<Permission, UUID> {
    fun existsByUserIdAndWorkspaceIdAndRoleSeq(userId: UUID, workspaceId: UUID, roleSeq: Long): Boolean
    fun findByUserIdAndWorkspaceIdAndWorkspaceType(userId: UUID, workspaceId: UUID, workspaceType: WorkspaceType): Permission?
}

fun PermissionRepository.validatePermissionExistence(userId: UUID, workspaceId: UUID, roleSeq: Long): Boolean {
    // Check if permission already exists
    return existsByUserIdAndWorkspaceIdAndRoleSeq(
        userId = userId,
        workspaceId = workspaceId,
        roleSeq = roleSeq
    )
}

fun PermissionRepository.findPermissionByUserIdAndWorkspaceIdAndWorkspaceType(userId: UUID, workspaceId: UUID, workspaceType: WorkspaceType): Permission {
    return findByUserIdAndWorkspaceIdAndWorkspaceType(
        userId = userId,
        workspaceId = workspaceId,
        workspaceType = workspaceType
    ) ?: throw NoSuchElementException("Permission not found")
}
