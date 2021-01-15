package com.starkoracia.sweaterspring.entities

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
class Message(
        @field:NotBlank(message = "Please fill the usernameExistMessage")
        @field:Length(max = 2048, message = "Message too long (more than 2kB)")
        var text: String,
        @field:NotBlank(message = "Please fill the tag")
        @field:Length(max = 255, message = "Message too long (more than 255)")
        var tag: String,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        var author: User?,
        var filename: String? = null) {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    fun getAuthorName() = author?.username ?: "unknown"
}