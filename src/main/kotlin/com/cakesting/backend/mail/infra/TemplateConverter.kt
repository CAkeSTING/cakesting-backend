package com.cakesting.backend.mail.infra

import com.cakesting.backend.mail.dto.VerificationMailDto
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.context.Context
@Component
abstract class TemplateConverter(
    private val springTemplateEngine: SpringTemplateEngine
) {
    protected fun createTemplate(templateName: String, dto: Any): String {
        val context = Context().apply {
            setVariable(dto::class.simpleName, dto)
        }
        return springTemplateEngine.process(templateName, context)
    }
}