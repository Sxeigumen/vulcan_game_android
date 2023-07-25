package com.example.game

data class QuestionAverage(
    val id: Int,
    val question: String,
    val image: Int,
    val imageOptionOne: Int,
    val imageAfterOptionOne: Int,
    val imageOptionTwo: Int,
    val imageAfterOptionTwo: Int,
    val imageOptionThree: Int,
    val imageAfterOptionThree: Int,
    val correctAnswer: Int
)
