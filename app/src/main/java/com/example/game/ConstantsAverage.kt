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
            R.drawable.schema1_option_one,
            R.drawable.schema1_after_option_one,
            R.drawable.schema1_option_two,
            R.drawable.schema1_after_option_two,
            R.drawable.schema1_option_three,
            R.drawable.schema1_after_option_three,
            R.drawable.schema1_after_option_three
        )
        questionList.add(que1)
        val que2 = QuestionAverage (
            2,
            "Поставьте защитный диод в правильном направлении",
            R.drawable.schema2_before,
            R.drawable.schema2_option_one,
            R.drawable.schema2_after_option_one,
            R.drawable.schema2_option_two,
            R.drawable.schema2_after_option_two,
            R.drawable.schema2_option_three,
            R.drawable.schema2_after_option_three,
            R.drawable.schema2_after_option_two
        )
        questionList.add(que2)
        val que3 = QuestionAverage (
            3,
            "Рассчитайте токоограничивающий резистор, еслм падение напряжения на диоде равно 1,5 В, ток в цепи равен 1 А, напряжение питания равно 5 В",
            R.drawable.schema3_before,
            R.drawable.schema3_option_one,
            R.drawable.schema3_after_option_one,
            R.drawable.schema3_option_two,
            R.drawable.schema3_after_option_two,
            R.drawable.schema3_option_three,
            R.drawable.schema3_after_option_three,
            R.drawable.schema3_after_option_two
        )
        questionList.add(que3)
        val que4 = QuestionAverage (
            4,
            "Составьте фильтр высоких частот",
            R.drawable.schema4_before,
            R.drawable.schema4_option_one,
            R.drawable.schema4_after_option_one,
            R.drawable.schema4_option_two,
            R.drawable.schema4_after_option_two,
            R.drawable.schema4_option_three,
            R.drawable.schema4_after_option_three,
            R.drawable.schema4_after_option_two
        )
        questionList.add(que4)
        val que5 = QuestionAverage (
            5,
            "Составьте фильтр низких частот",
            R.drawable.schema5_before,
            R.drawable.schema5_option_one,
            R.drawable.schema5_after_option_one,
            R.drawable.schema5_option_two,
            R.drawable.schema5_after_option_two,
            R.drawable.schema5_option_three,
            R.drawable.schema5_after_option_three,
            R.drawable.schema5_after_option_two
        )
        questionList.add(que5)
        return questionList
    }
}