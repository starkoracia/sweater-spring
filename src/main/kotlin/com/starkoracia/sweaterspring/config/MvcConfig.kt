package com.starkoracia.sweaterspring.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfig : WebMvcConfigurer {

    @Value("\${upload.path}")
    lateinit var uploadPath: String

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.apply {
            addViewController("/login").setViewName("login")
        }
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.apply {
            addResourceHandler("/img/**")
                    .addResourceLocations("""file:///${uploadPath}/""")
            addResourceHandler("/static/**")
                    .addResourceLocations("classpath:/static/")
        }
    }

}