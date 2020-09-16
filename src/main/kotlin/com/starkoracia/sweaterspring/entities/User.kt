package com.starkoracia.sweaterspring.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "usr")
class User(
        private var username: String,
        private var password: String,
        var active: Boolean,
        @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
        @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
        @Enumerated(EnumType.STRING)
        var roles: MutableSet<Role>,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null
) : UserDetails {

    fun isAdmin() = roles.contains(Role.ADMIN)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles

    override fun isEnabled(): Boolean = active

    override fun getUsername(): String = username

    fun setUsername(username: String) {
        this.username = username
    }

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

}
