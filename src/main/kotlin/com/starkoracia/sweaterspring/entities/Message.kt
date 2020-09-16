package com.starkoracia.sweaterspring.entities

import javax.persistence.*

@Entity
class Message(
        var text: String,
        var tag: String,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        var author: User?) {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var filename: String? = null

    fun getAuthorName() = author?.username ?: "<none>"
}