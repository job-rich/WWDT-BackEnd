package com.wwdt.workspace.domain

import com.wwdt.workspace.domain.enums.Authority

interface PermissionService {
    fun getRoleList(): Array<Role>
    fun getRoleDetail(authority: Authority): Role
    fun createWorkspacePermission(workspacePermissionVo: WorkspacePermissionVo): Permission
    fun hasPermission(workspacePermissionVo: WorkspacePermissionVo): Boolean
}


