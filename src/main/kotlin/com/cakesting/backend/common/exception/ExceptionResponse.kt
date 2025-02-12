package com.cakesting.backend.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

data class ExceptionResponse(
    val statusCode: Int,
    val message: String?,
    val error: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun toResponse(exception: Exception, status: HttpStatus): ResponseEntity<ExceptionResponse> {
            return ResponseEntity(from(exception, status), status)
        }

        fun from(exception: Exception, status: HttpStatus): ExceptionResponse {
            return ExceptionResponse(
                statusCode = status.value(),
                message = exception.message,
                error = status.reasonPhrase
            )
        }
    }
}