package com.starkoracia.sweaterspring.controllers

import com.starkoracia.sweaterspring.entities.Message
import com.starkoracia.sweaterspring.repositories.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class GreetingController(
        @Autowired
        private val messageRepository: MessageRepository) {

    @GetMapping("/")
    fun greeting(model: Model): String {
        return "greeting"
    }

    @GetMapping("/main")
    fun blog(model: Model): String {
        model["title"] = "Main"
        model["messages"] = messageRepository.findAll()
        return "main"
    }

    @PostMapping("/main")
    fun add(@RequestParam text: String,
            @RequestParam tag: String,
            model: Model): String {
        val newMessage = Message(text = text, tag = tag)
        messageRepository.save(newMessage)

        model["title"] = "Main"
        model["messages"] = messageRepository.findAll()
        return "main"
    }

    @PostMapping("filter")
    fun filter(@RequestParam filter: String,
               model: Model): String {
        model["messages"] =
                if (filter.isNotEmpty()) {
                    messageRepository.findByTagContainingIgnoreCase(filter)
                } else {
                    messageRepository.findAll()
                }
        return "main"
    }
}