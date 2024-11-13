package com.wwdt.shared_kernel.model

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PreRemove
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@SQLRestriction("deleted_at is null")
class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    // var 로 선언되어 있지만 CreatedDate 어노테이션 과 updatable = false 설정으로 인해 값이 변경되지 않음
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null

    @Column
    var deletedAt: LocalDateTime? = null

    @PreRemove
    // PreRemove 어노테이션으로 인해 .delete() 호출 시 deletedAt 필드에 삭제 시간이 기록됨
    fun preRemove() {
        deletedAt = LocalDateTime.now()
    }
}