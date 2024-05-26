package com.wwdt.domain.entity

import com.wwdt.domain.entity.common.BaseEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "t_user",
       indexes = [Index(name = "idx_user_email", columnList = "email", unique = true)]
)
class User(
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    val userJoinTeamRequests: MutableList<UserTeamJoinRequest> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_seq")
    val role: Role,

    @Column(nullable = false, length = 500)
    val email: String,

    @Column(nullable = false, length = 500)
    var password: String,

    @Column(nullable = false, length = 300)
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID()

): BaseEntity(){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (email != other.email) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(email='$email', name='$name', id='$id')"
    }

}