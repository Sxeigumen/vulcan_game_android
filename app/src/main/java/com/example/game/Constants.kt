package com.example.game

import android.text.BoringLayout

lateinit var MAIN:LevelHardActivity
var ELECTROLYZE: Boolean = false
var FIRSTMIX: Boolean = true
var FIRSTCOOL: Boolean = true
var FIRSTHEAT: Boolean = true
var FIRSTSHINE: Boolean = true
var FIRSTELECTROLYZE: Boolean = true
var GETNEWELEMENT: Boolean = false
object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"
    const val NUMBER_OF_GAME: String = "number"
    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()
        val que1 = Question (
            1,
            "В каком году появилась первая компьютерная игра?",
             R.drawable.question1,
            "1949",
            "1952",
            "1957",
            "1960",
            2
        )
        questionList.add(que1)
        val que2 = Question (
            2,
            "Если одиннадцать плюс два равняются одному, чему равны девять плюс пять",
            R.drawable.question2,
            "Два",
            "Шесть",
            "Десять",
            "Четырнадцать",
            1
        )
        questionList.add(que2)
        val que3 = Question (
            3,
            "У семерых братьев по сестре. Сколько всего сестер?",
            R.drawable.question3,
            "Семь",
            "Пять",
            "Три",
            "Одна",
            4
        )
        questionList.add(que3)
        val que4 = Question (
            4,
            "Как называется процесс исчезновения сопротивления у определенных элементов?",
            R.drawable.question4,
            "Фотосинтез",
            "Диффузий",
            "Сверхпроводимость",
            "Туннельный эффект",
            3
        )
        questionList.add(que4)
        val que5 = Question (
            5,
            "Какая игра стала самой первой многопользовательской?",
            R.drawable.question5,
            "Chess",
            "Tennis for two",
            "Pong",
            "OXO",
            2
        )
        questionList.add(que5)
        val que6 = Question (
            6,
            "Полтора судака стоят полтора рубля. Сколько стоят 13 судаков?",
            R.drawable.question6,
            "15 рублей",
            "19,5 рублей",
            "13 рублей",
            "16,5 рублей",
            3
        )
        questionList.add(que6)
        val que7 = Question (
            7,
            "По какой книге был снят фильм Бегущий по лезвию?",
            R.drawable.question7,
            "451 градус по Фаренгейту",
            "Бегущий по лезвию",
            "Мечтают ли андроиды об электроовцах?",
            "Солярис",
            3
        )
        questionList.add(que7)
        val que8 = Question (
            8,
            "Жаба — 3, собака — 3, корова — 2, рыба — 0. А чему равняется кошка? Почему?",
            R.drawable.question8,
            "0",
            "1",
            "2",
            "3",
            4
        )
        questionList.add(que8)
        val que9 = Question (
            9,
            "Какой архитектуры компьютеров пока не существует?",
            R.drawable.question9,
            "Квантовых",
            "Архитектуры фон Неймана",
            "Нейроморфных",
            "Многопроцессорных",
            1
        )
        questionList.add(que9)
        val que10 = Question (
            10,
            "На каком порту работает протокол HTTPS?",
            R.drawable.question10,
            "220",
            "443",
            "223",
            "80",
            2
        )
        questionList.add(que10)
        return questionList
    }
}