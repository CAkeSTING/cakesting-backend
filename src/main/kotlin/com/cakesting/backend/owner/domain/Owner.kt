package com.cakesting.backend.owner.domain

import com.cakesting.backend.common.baseentity.BaseEntity
import com.cakesting.backend.file.domain.File
import com.cakesting.backend.security.domain.Account
import jakarta.persistence.*

@Entity
class Owner(
    var name: String? = null,
    var phoneNumber: String? = null,
    var type: OwnerType? = null,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "account_id", nullable = false)
    var account: Account? = null
) : BaseEntity() {

}