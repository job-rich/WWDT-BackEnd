package com.wwdt.domain.user_management

import com.wwdt.domain.entity_deprecated.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "t_user")
class User(
    @Column(nullable = false, length = 100)
    var username: String,

    @Column
    val email: String,

    @Column
    val password: String,

    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID = UUID.randomUUID()
): BaseEntity() {

}