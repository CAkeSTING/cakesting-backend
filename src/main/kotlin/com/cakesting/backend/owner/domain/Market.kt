package com.cakesting.backend.owner.domain

import com.cakesting.backend.common.baseentity.BaseEntity
import com.cakesting.backend.file.domain.File
import jakarta.persistence.*

@Entity
class Market(

    @Column(nullable = false)
    var marketName: String,

    @Column(nullable = false)
    var marketPhoneNumber: String,

    @Column(nullable = false, unique = true)
    var businessNumber: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    var owner: Owner,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "business_licence_file_id")
    var businessLicence: File
) : BaseEntity() {

}