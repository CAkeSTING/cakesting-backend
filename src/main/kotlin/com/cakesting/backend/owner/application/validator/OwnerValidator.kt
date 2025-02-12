package com.cakesting.backend.owner.application.validator

import com.cakesting.backend.common.exception.ResourceNotFoundException
import com.cakesting.backend.file.domain.repository.FileRepository
import com.cakesting.backend.owner.application.dto.OwnerSignUpDto
import com.cakesting.backend.security.domain.repository.AccountRepository
import com.cakesting.backend.security.domain.repository.EmailVerificationCodeRepository
import org.springframework.stereotype.Component

@Component
class OwnerValidator(
    private val accountRepository: AccountRepository,
    private val fileRepository: FileRepository,
    private val emailVerificationCodeRepository: EmailVerificationCodeRepository
) {
    fun signupValidator(request: OwnerSignUpDto.Request) {
        validateLoginId(request.id)
        validateFileId(request.businessLicenceFileId)
        validateVerificationCode(request.email, request.verificationCode)
    }

    private fun validateLoginId(loginId: String) {
        if (accountRepository.existsByLoginId(loginId)) {
            throw IllegalArgumentException()
        }
    }

    private fun validateFileId(fileId: Long) {
        if (!fileRepository.existsById(fileId)) {
            throw IllegalArgumentException()
        }
    }

    private fun validateVerificationCode(email: String, code: String) {
        emailVerificationCodeRepository.findByEmailAndCode(email, code)
            ?: throw ResourceNotFoundException("잘못된 인증번호입니다.")
    }
}