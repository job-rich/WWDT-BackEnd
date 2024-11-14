package com.wwdt.workspace.application.module

import com.wwdt.workspace.domain.*
import com.wwdt.workspace.domain.enums.Authority
import com.wwdt.workspace.infra.PermissionRepository
import com.wwdt.workspace.infra.RoleRepository
import com.wwdt.workspace.infra.validatePermissionExistence
import org.springframework.stereotype.Component

@Component
class PermissionModule(
    private val permissionRepository: PermissionRepository,
    private val roleRepository: RoleRepository
): PermissionService {
    override fun getRoleList(): Array<Role> {
        TODO("Not yet implemented")
    }

    override fun getRoleDetail(authority: Authority): Role {
        return roleRepository.findByAuthority(authority)
    }

    override fun createWorkspacePermission(workspacePermissionVo: WorkspacePermissionVo): Permission {
        val projectPermission = Permission(
            userId = workspacePermissionVo.userId,
            workspaceId = workspacePermissionVo.workspaceId,
            workspaceType = workspacePermissionVo.workspaceType,
            role = workspacePermissionVo.role,
        )
        return permissionRepository.save(projectPermission)
    }

    override fun hasPermission(workspacePermissionVo: WorkspacePermissionVo): Boolean {
        permissionRepository.validatePermissionExistence(
            userId = workspacePermissionVo.userId,
            workspaceId = workspacePermissionVo.workspaceId,
            roleSeq = workspacePermissionVo.role.seq,
        )
        return true
    }
}