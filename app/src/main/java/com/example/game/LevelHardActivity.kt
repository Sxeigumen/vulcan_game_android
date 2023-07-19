package com.example.game

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.game.databinding.ActivityLevelHardBinding
import androidx.recyclerview.widget.RecyclerView

class LevelHardActivity : AppCompatActivity(), ElementAdapter.Listener {
    private lateinit var binding : ActivityLevelHardBinding
    private val adapter = ElementAdapter(this)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelHardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        val elements = mutableListOf(
            "Водород", "Кислород", "Марганец", "Фтор"
        )
        for (element in elements)
        {
            val elem = Element(R.drawable.ic_launcher_foreground, element)
            adapter.addElement(elem)
        }
        var selects = ArrayList<Boolean>(elements.size)
        binding.btnGet.setOnClickListener {
            val elem = Element(R.drawable.ic_launcher_foreground, "Хром")
            adapter.addElement(elem)
            /*if (binding.cell1.isEmpty() or binding.cell2.isEmpty() or binding.cell3.isEmpty()) {
                Toast.makeText(this, "Заполните все ячейки", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ничего не находится:(", Toast.LENGTH_SHORT).show()
                if (binding.iv1.id == R.drawable.water && binding.iv2.id == R.drawable.oxigen && binding.iv3.id == R.drawable.water)
                {
                    elements.add("Вода")
                    adapter.notifyDataSetChanged()
                }
                if (binding.iv1.id == R.drawable.marganec && binding.iv2.id == R.drawable.ftor && binding.iv3.id == R.drawable.ftor)
                {
                    elements.add("Фторид марганца")
                    adapter.notifyDataSetChanged()
                }
            }*/

        }

        binding.btnItem1.setOnClickListener {
            Toast.makeText(this, "Clicked 1!", Toast.LENGTH_SHORT).show()
        }
        binding.btnItem2.setOnClickListener {
            Toast.makeText(this, "Clicked 2!", Toast.LENGTH_SHORT).show()
        }
        binding.btnItem3.setOnClickListener {
            Toast.makeText(this, "Clicked 3!", Toast.LENGTH_SHORT).show()
        }
    }
    /*protected fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        val item = binding.listView.adapter.getItem(position) as String
        Toast.makeText(this, "$item выбран", Toast.LENGTH_LONG).show()
    }*/
    private fun init() {
        binding.apply {
            recView.layoutManager = LinearLayoutManager(this@LevelHardActivity)
            recView.adapter = adapter
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(element: Element) {
        Toast.makeText(this, "Clicked  on ${element.title}", Toast.LENGTH_SHORT).show()
    }
//    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
//        defaultOptionsView()
//        mSelectedOptionPosition = selectedOptionNum
//
//        tv.setTextColor(Color.parseColor("#363A43"))
//        tv.setTypeface(tv.typeface, Typeface.BOLD)
//        tv.background = ContextCompat.getDrawable(
//            this,
//            R.drawable.selected_option_border_bg
//        )
//    }
}