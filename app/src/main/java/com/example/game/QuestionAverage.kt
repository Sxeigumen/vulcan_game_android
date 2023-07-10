package com.example.game

data class QuestionAverage(
    val id: Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val imageOptionOne: Int,
    val imageAfterOptionOne: Int,
    val optionTwo: String,
    val imageOptionTwo: Int,
    val imageAfterOptionTwo: Int,
    val optionThree: String,
    val imageOptionThree: Int,
    val imageAfterOptionThree: Int,
    val correctAnswer: Int
)
