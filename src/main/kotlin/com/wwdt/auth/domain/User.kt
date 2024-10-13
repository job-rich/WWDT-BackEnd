package com.wwdt.auth.domain

import com.wwdt.shared_kernal.model.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "t_user",
       indexes = [Index(name = "idx_user_email", columnList = "email", unique = true)]
)
class User(

    @OneToMany(mappedBy = "user_id", cascade = [CascadeType.ALL] ,fetch = FetchType.LAZY)
    val role: MutableList<Role> = mutableListOf(),

    @Column(length = 255)
    var password: String,

    @Column(length = 255)
    var name: String,

    @Column(unique = true, length = 255)
    val email: String,

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
): BaseEntity()