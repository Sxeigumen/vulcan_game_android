package com.example.game

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"
    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val que1 = Question (
            1,
            "Что за элемент представлен на фото?",
             R.drawable.diod,
            "Диод",
            "Конденсатор",
            "Катушка",
            "Резистор",
            1
                )
        questionList.add(que1)
        val que2 = Question (
            2,
            "Что за элемент представлен на фото?",
            R.drawable.capacitor,
            "Диод",
            "Катушка",
            "Резистор",
            "Конденсатор",
            4
        )
        questionList.add(que2)
        val que3 = Question (
            3,
            "Что за элемент представлен на фото?",
            R.drawable.inductor,
            "Резистор",
            "Диод",
            "Катушка",
            "Конденсатор",
            3
        )
        questionList.add(que3)
        val que4 = Question (
            4,
            "Что за элемент представлен на фото?",
            R.drawable.resistor,
            "Диод",
            "Резистор",
            "Конденсатор",
            "Катушка",
            2
        )
        questionList.add(que4)
        return questionList
    }
}