package com.starkoracia.sweaterspring.service

import com.starkoracia.sweaterspring.entities.Role
import com.starkoracia.sweaterspring.entities.User
import com.starkoracia.sweaterspring.repositories.UserRepository
import com.starkoracia.sweaterspring.utils.MailSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var mailSender: MailSender
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Value("\${myhostname}")
    lateinit var hostname: String

    fun addUser(user: User): Boolean {
        val userFromDB = userRepository.findByUsername(user.username)
        return if (userFromDB != null) {
            false
        } else {
            user.activationCode = UUID.randomUUID().toString()
            user.password = passwordEncoder.encode(user.password)
            userRepository.save(user)

            sendEmailActivationMessage(user)

            true
        }
    }

    private fun sendEmailActivationMessage(user: User) {
        if (user.email.isNotEmpty()) {
            val message = """Hello, ${user.username}
                        |Welcome to Sweater. Please, visit next link: http://${hostname}/activate/${user.activationCode}"""
                    .trimMargin()

            mailSender.send(user.email, "Activation code", message)
        }
    }

    override fun loadUserByUsername(username: String): UserDetails = userRepository.findByUsername(username)!!

    fun activateUser(code: String): Boolean {
        val user = userRepository.findByActivationCode(code) ?: return false

        user.activationCode = null
        userRepository.save(user)

        return true
    }

    fun findAll(): MutableIterable<User> {
        return userRepository.findAll()
    }

    fun saveUser(user: User, username: String, form: Map<String, String>) {
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
    }

    fun editProfile(user: User, password: String, email: String): Boolean {
        if(password.isNotEmpty() && password != user.password) {
            user.password = passwordEncoder.encode(password)
        }
        val isEmailChanged = email.isNotEmpty() && email != user.email
        if(isEmailChanged) {
            user.email = email
            user.activationCode = UUID.randomUUID().toString()
            sendEmailActivationMessage(user)
        }
        userRepository.save(user)
        return isEmailChanged
    }

    fun subscribe(currentUser: User, user: User) {
        user.subscribers.add(currentUser)

        userRepository.save(user)
    }

    fun unsubscribe(currentUser: User, user: User) {
        user.subscribers.remove(currentUser)

        userRepository.save(user)
    }
}