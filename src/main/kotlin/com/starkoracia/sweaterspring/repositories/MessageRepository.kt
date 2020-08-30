package com.starkoracia.sweaterspring.repositories

import com.starkoracia.sweaterspring.entities.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, Long> {
    fun findByTagContainingIgnoreCase(filter: String): List<Message?>
}