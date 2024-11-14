package com.wwdt.workspace.infra

import com.wwdt.workspace.domain.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProjectRepository: JpaRepository<Project, UUID> {
}