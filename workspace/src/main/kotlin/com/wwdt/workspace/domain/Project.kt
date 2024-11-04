package com.wwdt.workspace.domain

import com.wwdt.shared_kernel.model.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(
    name = "t_project",
    indexes = [Index(name = "idx_project_name", columnList = "name"), Index(name = "idx_project_owner_id", columnList = "ownerId")]
)
class Project(

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val silos: MutableList<Silo> = mutableListOf(),

    @Column(nullable = false, length = 255)
    var name: String,

    @Column
    val description: String,

    @Column
    val ownerId: UUID,

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID
): BaseEntity()