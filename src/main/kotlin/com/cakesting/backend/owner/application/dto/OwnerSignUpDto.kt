package com.cakesting.backend.owner.application.dto

import com.cakesting.backend.file.domain.File
import com.cakesting.backend.owner.domain.Market
import com.cakesting.backend.owner.domain.Owner
import com.cakesting.backend.owner.domain.OwnerType
import com.cakesting.backend.security.domain.Account
import com.cakesting.backend.security.domain.AccountStatus
import jakarta.validation.constraints.*

class OwnerSignUpDto {
    data class Request(
        @field:NotBlank
        @field:Size(min = 6, max = 50, message = "아이디는 6 ~ 50자 길이여야 합니다.")
        @field:Pattern(
            regexp = "^[a-zA-Z0-9]+$",
            message = "아이디는 영문 대소문자와 숫자만 사용할 수 있습니다."
        )
        val id: String,

        @field:NotBlank
        @field:Size(min = 6, max = 50, message = "비밀번호는 6 ~ 50자 길이여야 합니다.")
        @field:Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[a-zA-Z\\d@\$!%*?&]+$",
            message = "비밀번호는 대소문자, 숫자, 특수문자를 모두 포함해야 합니다."
        )
        val password: String,

        @field:NotBlank
        @field:Size(min = 2, max = 50, message = "이름은 2 ~ 50자 길이여야 합니다.")
        val name: String,

        @field:NotBlank(message = "이메일은 필수 입력 항목입니다.")
        @field:Email(message = "유효한 이메일 형식이어야 합니다.")
        val email: String,

        @field:NotBlank
        @field:Pattern(
            regexp = "^(01[0-9])\\d{3,4}\\d{4}$",
            message = "전화번호는 01012345678 형식의 숫자만 입력해야 합니다."
        )
        val phoneNumber: String,

        @field:NotBlank
        val verificationCode: String,

        @field:NotBlank
        val marketName: String,

        val marketPhoneNumber: String,

        @field:Pattern(
            regexp = "^\\d{3}-\\d{2}-\\d{5}$",
            message = "사업자 번호형식에 맞지 않습니다."
        )
        val businessNumber: String,

        @field:NotNull
        val businessLicenceFileId: Long

    )
     {
        fun toOwner(encryptedPassword: String): Owner {
            val account = Account(
                loginId = this.id,
                password = encryptedPassword,
                status = AccountStatus.INACTIVE
            )

            return Owner(
                account = account,
                name = this.name,
                phoneNumber = this.phoneNumber,
                type = OwnerType.HOST
            )
        }

         fun toMarket(file: File, owner: Owner): Market {
            return Market(
                owner = owner,
                marketName = marketName,
                marketPhoneNumber = marketPhoneNumber,
                businessNumber = businessNumber,
                businessLicence = file
            )
         }
    }



}