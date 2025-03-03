package com.cakesting.backend.owner.application.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class EmailVerificationDto {
    data class Request (
        @field:NotBlank(message = "이메일은 필수 입력 항목입니다.")
        @field:Email(message = "유효한 이메일 형식이어야 합니다.")
        val email: String,

        @field:NotBlank(message = "인증 코드는 필수 입력 항목입니다.")
        @field:Size(min = 6, max = 6, message = "인증 코드는 6자리여야 합니다.")
        val code: String
    )
}