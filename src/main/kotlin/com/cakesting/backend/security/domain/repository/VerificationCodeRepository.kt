package com.cakesting.backend.security.domain.repository

import com.cakesting.backend.security.domain.VerificationCode
import org.springframework.data.jpa.repository.JpaRepository

interface VerificationCodeRepository : JpaRepository<VerificationCode, Long> {
}