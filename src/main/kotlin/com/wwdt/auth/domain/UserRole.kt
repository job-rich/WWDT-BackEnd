package com.wwdt.auth.domain

import com.wwdt.shared_kernal.model.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_user_role")
class UserRole(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "role_seq")
    val role: Role,

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val seq: Long = 0
): BaseEntity()