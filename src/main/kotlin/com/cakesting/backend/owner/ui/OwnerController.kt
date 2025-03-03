package com.cakesting.backend.owner.ui

import com.cakesting.backend.owner.application.OwnerService
import com.cakesting.backend.owner.application.dto.EmailVerificationDto
import com.cakesting.backend.owner.application.dto.OwnerSignUpDto
import com.cakesting.backend.owner.application.dto.OwnerVerificationEmailDto
import jakarta.validation.Valid
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/owner")
class OwnerController(
    private val ownerService: OwnerService
) {
    @PostMapping("/signup")
    fun signUpOwner(
        @RequestBody @Valid request: OwnerSignUpDto.Request
    ) {
        ownerService.signUpOwner(request)
    }

    @PostMapping("/verifications/emails")
    fun sendVerificationEmail(
        @RequestBody @Valid request: OwnerVerificationEmailDto.Request
    ) {
        ownerService.sendVerificationMail(request)
    }

    @PostMapping("/verifications/emails/verify")
    fun verifyEmailCode(
        @RequestBody @Valid reqeust: EmailVerificationDto.Request
    ) {
        ownerService.verifyEmailCode(reqeust)
    }
}