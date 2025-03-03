package com.cakesting.backend.owner.application

import com.cakesting.backend.common.exception.ResourceNotFoundException
import com.cakesting.backend.file.domain.repository.FileRepository
import com.cakesting.backend.mail.infra.MailSender
import com.cakesting.backend.owner.application.dto.EmailVerificationDto
import com.cakesting.backend.owner.application.dto.OwnerSignUpDto
import com.cakesting.backend.owner.application.dto.OwnerVerificationEmailDto
import com.cakesting.backend.owner.application.validator.OwnerValidator
import com.cakesting.backend.owner.domain.Owner
import com.cakesting.backend.owner.domain.repository.MarketRepository
import com.cakesting.backend.owner.domain.repository.OwnerRepository
import com.cakesting.backend.security.domain.EmailVerificationCode
import com.cakesting.backend.security.domain.repository.AccountRepository
import com.cakesting.backend.security.domain.repository.EmailVerificationCodeRepository
import com.cakesting.backend.security.util.SecureCodeGenerator
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class OwnerService(
    private val ownerRepository: OwnerRepository,
    private val marketRepository: MarketRepository,
    private val emailVerificationCodeRepository: EmailVerificationCodeRepository,
    private val ownerValidator: OwnerValidator,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val mailSender: MailSender,
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

    @Transactional
    fun sendVerificationMail(
        request: OwnerVerificationEmailDto.Request
    ) {
        EmailVerificationCode(
            email = request.email,
            code = SecureCodeGenerator.generate()
        ).apply {
            emailVerificationCodeRepository.save(this)
            mailSender.sendVerificationMail(email, code)
        }
    }

    fun verifyEmailCode(
        request: EmailVerificationDto.Request
    ) {
        val fiveMinutesAgo = LocalDateTime.now().minusMinutes(5)
        emailVerificationCodeRepository.findTop1ByEmailAndCodeAndCreatedAtAfterOrderByCreatedAtDesc(
            request.email,
            request.code,
            fiveMinutesAgo
        ) ?: throw ResourceNotFoundException("잘못된 인증번호입니다.")
    }
}