package com.starkoracia.sweaterspring.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Message(
        private var text: String,
        private var tag: String,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private var id: Long? = null)