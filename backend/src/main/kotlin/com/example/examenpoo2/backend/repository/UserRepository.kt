package com.example.examenpoo2.backend.repository

import com.example.examenpoo2.backend.model.User
import com.example.examenpoo2.backend.model.UserRole
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import java.io.File

interface UserRepository {
    fun getAllUsers(): List<User>
    fun getUserById(id: Int): User?
    fun getUserByEmail(email: String): User?
    fun addUser(user: User): User
    fun updateUser(id: Int, user: User): User?
    fun deleteUser(id: Int): Boolean
}

@Repository
class JsonUserRepository : UserRepository {
    private val mapper = jacksonObjectMapper()
    private val users: MutableList<User> by lazy {
        try {
            val resource = ClassPathResource("users.json")
            mapper.readValue<List<User>>(resource.inputStream).toMutableList()
        } catch (e: Exception) {
            mutableListOf()
        }
    }

    override fun getAllUsers(): List<User> = users

    override fun getUserById(id: Int): User? = users.find { it.id == id }

    override fun getUserByEmail(email: String): User? = users.find { it.email == email }

    override fun addUser(user: User): User {
        val newUser = user.copy(id = (users.maxOfOrNull { it.id } ?: 0) + 1)
        users.add(newUser)
        return newUser
    }

    override fun updateUser(id: Int, user: User): User? {
        val index = users.indexOfFirst { it.id == id }
        return if (index != -1) {
            val updated = user.copy(id = id)
            users[index] = updated
            updated
        } else {
            null
        }
    }

    override fun deleteUser(id: Int): Boolean {
        return users.removeIf { it.id == id }
    }
}
