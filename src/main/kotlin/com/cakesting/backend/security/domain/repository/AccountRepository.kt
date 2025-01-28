package com.cakesting.backend.security.domain.repository

import com.cakesting.backend.security.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun existsByLoginId(loginId: String): Boolean
}