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

}