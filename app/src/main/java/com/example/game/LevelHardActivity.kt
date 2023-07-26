package com.example.game

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.game.databinding.ActivityLevelHardBinding
import com.example.game.dialogs.CustomPopUpListener

class LevelHardActivity : AppCompatActivity(), CustomPopUpListener {
    private lateinit var binding: ActivityLevelHardBinding
    lateinit var navController: NavController

    /** dataModel для связи с другими элементами UI */
    private val dataModel: DataModel by viewModels()
    var toast: Toast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelHardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.place2) as NavHostFragment
        navController = navHostFragment.navController
        MAIN = this
        openFrag(Fragment_list.newInstance(), R.id.place1)
        openFrag(Fragment_main.newInstance(), R.id.place2)
        openFrag(Fragment_action.newInstance(), R.id.place3)
    }

    fun customToast(string: String) {
        if (toast != null) {
            toast?.cancel()
        }
        toast = Toast.makeText(this, string, Toast.LENGTH_LONG)
        toast?.show()
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.place2, fragment)
        transaction.setReorderingAllowed(true)
        transaction.commit()
    }

    private fun openFrag(frag: Fragment, idHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, frag)
            .commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /** слушатель для удачного получения элемента */
    override fun onSuccessfulReceive(dialog: DialogFragment) {
        for (element in dataModel.potentialElementsToAdd.value!!) {
            dataModel.elementToList.value = element
        }
    }

    /** слушатель для неудачного получения элемента */
    override fun onFailedReceive(dialog: DialogFragment) {
        var elements = ""
        for (element in dataModel.potentialElementsToAdd.value!!) {
            elements += element.NameId + ' '
        }
        dataModel.potentialElementsToAdd.value = null
        customToast("Не удалось получить элемент $elements")
    }
}