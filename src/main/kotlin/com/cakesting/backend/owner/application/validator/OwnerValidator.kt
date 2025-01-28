package com.cakesting.backend.owner.application.validator

import com.cakesting.backend.file.domain.repository.FileRepository
import com.cakesting.backend.owner.application.dto.OwnerSignUpDto
import com.cakesting.backend.security.domain.repository.AccountRepository
import org.springframework.stereotype.Component

@Component
class OwnerValidator(
    private val accountRepository: AccountRepository,
    private val fileRepository: FileRepository
) {
    fun signupValidator(request: OwnerSignUpDto.Request) {
        validateLoginId(request.id)
        validateFileId(request.businessLicenceFileId)
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
}