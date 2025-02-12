package com.cakesting.backend.mail.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Configuration
class EmailProperties {

    @Value("\${spring.mail.host}")
    private lateinit var host: String

    @Value("\${spring.mail.port}")
    private var port: Int = 0

    @Value("\${spring.mail.username}")
    private lateinit var username: String

    @Value("\${spring.mail.password}")
    private lateinit var password: String

    @Value("\${spring.mail.properties.mail.smtp.auth}")
    private var auth: Boolean = false

    @Value("\${spring.mail.properties.mail.smtp.starttls.enable}")
    private var starttlsEnable: Boolean = false

    @Value("\${spring.mail.properties.mail.smtp.starttls.required}")
    private var starttlsRequired: Boolean = false

    @Value("\${spring.mail.properties.mail.smtp.connectiontimeout}")
    private var connectionTimeout: Int = 0

    @Value("\${spring.mail.properties.mail.smtp.timeout}")
    private var timeout: Int = 0

    @Value("\${spring.mail.properties.mail.smtp.writetimeout}")
    private var writeTimeout: Int = 0


    @Bean
    fun javaMailSender(): JavaMailSender {
        return JavaMailSenderImpl().apply {
            host = this@EmailProperties.host
            port = this@EmailProperties.port
            username = this@EmailProperties.username
            password = this@EmailProperties.password
            defaultEncoding = "UTF-8"
            javaMailProperties = getMailProperties()
        }
    }

    private fun getMailProperties(): Properties {
        return Properties().apply {
            put("mail.smtp.auth", auth.toString())
            put("mail.smtp.starttls.enable", starttlsEnable.toString())
            put("mail.smtp.starttls.required", starttlsRequired.toString())
            put("mail.smtp.connectiontimeout", connectionTimeout.toString())
            put("mail.smtp.timeout", timeout.toString())
            put("mail.smtp.writetimeout", writeTimeout.toString())
        }
    }
}