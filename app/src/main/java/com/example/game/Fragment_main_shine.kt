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
import com.example.game.databinding.FragmentMainHeatBinding
import com.example.game.databinding.FragmentMainShineBinding

class Fragment_main_shine : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainShineBinding
    var freeBoxIndex = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainShineBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("test", "testShine")
        binding.iv1Shine.setImageDrawable(null)
        binding.iv1Shine.setOnClickListener {
            if(binding.iv1Shine.drawable != null){
                binding.iv1Shine.visibility = View.INVISIBLE
                freeBoxIndex--
            }
        }

        dataModel.message.observe(viewLifecycleOwner, Observer {
            when (freeBoxIndex) {
                1 -> {
                    binding.iv1Shine.visibility = View.VISIBLE
                    binding.iv1Shine.setImageResource(it)
                    freeBoxIndex++
                }
                else -> Toast.makeText(context, "All cells are filled", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main_shine()
    }
}