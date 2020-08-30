package com.starkoracia.sweaterspring.entities

import javax.persistence.*

@Entity
@Table(name = "usr")
class User(
        private var username: String,
        private var password: String,
        private var active: Boolean,
            @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
            @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
            @Enumerated(EnumType.STRING)
        private var roles:Set<Role>,
            @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private var id: Long
)