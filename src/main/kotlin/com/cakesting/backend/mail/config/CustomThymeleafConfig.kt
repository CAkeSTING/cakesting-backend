package com.cakesting.backend.mail.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

@Configuration
class CustomThymeleafConfig {

    @Bean
    fun thymeleafTemplateResolver(): ClassLoaderTemplateResolver {
        return ClassLoaderTemplateResolver().apply {
            prefix = "/mail-template/"
            suffix = ".hbs"
            isCacheable = false
            characterEncoding = "UTF-8"
            templateMode = TemplateMode.HTML
        }
    }

    @Bean
    fun templateEngine(): SpringTemplateEngine {
        return SpringTemplateEngine().apply {
            setTemplateResolver(thymeleafTemplateResolver())
        }
    }
}