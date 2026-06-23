package com.example.examenpoo2.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

@Serializable
object WelcomeRoute

@Serializable
object QuestionListRoute

@Serializable
data class QuestionDetailRoute(val questionId: Int)
