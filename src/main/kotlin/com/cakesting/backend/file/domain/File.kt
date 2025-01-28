package com.cakesting.backend.file.domain

import com.cakesting.backend.common.baseentity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class File(
    @Column(nullable = false)
    var originName: String,
    @Column(nullable = false)
    var path: String,
    @Column(nullable = false)
    var size: Long,
    var extension: String,
    @Column(nullable = false)
    var isDeleted: Boolean = false
) : BaseEntity() {
}