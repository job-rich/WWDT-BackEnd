package com.wwdt.entity

import com.wwdt.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_user")
class User(
    @Column(nullable = false, unique = true, length = 500)
    val email: String,

    @Column(nullable = false, length = 500)
    var password: String,

    @Column(nullable = false, length = 200)
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String

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