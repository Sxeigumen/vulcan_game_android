package com.example.game

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.game.databinding.ActivityLevelAverageBinding

class LevelAverageActivity : AppCompatActivity(), View.OnClickListener {
    private val itemDragMessage = "Item Added"

    private var mCurrentPosition:Int = 1
    private var mQuestionsList : ArrayList<QuestionAverage>? = null
    //private var mSelectedOptionPosition:Int = 0
    private var mCorrectAnswers:Int = 0
    private var mUserName: String? = null
    private var mCurrentStage : Int = 0

    private lateinit var binding: ActivityLevelAverageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_average)

        binding = ActivityLevelAverageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = ConstantsAverage.getQuestions()
        setQuestion()

        attachViewDraglistener()
        binding.maskDropArea.setOnDragListener(maskDragListener)
        binding.btnSubmit.setOnClickListener(this)
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
        //defaultOptionsView()

        if (mCurrentPosition - 1 == mQuestionsList!!.size) {
            binding.btnSubmit.text = "FINISH"
        } else {
            binding.btnSubmit.text = "SUBMIT"
        }

        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition" + "/" + binding.progressBar.max
        binding.ivScheme.setImageResource(question.image)

        binding.tvQuestion.text = question.question
        binding.tvQuestion.text = question.question
        binding.ivOptionOne.setImageResource(question.imageOptionOne)
        binding.ivOptionTwo.setImageResource(question.imageOptionTwo)
        binding.ivOptionThree.setImageResource(question.imageOptionThree)

    }

   /* private fun defaultOptionsView()
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
    }*/

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_submit -> {
                if (mCurrentStage == 0) {
                    mCurrentPosition ++
                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        } else -> {
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                        intent.putExtra(Constants.NUMBER_OF_GAME, "2")
                        startActivity(intent)
                        finish()
                    }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mCurrentStage) {
                        Toast.makeText(
                            this,
                            "Incorrect answer",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "CORRECT!",
                            Toast.LENGTH_SHORT
                        ).show()
                        mCorrectAnswers++
                    }
                    if (mCurrentPosition == mQuestionsList!!.size) {
                        binding.btnSubmit.text = "FINISH"
                    } else {
                        binding.btnSubmit.text = "Go TO NEXT QUESTION"
                    }
                    mCurrentStage = 0
                }
            }
        }
    }
    /*private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
*/
    private val maskDragListener = View.OnDragListener {
            view, dragEvent ->
        val draggableItem = dragEvent.localState as View
        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                binding.maskDropArea.alpha = 0.3f
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                binding.maskDropArea.alpha = 1.0f
                //draggableItem.visibility = View.VISIBLE
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                binding.maskDropArea.alpha = 1.0f
                if (draggableItem.id == R.id.ivOptionOne)
                {
                    /*val name : String = "schema${mCurrentPosition}_after_optionone"
                    val resID = resources.getIdentifier(
                        name, "drawable",
                        packageName
                    )*/
                    mQuestionsList?.get(mCurrentPosition - 1)?.let {
                        binding.ivScheme.setImageResource(
                            it.imageAfterOptionOne)
                        mCurrentStage = it.imageAfterOptionOne
                    }
                }
                if (draggableItem.id == R.id.ivOptionTwo)
                {
                    /*val name : String = "schema1_after_optiontwo"
                    val resID = resources.getIdentifier(
                        name, "drawable",
                        packageName
                    )*/
                    mQuestionsList?.get(mCurrentPosition - 1)?.let {
                        binding.ivScheme.setImageResource(
                            it.imageAfterOptionTwo)
                        mCurrentStage = it.imageAfterOptionTwo
                    }
                }
                if (draggableItem.id == R.id.ivOptionThree)
                {
                    /*val name : String = "schema1_after_optionthree"
                    val resID = resources.getIdentifier(
                        name, "drawable",
                        packageName
                    )*/
                    mQuestionsList?.get(mCurrentPosition - 1)?.let {
                        binding.ivScheme.setImageResource(
                            it.imageAfterOptionThree)
                        mCurrentStage = it.imageAfterOptionThree
                    }
                }

                /*if (dragEvent.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    val draggedData = dragEvent.clipData.getItemAt(0).text
                    println("draggedData $draggedData")
                }*/

                /*draggableItem.x = dragEvent.x - (draggableItem.width/2)
                draggableItem.y = dragEvent.y - (draggableItem.height/2)

                val parent = draggableItem.parent as ConstraintLayout

                parent.removeView(draggableItem)

                val dropArea = view as ConstraintLayout
                dropArea.addView(draggableItem)*/
                //checkIfElementIsOnScheme(dragEvent)
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {

                //draggableItem.visibility = View.VISIBLE
                view.invalidate()
                true
            }
            else -> {
                false
            }
        }
    }
    private val elementOn = "Bingo! Element on"
    private val elementOff = "Element off :("
    /*private fun checkIfElementIsOnScheme(dragEvent: DragEvent)  {
        val faceXStart = binding.ivScheme.x
        val faceYStart = binding.ivScheme.y

        val faceXEnd = faceXStart + binding.ivScheme.width
        val faceYEnd = faceYStart + binding.ivScheme.height

        val toastedMsg = if (dragEvent.x in faceXStart..faceXEnd && dragEvent.y in faceYStart..faceYEnd) {
            elementOn
        } else {
            elementOff
        }
        Toast.makeText(this, toastedMsg, Toast.LENGTH_SHORT).show()
    }*/
    private fun attachViewDraglistener()
    {
        binding.ivOptionOne.setOnLongClickListener { view: View ->
            val item = ClipData.Item(itemDragMessage)

            val dataToDrag = ClipData(itemDragMessage, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)
            val name : String = "schema${mCurrentPosition}_option_one"
            val resID = resources.getIdentifier(
                name, "drawable",
                packageName
            )

            val maskShadow = MaskDragShadowBuilder(view, resID)

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                @Suppress("DEPRECATION")
                view.startDrag(dataToDrag, maskShadow, view, 0)
            } else {
                view.startDragAndDrop(dataToDrag, maskShadow, view, 0)
            }
            //view.visibility = View.INVISIBLE
            true

        }
        binding.ivOptionTwo.setOnLongClickListener { view: View ->
            val item = ClipData.Item(itemDragMessage)

            val dataToDrag = ClipData(itemDragMessage, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)
            val name : String = "schema${mCurrentPosition}_option_two"
            val resID = resources.getIdentifier(
                name, "drawable",
                packageName
            )

            val maskShadow = MaskDragShadowBuilder(view,  resID)

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                @Suppress("DEPRECATION")
                view.startDrag(dataToDrag, maskShadow, view, 0)
            } else {
                view.startDragAndDrop(dataToDrag, maskShadow, view, 0)
            }
            //view.visibility = View.INVISIBLE
            true

        }
        binding.ivOptionThree.setOnLongClickListener { view: View ->
            val item = ClipData.Item(itemDragMessage)

            val dataToDrag = ClipData(itemDragMessage, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)
            val name : String = "schema${mCurrentPosition}_option_three"
            val resID = resources.getIdentifier(
                name, "drawable",
                packageName
            )

            val maskShadow = MaskDragShadowBuilder(view,  resID)

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                @Suppress("DEPRECATION")
                view.startDrag(dataToDrag, maskShadow, view, 0)
            } else {
                view.startDragAndDrop(dataToDrag, maskShadow, view, 0)
            }
            //view.visibility = View.INVISIBLE
            true

        }
    }
}
private class MaskDragShadowBuilder(view: View, imageId: Int) : View.DragShadowBuilder(view) {
    private val shadow = ResourcesCompat.getDrawable(view.context.resources, imageId, view.context.theme)

    override fun onProvideShadowMetrics(size: Point, touch: Point) {
        val width: Int = view.width
        val height: Int = view.height
        shadow?.setBounds(0, 0, width, height)
        size.set(width, height)
        touch.set(width / 2, height / 2)
    }

    override fun onDrawShadow(canvas: Canvas) {
        shadow?.draw(canvas)
    }
}