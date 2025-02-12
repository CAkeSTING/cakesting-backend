package com.cakesting.backend.security.util

import org.springframework.stereotype.Component
import java.security.SecureRandom

@Component
class SecureCodeGenerator {
    companion object {
        private const val CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789"
        private const val CODE_LENGTH = 6
        private val RANDOM = SecureRandom()

        /**
         * 인증 코드를 생성하는 메소드
         *
         * @return 생성된 인증 코드 (예: wu2mf9)
         */
        fun generate(): String {
            val code = StringBuilder(CODE_LENGTH)
            repeat(CODE_LENGTH) {
                code.append(CHARACTERS[RANDOM.nextInt(CHARACTERS.length)])
            }
            return code.toString()
        }
    }
}