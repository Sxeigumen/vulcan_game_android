package com.example.game

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName: TextView = findViewById(R.id.tv_name)
        val tvScore:TextView = findViewById(R.id.tv_score)
        val btnFinish: Button = findViewById(R.id.btn_finish)
        val imageTrophy: ImageView = findViewById(R.id.iv_trophy)


        val username = intent.getStringExtra(Constants.USER_NAME)
        tvName.text = username

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        if (correctAnswers in 9..10)
        {
            imageTrophy.setImageResource(R.drawable.trophy_gold)
        }
        else if (correctAnswers in 7..8)
        {
            imageTrophy.setImageResource(R.drawable.trophy_silver)
        }
        else if (correctAnswers in 5..6)
        {
            imageTrophy.setImageResource(R.drawable.trophy_bronze)
        }
        else
        {
            imageTrophy.setImageResource(R.drawable.medal)
        }
        tvScore.text = "Your score is $correctAnswers out of $totalQuestions"
        btnFinish.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra(Constants.USER_NAME, username)
            startActivity(intent)
            finish()
        }
    }
}