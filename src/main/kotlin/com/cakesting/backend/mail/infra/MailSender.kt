package com.cakesting.backend.mail.infra

import com.cakesting.backend.mail.dto.VerificationMailDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine

@Component
class MailSender(
    private val mailSender: JavaMailSender,
    springTemplateEngine: SpringTemplateEngine
) : TemplateConverter(springTemplateEngine) {

    fun sendVerificationMail(to: String, code: String) {
        val dto = VerificationMailDto(verificationCode = code)
        System.out.println(dto.verificationCode)
        val content = createTemplate("email-verification-template", dto)
        sendMail(to, "케익스팅 인증번호 확인 메일입니다.", content)
    }

    private fun sendMail(to: String, subject: String, content: String) {

        val message = mailSender.createMimeMessage()

        MimeMessageHelper(message).apply {
            setTo(to)
            setSubject(subject)
            setText(content, true)
        }

        mailSender.send(message)
    }
}