package com.wwdt.workspace.domain

interface ProjectService {
    fun createProject(createProjectVo: CreateProjectVo): Project
    fun getProjectDetail(projectId: String): Project
    fun getProjectList(userId: String): Array<Project>
    fun updateProject(updateProjectVo: UpdateProjectVo): Project
    fun deleteProject(projectId: String)
}