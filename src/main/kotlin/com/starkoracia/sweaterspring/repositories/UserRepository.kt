package com.starkoracia.sweaterspring.repositories

import com.starkoracia.sweaterspring.entities.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}