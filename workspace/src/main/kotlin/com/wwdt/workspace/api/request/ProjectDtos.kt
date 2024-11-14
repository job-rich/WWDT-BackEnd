package com.wwdt.workspace.api.request

import com.wwdt.shared_kernel.utils.SecurityExtension
import com.wwdt.workspace.domain.CreateProjectVo
import com.wwdt.workspace.domain.WorkspacePermissionVo
import com.wwdt.workspace.domain.Role
import com.wwdt.workspace.domain.enums.WorkspaceType
import java.util.*

data class CreateProjectDto(
    val name: String,
    val description: String,
) {
    init {
        require(name.isNotBlank()) { "Name is required" }
        require(description.isNotBlank()) { "Description is required" }
    }
    fun toCreateProjectVo(): CreateProjectVo {
        return CreateProjectVo(
            name = name,
            description = description,
            ownerId = SecurityExtension.getUserId(),
        )
    }
    fun toCreatePermissionVo(projectId: UUID, workspaceType: WorkspaceType, role: Role): WorkspacePermissionVo {
        return WorkspacePermissionVo(
            userId = SecurityExtension.getUserId(),
            workspaceId = projectId,
            workspaceType = workspaceType,
            role = role,
        )
    }
}