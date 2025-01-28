package com.cakesting.backend.security.domain

import com.cakesting.backend.common.baseentity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class Account(
    var loginId: String? = null,
    var password: String? = null,
    @Enumerated(EnumType.STRING)
    var status: AccountStatus
) : BaseEntity() {
}