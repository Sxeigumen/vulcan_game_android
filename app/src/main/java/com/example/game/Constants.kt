package com.example.game

lateinit var MAIN:LevelHardActivity
var ELECTROLYZE: Boolean = false

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"
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
            "Что за элемент представлен на фото?",
            R.drawable.inductor,
            "Конденсатор",
            "Резистор",
            "Диод",
            "Катушка",
            4
        )
        questionList.add(que2)
        val que3 = Question (
            3,
            "Где был создан самый маленький процесоор?",
            R.drawable.question3,
            "IBM",
            "Android",
            "Samsung",
            "Apple",
            1
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
            "Что за элемент представлен на фото?",
            R.drawable.capacitor,
            "Конденсатор",
            "Катушка",
            "Диод",
            "Резистор",
            1
        )
        questionList.add(que6)
        val que7 = Question (
            7,
            "Кто создал лампы накаливания?",
            R.drawable.question7,
            "Михаил Боресков",
            "Анатолий Еперин",
            "Валентин Вологдин",
            "Александр Лодыгин",
            4
        )
        questionList.add(que7)
        val que8 = Question (
            8,
            "Где был разработан Wi-Fi?",
            R.drawable.question8,
            "CNRS",
            "Columbia University",
            "CSIRO (Commonwealth Scientific and Industrial Research Organization)",
            "Seoul National University",
            3
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