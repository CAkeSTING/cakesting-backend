package com.cakesting.backend.mail

import com.cakesting.backend.SpringBootTestSupport
import com.cakesting.backend.mail.infra.MailSender
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender

@Disabled("메일 발신 500개 제한으로 인해 필요시에만 실행")
internal class MailTests(
    @Autowired
    private var mailSender: MailSender
) : SpringBootTestSupport() {

    @Test
    fun sendMailTest() {
        val to = "narahim.lee@gmail.com"
        mailSender.sendVerificationMail(to, "1234245")
    }
}