package com.starkoracia.sweaterspring.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class MailConfig {

    @Value("\${spring.mail.host}")
    private lateinit var host: String

    @Value("\${spring.mail.username}")
    private lateinit var username: String

    @Value("\${spring.mail.password}")
    private lateinit var password: String

    @Value("\${spring.mail.port}")
    private lateinit var port: String

    @Value("\${spring.mail.protocol}")
    private lateinit var protocol: String

    @Value("\${spring.mail.properties.mail.debug}")
    private lateinit var debug: String

    @Value("\${spring.mail.properties.mail.smtp.auth}")
    private lateinit var auth: String

    @Value("\${spring.mail.properties.mail.smtp.starttls.enable}")
    private lateinit var starttls: String

    @Bean
    fun getMailSender(): JavaMailSenderImpl {
        var mailSender = JavaMailSenderImpl()

        mailSender.host = host
        mailSender.username = username
        mailSender.password = password
        mailSender.port = port.toInt()
        mailSender.protocol = protocol

        val properties = mailSender.javaMailProperties
//        properties.setProperty("mail.transport.protocol", protocol)
        properties.setProperty("mail.smtp.auth", auth)
        properties.setProperty("mail.smtp.starttls.enable", starttls)
        properties.setProperty("mail.debug", debug)

        return mailSender
    }
}