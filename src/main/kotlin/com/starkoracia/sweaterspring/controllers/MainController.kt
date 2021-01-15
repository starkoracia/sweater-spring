package com.starkoracia.sweaterspring.controllers

import com.starkoracia.sweaterspring.entities.Message
import com.starkoracia.sweaterspring.entities.User
import com.starkoracia.sweaterspring.repositories.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*
import javax.validation.Valid


@Controller
class MainController() {
    @Autowired
    private lateinit var messageRepository: MessageRepository

    @Value("\${upload.path}")
    lateinit var uploadPath: String

    @GetMapping("/")
    fun greeting(model: Model): String {
        model["title"] = "Main"
        return "greeting"
    }

    @GetMapping("/user-messages/{user}")
    fun userMessages(model: Model,
                     @PathVariable user: User,
                     @AuthenticationPrincipal currentUser: User,
                     @RequestParam(required = false) message: Message?): String {
        var messages: MutableSet<Message> = user.messages

        model["messages"] = messages
        if (message != null) {
            model["message"] = message
        }
        model["isCurrentUser"] = (currentUser == user)
        model["userChannel"] = user
        model["subscriptionsCount"] = user.subscriptions.size
        model["subscribersCount"] = user.subscribers.size
        model["isSubscriber"] = user.subscribers.contains(currentUser)

        return "userMessages"
    }

    @PostMapping("/user-messages/{user}")
    fun editUserMessage(model: Model,
                        @PathVariable user: User,
                        @AuthenticationPrincipal currentUser: User,
                        @RequestParam(name = "id") message: Message,
                        @RequestParam("text") text: String,
                        @RequestParam("tag") tag: String,
                        @RequestParam("file") file: MultipartFile?): String {

        if (text.isNotEmpty()) {
            message.text = text
        }
        if (tag.isNotEmpty()) {
            message.tag = tag
        }
        saveFile(file, message)

        return "redirect:/user-messages/${user.id}"
    }

    @GetMapping("/main")
    fun blog(@RequestParam(required = false) filter: String?,
             model: Model): String {
        model["title"] = "Main"
        model["messages"] =
                if (!filter.isNullOrEmpty()) {
                    messageRepository.findByTagContainingIgnoreCaseOrTextContainingIgnoreCaseOrAuthorUsernameContainingIgnoreCase(filter, filter, filter)
                } else {
                    messageRepository.findAll()
                }
        return "main"
    }

    @PostMapping("/main")
    fun add(
            @AuthenticationPrincipal user: User,
            @Valid
            message: Message,
            bindingResult: BindingResult,
            model: Model,
            @RequestParam("file") file: MultipartFile?): String {

        message.author = user

        if (bindingResult.hasErrors()) {
            val errorsMap = ControllerUtils.getErrors(bindingResult)
            model.mergeAttributes(errorsMap)
            model["message"] = message
        } else {
            saveFile(file, message)
            messageRepository.save(message)
            model.addAttribute("message", null)
        }
        model["title"] = "Main"
        model["messages"] = messageRepository.findAll()
        return "main"
    }

    private fun saveFile(file: MultipartFile?, message: Message) {
        if (file != null && !file.isEmpty) {
            val uploadRir = File(uploadPath)
            if (!uploadRir.exists()) {
                uploadRir.mkdir()
            }

            val uuidFile = UUID.randomUUID().toString()
            val resultFilename = "${uuidFile}_${file.originalFilename}"

            file.transferTo(File("""$uploadPath/$resultFilename"""))

            message.filename = resultFilename
        }
    }

}