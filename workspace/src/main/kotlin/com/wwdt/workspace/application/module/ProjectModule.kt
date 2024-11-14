package com.wwdt.workspace.application.module

import com.wwdt.workspace.domain.CreateProjectVo
import com.wwdt.workspace.domain.Project
import com.wwdt.workspace.domain.ProjectService
import com.wwdt.workspace.domain.UpdateProjectVo
import com.wwdt.workspace.infra.ProjectRepository
import org.springframework.stereotype.Component

@Component
class ProjectModule(
    private val projectRepository: ProjectRepository
): ProjectService {
    override fun createProject(createProjectVo: CreateProjectVo): Project {
        val newProject = Project(
            name = createProjectVo.name,
            description = createProjectVo.description,
            ownerId = createProjectVo.ownerId,
        )
        return projectRepository.save(newProject)
    }

    override fun getProjectDetail(projectId: String): Project {
        TODO("Not yet implemented")
    }

    override fun getProjectList(userId: String): Array<Project> {
        TODO("Not yet implemented")
    }

    override fun updateProject(updateProjectVo: UpdateProjectVo): Project {
        TODO("Not yet implemented")
    }

    override fun deleteProject(projectId: String) {
        TODO("Not yet implemented")
    }
}