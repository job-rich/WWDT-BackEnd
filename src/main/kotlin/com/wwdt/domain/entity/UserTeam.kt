package com.wwdt.domain.entity

import com.wwdt.domain.entity.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_user_team")
class UserTeam(
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_seq")
    val team: Team,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L
): BaseEntity(){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserTeam) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "UserTeam(user=$user, team=$team, seq=$seq)"
    }
}