package com.example.game

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.game.databinding.ActivityLevelHardBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.game.elementsCreation.Elements

class LevelHardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLevelHardBinding
    private val adapter = ElementAdapter()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelHardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        val elements = mutableListOf(
            "Водород", "Кислород", "Марганец", "Фтор"
        )
        for (element in elements) {
            val elem = Element(R.drawable.ic_launcher_foreground, element)
            adapter.addElement(elem)
        }

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
        /*binding.recListView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this,
                "Item in position $position clicked",
                Toast.LENGTH_LONG
            ).show()
            true
        }*/
        binding.iv1.setOnClickListener {
            Toast.makeText(this, "Clicked 1!", Toast.LENGTH_SHORT).show()
        }
        binding.iv2.setOnClickListener {
            Toast.makeText(this, "Clicked 2!", Toast.LENGTH_SHORT).show()
        }
        binding.iv3.setOnClickListener {
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
}