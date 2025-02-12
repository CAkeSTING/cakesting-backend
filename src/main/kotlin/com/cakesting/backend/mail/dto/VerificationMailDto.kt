package com.cakesting.backend.mail.dto

import lombok.Getter
import lombok.NoArgsConstructor

data class VerificationMailDto(
    val verificationCode: String
) {
}