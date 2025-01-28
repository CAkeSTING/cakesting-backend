package com.cakesting.backend.owner.domain.repository

import com.cakesting.backend.owner.domain.Owner
import org.springframework.data.jpa.repository.JpaRepository

interface OwnerRepository : JpaRepository<Owner, Long> {
}