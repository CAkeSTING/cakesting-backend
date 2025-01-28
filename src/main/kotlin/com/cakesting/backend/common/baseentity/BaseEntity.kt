package com.cakesting.backend.common.baseentity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @Id
    @GeneratedValue
    var id: Long? = null;
    @CreatedDate
    @Column(nullable = false)
    var createdAt: LocalDateTime? = null
}