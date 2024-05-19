package com.wwdt.entity

import com.wwdt.common.BaseEntity
import jakarta.persistence.*

@Entity
class Team(
    @Column(length = 10000)
    var description: String,

    @Column(nullable = false, length = 100)
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long?
): BaseEntity(){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Team) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Team(name='$name', seq=$seq, description='$description')"
    }
}