package com.starkoracia.sweaterspring.repositories

import com.starkoracia.sweaterspring.entities.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, Long> {
    fun findByTagContainingIgnoreCaseOrTextContainingIgnoreCaseOrAuthorUsernameContainingIgnoreCase(tag: String, text: String, authorName: String): List<Message?>
}