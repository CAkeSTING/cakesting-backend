package com.cakesting.backend.common.exception

import com.cakesting.backend.common.exception.ExceptionResponse.Companion.toResponse
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Slf4j
@RestControllerAdvice
class ExceptionAdvice {
    private val log = LoggerFactory.getLogger(ExceptionAdvice::class.java)

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentException(exception: IllegalArgumentException): ResponseEntity<ExceptionResponse> {
        log.warn("IllegalArgumentException : {}", exception.message, exception)
        return toResponse(exception, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementException(exception: NoSuchElementException): ResponseEntity<ExceptionResponse> {
        log.warn("NoSuchElementException 발생 : {}", exception.message, exception)
        return toResponse(exception, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException): ResponseEntity<ExceptionResponse> {
        log.warn("ResourceNotFoundException 발생: {}", exception.message, exception)
        return ExceptionResponse.toResponse(exception, HttpStatus.NOT_FOUND)
    }
}