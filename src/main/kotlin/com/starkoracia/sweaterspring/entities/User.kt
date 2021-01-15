package com.starkoracia.sweaterspring.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "usr")
class User(
        @field:NotBlank(message = "Username cannot be empty")
        private var username: String,
        @field:NotBlank(message = "Password cannot be empty")
        private var password: String,
        @field:Transient
        var password2: String?,
        @field:Email(message = "Email is not correct")
        @field:NotBlank(message = "Email cannot be empty")
        var email: String,
        var activationCode: String? = null,
        var active: Boolean = true,
        @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
        @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
        @Enumerated(EnumType.STRING)
        var roles: MutableSet<Role> = mutableSetOf(Role.USER),
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null
) : UserDetails {

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    lateinit var messages: MutableSet<Message>

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = [JoinColumn(name = "channel_id")],
            inverseJoinColumns = [JoinColumn(name = "subscriber_id")])
    var subscribers: MutableSet<User> = hashSetOf()

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = [JoinColumn(name = "subscriber_id")],
            inverseJoinColumns = [JoinColumn(name = "channel_id")])
    var subscriptions: MutableSet<User> = hashSetOf()

    fun isAdmin() = roles.contains(Role.ADMIN)

    fun setPassword(password: String) {
        this.password = password
    }

    fun setUsername(username: String) {
        this.username = username
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles

    override fun isEnabled(): Boolean = active

    override fun getUsername(): String = username

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }


}
