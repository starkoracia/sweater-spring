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
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*


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

    @GetMapping("/main")
    fun blog(@RequestParam(required = false) filter: String?,
             model: Model): String {
        model["title"] = "Main"
        model["messages"] =
                if (!filter.isNullOrEmpty()) {
                    messageRepository.findByTagContainingIgnoreCase(filter)
                } else {
                    messageRepository.findAll()
                }
        return "main"
    }

    @PostMapping("/main")
    fun add(@AuthenticationPrincipal user: User,
            @RequestParam text: String,
            @RequestParam tag: String,
            @RequestParam("file") file: MultipartFile?,
            model: Model): String {
        val newMessage = Message(text = text, tag = tag, author = user)

        if(file != null && !file.isEmpty) {
            val uploadRir = File(uploadPath)
            if(!uploadRir.exists()) {
                uploadRir.mkdir()
            }

            val uuidFile = UUID.randomUUID().toString()
            val resultFilename = "${uuidFile}_${file.originalFilename}"

            file.transferTo(File("""$uploadPath\$resultFilename"""))

            newMessage.filename = resultFilename
        }

        messageRepository.save(newMessage)

        model["title"] = "Main"
        model["messages"] = messageRepository.findAll()
        return "main"
    }
}