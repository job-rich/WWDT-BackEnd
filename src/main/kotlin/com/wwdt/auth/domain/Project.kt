package com.wwdt.auth.domain

import com.wwdt.shared_kernal.model.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(
    name = "t_project",
    indexes = [Index(name = "idx_project_name", columnList = "name")]
)
class Project(

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val silos: MutableList<Silo> = mutableListOf(),

    @Column(nullable = false, length = 255)
    var name: String,

    @Column
    val description: String,

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID
): BaseEntity()