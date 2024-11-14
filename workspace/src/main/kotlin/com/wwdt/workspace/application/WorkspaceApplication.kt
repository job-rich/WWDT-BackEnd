package com.wwdt.workspace.application

import com.wwdt.shared_kernel.model.CommonResponse
import com.wwdt.workspace.api.request.CreateProjectDto
import com.wwdt.workspace.domain.PermissionService
import com.wwdt.workspace.domain.Project
import com.wwdt.workspace.domain.ProjectService
import com.wwdt.workspace.domain.Role
import com.wwdt.workspace.domain.enums.Authority.OWNER
import com.wwdt.workspace.domain.enums.WorkspaceType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WorkspaceApplication(
    private val projectModule: ProjectService,
    private val permissionModule: PermissionService,
) {
    /*
    프로젝트 생성 -> 프로젝트 권한 존재하는지 검증 -> 프로젝트 권한 생성
     */
    @Transactional
    fun processCreateProject(createProject: CreateProjectDto): CommonResponse {
        val project: Project = projectModule.createProject(createProjectVo = createProject.toCreateProjectVo())
        val role: Role = permissionModule.getRoleDetail(authority = OWNER)
        val permission = createProject.toCreatePermissionVo(
            projectId = project.id,
            workspaceType = WorkspaceType.PROJECT,
            role = role,
        )
        permissionModule.hasPermission(workspacePermissionVo = permission)
        permissionModule.createWorkspacePermission(workspacePermissionVo = permission)
        return CommonResponse(
            message = "create project success",
            result = project
        )
    }
}