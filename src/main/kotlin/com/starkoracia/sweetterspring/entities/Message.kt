package com.starkoracia.sweetterspring.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Message(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,
        var text: String,
        var tag: String
)