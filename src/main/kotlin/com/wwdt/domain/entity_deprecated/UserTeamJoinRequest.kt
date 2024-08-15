package com.wwdt.domain.entity_deprecated

import com.wwdt.domain.entity_deprecated.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_user_team_join_request")
class UserTeamJoinRequest(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_seq")
    val team: Team,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L

): BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserTeamJoinRequest) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "UserTeamJoinRequest(user=$user, team=$team, seq=$seq)"
    }
}