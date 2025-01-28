package com.cakesting.backend.security.domain

import com.cakesting.backend.common.baseentity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class VerificationCode(
    @Column(nullable = false)
    val phoneNumber: String,
    @Column(nullable = false)
    val code: String
) : BaseEntity() {
}