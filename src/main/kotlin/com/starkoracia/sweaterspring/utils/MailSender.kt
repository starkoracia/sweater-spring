package com.starkoracia.sweaterspring.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailSender {
    @Autowired
    private lateinit var mailSender: JavaMailSender

    @Value("\${spring.mail.username}")
    private lateinit var username: String

    fun send(emailTo: String, subject: String, message: String) {
        var mailMessage = SimpleMailMessage()
        mailMessage.apply {
            setFrom(username)
            setTo(emailTo)
            setSubject(subject)
            setText(message)
        }
        mailSender.send(mailMessage)
    }
}