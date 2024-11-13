package com.wwdt.workspace.domain

interface PermissionService {
    fun getRoleList(): Array<Role>
    fun createWorkspacePermission(createWorkspacePermissionVo: CreateWorkspacePermissionVo): Boolean
    fun hasPermission(permissionVo: HasPermissionVo): Boolean
}

