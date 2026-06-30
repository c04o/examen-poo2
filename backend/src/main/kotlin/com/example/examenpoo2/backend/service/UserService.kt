package com.example.examenpoo2.backend.service

import com.example.examenpoo2.backend.model.User
import com.example.examenpoo2.backend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<User> = userRepository.getAllUsers()
    fun getUserById(id: Int): User? = userRepository.getUserById(id)
    fun getUserByEmail(email: String): User? = userRepository.getUserByEmail(email)
    fun createUser(user: User): User = userRepository.addUser(user)
    fun updateUser(id: Int, user: User): User? = userRepository.updateUser(id, user)
    fun deleteUser(id: Int): Boolean = userRepository.deleteUser(id)
    
    fun login(email: String, password: String): User? {
        val user = userRepository.getUserByEmail(email)
        return if (user != null && user.password == password) {
            user
        } else {
            null
        }
    }
}
