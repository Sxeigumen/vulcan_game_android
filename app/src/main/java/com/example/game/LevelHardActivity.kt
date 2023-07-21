package com.example.game

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.registerForActivityResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.game.databinding.ActivityLevelHardBinding
import com.example.game.databinding.FragmentMainBinding

class LevelHardActivity : AppCompatActivity(){
    private lateinit var binding : ActivityLevelHardBinding
    private val dataModel: DataModel by viewModels()
    lateinit var navController: NavController
    private val adapter = ElementAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelHardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.place2) as NavHostFragment
        navController = navHostFragment.navController
        MAIN = this
        openFrag(Fragment_list.newInstance(), R.id.place1)
        openFrag(Fragment_main.newInstance(), R.id.place2)
        openFrag(Fragment_action.newInstance(), R.id.place3)
    }
    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.place2, fragment)
        transaction.commit()
    }

    private fun openFrag(frag: Fragment, idHolder: Int){
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, frag)
            .commit()
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