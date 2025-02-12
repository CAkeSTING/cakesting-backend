package com.cakesting.backend.security.domain.repository

import com.cakesting.backend.security.domain.EmailVerificationCode
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface EmailVerificationCodeRepository : JpaRepository<EmailVerificationCode, Long> {
    fun findTop1ByEmailAndCodeAndCreatedAtAfterOrderByCreatedAtDesc(
        email: String,
        code: String,
        createdAt: LocalDateTime
    ): EmailVerificationCode?
    fun findByEmailAndCode(email: String, code: String): EmailVerificationCode?
}