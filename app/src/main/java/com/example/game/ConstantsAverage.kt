package com.example.game

object ConstantsAverage {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"
    fun getQuestions(): ArrayList<QuestionAverage> {
        val questionList = ArrayList<QuestionAverage>()

        val que1 = QuestionAverage (
            1,
            "Поставьте токоограничивающий элемент",
            R.drawable.schema1_before,
            "Резистор",
            R.drawable.resistor,
            R.drawable.schema1_after_optionthree,
            "Конденсатор",
            R.drawable.capacitor,
            R.drawable.schema1_after_optionone,
            "Катушка",
            R.drawable.inductor,
            R.drawable.schema1_after_optiontwo,
            1
        )
        questionList.add(que1)
        val que2 = QuestionAverage (
            1,
            "Поставьте токоограничивающий элемент",
            R.drawable.schema1_before,
            "Катушка",
            R.drawable.inductor,
            R.drawable.schema1_after_optiontwo,
            "Конденсатор",
            R.drawable.capacitor,
            R.drawable.schema1_after_optionone,
            "Резистор",
            R.drawable.resistor,
            R.drawable.schema1_after_optionthree,
            3
        )
        questionList.add(que2)
        val que3 = QuestionAverage (
            1,
            "Поставьте токоограничивающий элемент",
            R.drawable.schema1_before,
            "Кондексатор",
            R.drawable.capacitor,
            R.drawable.schema1_after_optionone,
            "Резистор",
            R.drawable.resistor,
            R.drawable.schema1_after_optionthree,
            "Катушка",
            R.drawable.inductor,
            R.drawable.schema1_after_optiontwo,
            2
        )
        questionList.add(que3)
        return questionList
    }
}