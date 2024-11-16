package com.wwdt.workspace.domain

import com.wwdt.shared_kernel.model.BaseEntity
import com.wwdt.workspace.domain.enums.Authority
import jakarta.persistence.*

@Entity
@Table(name = "t_role")
class Role(

    @Column
    @Enumerated(EnumType.STRING)
    val authority: Authority,

    @Column
    val description: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L,
): BaseEntity()