package com.wwdt.domain.entity

import com.wwdt.domain.entity.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_comment")
class Comment(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_seq")
    val parent: Comment? = null,

    @Column
    val content: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    val issue: Issue,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L
): BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Comment) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "Comment(" +
                "seq=$seq, " +
                "issue=$issue, " +
                "user=$user, " +
                "content='$content', " +
                "parent=$parent" +
                ")"
    }
}