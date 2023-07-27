package com.example.game

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.game.databinding.ActivityLevelEasyBinding
import java.io.IOException

class LevelEasyActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition:Int = 1
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionPosition:Int = 0
    private var mCorrectAnswers:Int = 0
    private var mUserName: String? = null
    private lateinit var binding: ActivityLevelEasyBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_easy)
        MAINEASY = this
        binding = ActivityLevelEasyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionsList = Constants.getQuestions()
        setQuestion()
        playAudio()
        binding.tvOptionOne?.setOnClickListener(this)
        binding.tvOptionTwo?.setOnClickListener(this)
        binding.tvOptionThree?.setOnClickListener(this)
        binding.tvOptionFour?.setOnClickListener(this)
        binding.btnSubmit?.setOnClickListener(this)
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    @SuppressLint("SetTextI18n")
    private fun setQuestion() {

        val question = mQuestionsList!![mCurrentPosition - 1]
        defaultOptionsView()

        if (mCurrentPosition - 1 == mQuestionsList!!.size) {
            binding.btnSubmit?.text = "FINISH"
        } else {
            binding.btnSubmit?.text = "SUBMIT"
        }

        binding.progressBar?.progress = mCurrentPosition
        binding.tvProgress?.text = "$mCurrentPosition" + "/" + binding.progressBar?.max

        binding.tvQuestion?.text = question.question
        binding.ivImage?.setImageResource(question.image)
        binding.tvOptionOne?.text = question.optionOne
        binding.tvOptionTwo?.text = question.optionTwo
        binding.tvOptionThree?.text = question.optionThree
        binding.tvOptionFour?.text = question.optionFour

    }
    private fun defaultOptionsView()
    {
        val options = ArrayList<TextView>()
        binding.tvOptionOne?.let {
            options.add(0, it)
        }
        binding.tvOptionTwo?.let {
            options.add(1, it)
        }
        binding.tvOptionThree?.let {
            options.add(2, it)
        }
        binding.tvOptionFour?.let {
            options.add(3,it)
        }


        for (option in options)
        {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.isEnabled = true
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_option_one -> {
                binding.tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_option_two -> {
                binding.tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_option_three -> {
                binding.tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_option_four -> {
                binding.tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition ++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        } else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            intent.putExtra(Constants.NUMBER_OF_GAME, "1")
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        binding.btnSubmit?.text = "FINISH"
                    } else {
                        binding.btnSubmit?.text = "Go TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                    binding.tvOptionOne?.isEnabled = false
                    binding.tvOptionTwo?.isEnabled = false
                    binding.tvOptionThree?.isEnabled = false
                    binding.tvOptionFour?.isEnabled = false
                }
            }
        }
    }
    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                binding.tvOptionOne?.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                binding.tvOptionTwo?.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                binding.tvOptionThree?.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                binding.tvOptionFour?.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
    fun playAudio(){
        val audioURL = "https://s67vla.storage.yandex.net/get-mp3/a4b3560e4659800a6ca1e18be1b38de8/00060177b3aa3ae8/rmusic/U2FsdGVkX18lZfFP5bNel22r06hX10ISSp9oUaPXeWtiuBMvaNqyRvkeZgNZpUttWTgC8wSz6Q-8bhLQ6MWSiTNaIIgYHWuBQbxdbdzeoAw/7c0e37dc0d0da03c903df7778d08acc1544ee374664f64355dcec7c13b244e31/17891?track-id=111749884&play=false"
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try{
            mediaPlayer!!.setDataSource(audioURL)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
    fun pauseAudio(){
        if(mediaPlayer != null){
            if(mediaPlayer!!.isPlaying){
                mediaPlayer!!.stop()
                mediaPlayer!!.reset()
                mediaPlayer!!.release()
            }
            mediaPlayer = null
        }
    }

}