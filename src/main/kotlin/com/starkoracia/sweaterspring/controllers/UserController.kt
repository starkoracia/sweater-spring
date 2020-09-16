package com.starkoracia.sweaterspring.controllers

import com.starkoracia.sweaterspring.entities.Role
import com.starkoracia.sweaterspring.entities.User
import com.starkoracia.sweaterspring.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository


    @GetMapping("/userList")
    fun getUserList(model: Model): String {
        model["users"] = userRepository.findAll()
        return "userList"
    }

    @GetMapping("{user}")
    fun userEdit(@PathVariable user: User,
                 model: Model): String {
        model["user"] = user
        model["roles"] = Role.values()
        return "userEdit"
    }

    @PostMapping()
    fun userSave(@RequestParam("userId") user: User,
                 @RequestParam username: String,
                 @RequestParam form: Map<String, String>): String {
        user.username = username

        val roles = Role.values()
                .map { it.name }.toSet()

        val roleList = form.keys
                .filter { roles.contains(it) }
                .map { Role.valueOf(it) }

        user.roles.apply {
            clear()
            addAll(roleList)
        }
        userRepository.save(user)

        return "redirect:/user/userList"
    }
}