package com.wwdt.workspace.domain

import com.wwdt.auth.domain.enums.LoginType
import com.wwdt.shared_kernel.model.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "t_user",
       indexes = [Index(name = "idx_user_email", columnList = "email", unique = true)]
)
class User(
    @Column(length = 255)
    var password: String,

    @Column(length = 255)
    var name: String,

    @Column(unique = true, length = 255)
    val email: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val loginType: LoginType = LoginType.LOCAL,

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
): BaseEntity()