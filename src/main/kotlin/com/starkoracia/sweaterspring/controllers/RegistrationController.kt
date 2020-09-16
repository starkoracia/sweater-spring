package com.starkoracia.sweaterspring.controllers

import com.starkoracia.sweaterspring.entities.Role
import com.starkoracia.sweaterspring.entities.User
import com.starkoracia.sweaterspring.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class RegistrationController {
    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping("/registration")
    fun registration(model: Model) = "registration"

    @PostMapping("/registration")
    fun addUser(
                @RequestParam username: String,
                @RequestParam password: String,
                model: Model): String {
        return if(userRepository.findByUsername(username) != null) {
            model["message"] = "This username is already exist"
            "/registration"
        } else {
            userRepository.save(User(username, password, active = true, roles = mutableSetOf(Role.USER)))
            "redirect:/login"
        }

    }
}