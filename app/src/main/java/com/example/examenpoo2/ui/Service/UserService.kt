package com.example.examenpoo2.ui.Service

import com.example.examenpoo2.ui.Model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("users/login")
    suspend fun login(@Body credentials: Map<String, String>): Response<User>
}
