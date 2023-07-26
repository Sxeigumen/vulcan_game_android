package com.example.game

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val username = intent.getStringExtra(Constants.USER_NAME)
        val buttonStart1:Button = findViewById(R.id.btn_start1)
        val buttonStart2:Button = findViewById(R.id.btn_start2)
        val buttonStart3:Button = findViewById(R.id.btn_start3)
        buttonStart1.setOnClickListener {
            val intent = Intent(this, LevelEasyActivity::class.java)
            intent.putExtra(Constants.USER_NAME, username)
            startActivity(intent)
            finish()
        }
        buttonStart2.setOnClickListener {
            val intent = Intent(this, LevelAverageActivity::class.java)
            intent.putExtra(Constants.USER_NAME, username)
            startActivity(intent)
            finish()
        }
        buttonStart3.setOnClickListener {
            val intent = Intent(this, LevelHardActivity::class.java)
            intent.putExtra(Constants.USER_NAME, username)
            startActivity(intent)
            finish()
        }
    }
}

