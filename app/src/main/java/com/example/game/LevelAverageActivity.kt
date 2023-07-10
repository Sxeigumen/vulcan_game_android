package com.example.game

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.example.game.databinding.ActivityLevelAverageBinding

class LevelAverageActivity : AppCompatActivity() {
    private val maskDragMessage = "Mask Added"

    private lateinit var binding: ActivityLevelAverageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_average)

        binding = ActivityLevelAverageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        attachViewDraglistener()
        binding.maskDropArea.setOnDragListener(maskDragListener)
    }
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
                    val name : String = "schema1_after_optionone"
                    val resID = resources.getIdentifier(
                        name, "drawable",
                        packageName
                    )
                    binding.faceArea.setBackgroundResource(resID)
                }
                if (draggableItem.id == R.id.ivOptionTwo)
                {
                    val name : String = "schema1_after_optiontwo"
                    val resID = resources.getIdentifier(
                        name, "drawable",
                        packageName
                    )
                    binding.faceArea.setBackgroundResource(resID)
                }
                if (draggableItem.id == R.id.ivOptionThree)
                {
                    val name : String = "schema1_after_optionthree"
                    val resID = resources.getIdentifier(
                        name, "drawable",
                        packageName
                    )
                    binding.faceArea.setBackgroundResource(resID)
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
                checkIfElementIsOnScheme(dragEvent)
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
    private fun checkIfElementIsOnScheme(dragEvent: DragEvent)  {
        val faceXStart = binding.faceArea.x
        val faceYStart = binding.faceArea.y

        val faceXEnd = faceXStart + binding.faceArea.width
        val faceYEnd = faceYStart + binding.faceArea.height

        val toastedMsg = if (dragEvent.x in faceXStart..faceXEnd && dragEvent.y in faceYStart..faceYEnd) {
            elementOn
        } else {
            elementOff
        }
        Toast.makeText(this, toastedMsg, Toast.LENGTH_SHORT).show()
    }
    private fun attachViewDraglistener()
    {
        binding.ivOptionOne.setOnLongClickListener { view: View ->
            val item = ClipData.Item(maskDragMessage)

            val dataToDrag = ClipData(maskDragMessage, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)

            val maskShadow = MaskDragShadowBuilder(view)

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
            val item = ClipData.Item(maskDragMessage)

            val dataToDrag = ClipData(maskDragMessage, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)

            val maskShadow = MaskDragShadowBuilder(view)

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
            val item = ClipData.Item(maskDragMessage)

            val dataToDrag = ClipData(maskDragMessage, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)

            val maskShadow = MaskDragShadowBuilder(view)

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
private class MaskDragShadowBuilder(view: View) : View.DragShadowBuilder(view) {
    private val shadow = ResourcesCompat.getDrawable(view.context.resources, R.drawable.arrow, view.context.theme)

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