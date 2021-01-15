package com.starkoracia.sweaterspring.controllers

import com.starkoracia.sweaterspring.entities.User
import com.starkoracia.sweaterspring.entities.dto.CaptchaResponseDto
import com.starkoracia.sweaterspring.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.RestTemplate
import javax.validation.Valid

@Controller
class RegistrationController {

    val CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?"

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var userService: UserService

    @Value("\${recaptcha.secret.key}")
    lateinit var recaptchaSecretKey: String

    @GetMapping("/registration")
    fun registration(model: Model) = "registration"

    @PostMapping("/registration")
    fun addUser(
            @RequestParam("g-recaptcha-response") recaptchaResponse: String,
            @Valid user: User,
            bindingResult: BindingResult,
            model: Model): String {

        val url = CAPTCHA_URL + "secret=${recaptchaSecretKey}&response=${recaptchaResponse}"
        val response = restTemplate.postForObject(url, null, CaptchaResponseDto::class.java)!!

        if (!response.success) {
            model["captchaError"] = "Fill captcha"
        }

        val isPasswordsMatches = user.password == user.password2
        if (bindingResult.hasErrors() || !isPasswordsMatches || !response.success) {
            val errors = ControllerUtils.getErrors(bindingResult)
            if (!isPasswordsMatches) {
                errors["passwordMError"] = "Passwords not matches"
            }
            model.mergeAttributes(errors)
            model["user"] = user
            return "registration"
        } else {
            val isUserAdded = !userService.addUser(user)
            if (isUserAdded) {
                model["usernameExistMessage"] = "This username is already exist"
                return "registration"
            }
            return "redirect:/login"
        }
    }

    @GetMapping("/activate/{code}")
    fun activate(@PathVariable code: String,
                 model: Model): String {
        var isActivated: Boolean = userService.activateUser(code)
        model["message"] = if (isActivated) {
            "User successfully activated"
        } else {
            "Activation code is not found!"
        }
        return "login"
    }
}