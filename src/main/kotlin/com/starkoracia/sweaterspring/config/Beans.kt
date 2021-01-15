package com.starkoracia.sweaterspring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.client.RestTemplate

@Configuration
class Beans {
    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(8)
    }

    @Bean
    fun getRestTemplate() : RestTemplate {
        return RestTemplate()
    }
}

