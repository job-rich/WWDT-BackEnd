package com.wwdt.workspace.domain

import com.wwdt.workspace.domain.enums.Authority
import com.wwdt.workspace.domain.enums.WorkspaceType

data class CreateProjectVo(
    val name: String,
    val description: String,
    val ownerId: String,
)

data class UpdateProjectVo(
    val projectId: String,
    val name: String,
    val description: String,
)

data class CreateSiloVo(
    val projectId: String,
    val name: String,
    val description: String,
    val ownerId: String,
)

data class UpdateSiloVo(
    val siloId: String,
    val name: String,
    val description: String,
)

data class CreateWorkspacePermissionVo(
    val userId: String,
    val workspaceId: String,
    val workspaceType: WorkspaceType,
    val role: Role,
)

data class HasPermissionVo(
    val userId: String,
    val workspaceId: String,
    val workspaceType: WorkspaceType,
    val permission: Authority,
)


