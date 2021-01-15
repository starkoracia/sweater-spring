package com.starkoracia.sweaterspring.controllers

import com.starkoracia.sweaterspring.entities.Role
import com.starkoracia.sweaterspring.entities.User
import com.starkoracia.sweaterspring.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userService: UserService


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userList")
    fun getUserList(model: Model): String {
        model["users"] = userService.findAll()
        return "adminUserList"
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    fun userEdit(@PathVariable user: User,
                 model: Model): String {
        model["user"] = user
        model["roles"] = Role.values()
        return "userEdit"
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    fun userSave(@RequestParam("userId") user: User,
                 @RequestParam username: String,
                 @RequestParam form: Map<String, String>): String {
        userService.saveUser(user, username, form)
        return "redirect:/user/userList"
    }

    @GetMapping("/subscribe/{user}")
    fun subscribe(@PathVariable user: User,
                  @AuthenticationPrincipal currentUser: User): String {
        userService.subscribe(currentUser, user)

        return "redirect:/user-messages/${user.id}"
    }

    @GetMapping("/unsubscribe/{user}")
    fun unsubscribe(@PathVariable user: User,
                    @AuthenticationPrincipal currentUser: User): String {
        userService.unsubscribe(currentUser, user)

        return "redirect:/user-messages/${user.id}"
    }

    @GetMapping("/{type}/{user}/list")
    fun userList(@PathVariable type: String,
                 @PathVariable user: User,
                 model: Model): String {
        if (type == "subscribers") {
            model["type"] = "Subscribers"
            model["users"] = user.subscribers
        } else {
            model["type"] = "Subscriptions"
            model["users"] = user.subscriptions
        }
        model["userChannel"] = user
        return "userList"
    }

    @GetMapping("/profile")
    fun profile(@AuthenticationPrincipal user: User,
                model: Model): String {
        model["userEmail"] = user.email
        return "profile"
    }

    @PostMapping("/profile")
    fun editProfile(@AuthenticationPrincipal user: User,
                    @RequestParam password: String,
                    @RequestParam email: String,
                    model: Model): String {
        val isEmailChanged = userService.editProfile(user, password, email)
        model["userEmail"] = user.email
        model["changedMessage"] = "Changes saved"
        model["isEmailChanged"] = isEmailChanged
        return "profile"
    }
}