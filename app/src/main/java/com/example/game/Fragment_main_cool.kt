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
import com.example.game.databinding.FragmentMainBinding
import com.example.game.databinding.FragmentMainCoolBinding

class Fragment_main_cool : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainCoolBinding
    var freeBoxIndex = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainCoolBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("test", "testCool")
        binding.iv1Cool.setImageDrawable(null)
        binding.iv1Cool.setOnClickListener {
            if(binding.iv1Cool.drawable != null){
                binding.iv1Cool.visibility = View.INVISIBLE
                freeBoxIndex--
            }
        }

        dataModel.message.observe(viewLifecycleOwner, Observer {
            when (freeBoxIndex) {
                1 -> {
                    binding.iv1Cool.visibility = View.VISIBLE
                    binding.iv1Cool.setImageResource(it)
                    freeBoxIndex++
                }
                else -> Toast.makeText(context, "All cells are filled", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main_cool()
    }
}