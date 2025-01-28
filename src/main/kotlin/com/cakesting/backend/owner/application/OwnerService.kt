package com.cakesting.backend.owner.application

import com.cakesting.backend.file.domain.repository.FileRepository
import com.cakesting.backend.owner.application.dto.OwnerSignUpDto
import com.cakesting.backend.owner.application.validator.OwnerValidator
import com.cakesting.backend.owner.domain.Owner
import com.cakesting.backend.owner.domain.repository.MarketRepository
import com.cakesting.backend.owner.domain.repository.OwnerRepository
import com.cakesting.backend.security.domain.repository.AccountRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional(readOnly = true)
class OwnerService(
    private val ownerRepository: OwnerRepository,
    private val marketRepository: MarketRepository,
    private val ownerValidator: OwnerValidator,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val fileRepository: FileRepository
) {
    @Transactional
    fun signUpOwner(
        request: OwnerSignUpDto.Request
    ) {
        ownerValidator.signupValidator(request)
        val owner = ownerRepository.save(request.toOwner(encodePassword(request.password)))
        val businessLicense = fileRepository.findById(request.businessLicenceFileId)
        marketRepository.save(request.toMarket(businessLicense.get(), owner))
    }

    fun encodePassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }
}