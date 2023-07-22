package com.example.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.game.databinding.FragmentMainCoolBinding
import com.example.game.databinding.FragmentMainHeatBinding

class Fragment_main_heat : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainHeatBinding
    var freeBoxIndex = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainHeatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("test", "testHeat")
        binding.iv1Heat.setImageDrawable(null)
        binding.iv1Heat.setOnClickListener {
            if(binding.iv1Heat.drawable != null){
                binding.iv1Heat.visibility = View.INVISIBLE
                freeBoxIndex--
            }
        }

        dataModel.message.observe(viewLifecycleOwner, Observer {
            when (freeBoxIndex) {
                1 -> {
                    binding.iv1Heat.visibility = View.VISIBLE
                    binding.iv1Heat.setImageResource(it.ImageId)
                    freeBoxIndex++
                }
                else -> Toast.makeText(context, "All cells are filled", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main_heat()
    }
}