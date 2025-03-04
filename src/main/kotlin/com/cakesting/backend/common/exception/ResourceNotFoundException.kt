package com.cakesting.backend.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(
    message: String
) : RuntimeException(message) {
    val status: HttpStatus = HttpStatus.NOT_FOUND
}