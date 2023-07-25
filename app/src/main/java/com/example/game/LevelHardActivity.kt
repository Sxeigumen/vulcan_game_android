package com.example.game

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.game.ServerCommunication.Client
import com.example.game.ServerCommunication.ClientDataModel
import com.example.game.ServerCommunication.ServerInfo
import com.example.game.databinding.ActivityLevelHardBinding

class LevelHardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLevelHardBinding
    lateinit var navController: NavController
    private val clientDataModel: ClientDataModel by viewModels()

    /** variables for server connection */
//    private val serverInfo = ServerInfo("10.0.41.59", 20_000)
    private val serverInfo = ServerInfo("10.0.41.246", 12345)

    //    private val serverInfo = ServerInfo("192.168.1.9", 12345)
    private lateinit var client: Client
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

        /** Инициализация клиента и его отправка всем клиентам */
        client = Client(serverInfo)
        client.run()
        clientDataModel.client.value = client
    }

    override fun onStop() {
        super.onStop()
        /** Закрытие клиента */
        client.close()
    }

    fun customToast(string: Int){
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

    override fun onBackPressed() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}