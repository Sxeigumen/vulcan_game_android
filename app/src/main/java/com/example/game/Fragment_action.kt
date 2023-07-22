package com.example.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.game.databinding.FragmentActionBinding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController


class Fragment_action : Fragment() {
    lateinit var binding: FragmentActionBinding
    val FragmentHeat: Fragment = Fragment_main_heat()
    val FragmentMain: Fragment = Fragment_main()
    val FragmentCool: Fragment = Fragment_main_cool()
    val FragmentElec: Fragment = Fragment_main_electrolyze()
    val FragmentShine: Fragment = Fragment_main_shine()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var idCurrentFragment: Int = MAIN.navController.currentDestination!!.id
        binding.btnMix.setOnClickListener{
            MAIN.replaceFragment(FragmentMain)
        }

        binding.btnHeat.setOnClickListener{
            MAIN.replaceFragment(FragmentHeat)
        }

        binding.btnElectro.setOnClickListener{
            if(ELECTROLYZE){
                MAIN.replaceFragment(FragmentElec)
            }
            else{
                Toast.makeText(context, R.string.notHaveElec, Toast.LENGTH_LONG).show()
            }
        }

        binding.btnHolod.setOnClickListener{
            MAIN.replaceFragment(FragmentCool)
        }

        binding.btnIzluch.setOnClickListener{
            MAIN.replaceFragment(FragmentShine)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_action()
    }
}