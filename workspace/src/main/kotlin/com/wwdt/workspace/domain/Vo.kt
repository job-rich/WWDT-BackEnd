package com.wwdt.workspace.domain

import com.wwdt.workspace.domain.enums.WorkspaceType
import java.util.*

data class CreateProjectVo(
    val name: String,
    val description: String,
    val ownerId: UUID,
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

data class WorkspacePermissionVo(
    val userId: UUID,
    val workspaceId: UUID,
    val workspaceType: WorkspaceType,
    val role: Role,
)



