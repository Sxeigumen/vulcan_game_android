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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.game.databinding.FragmentMainBinding


class Fragment_main : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainBinding
    private var freeBoxIndex: Int = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("test", "testMain")
        binding.iv1Main.setImageDrawable(null)
        binding.iv2Main.setImageDrawable(null)
        binding.iv3Main.setImageDrawable(null)
        binding.iv1Main.setOnClickListener {
            if(binding.iv1Main.drawable != null){
                binding.iv1Main.visibility = View.INVISIBLE
                freeBoxIndex--
            }

        }
        binding.iv2Main.setOnClickListener {
            if(binding.iv2Main.drawable != null) {
                binding.iv2Main.visibility = View.INVISIBLE
                freeBoxIndex -= 3
            }
        }
        binding.iv3Main.setOnClickListener {
            if(binding.iv3Main.drawable != null){
                binding.iv3Main.visibility = View.INVISIBLE
                freeBoxIndex-=5
            }
        }

        dataModel.message.observe(viewLifecycleOwner, Observer {
            Log.i("fisrt", "test")
            when (freeBoxIndex) {
                1, 4, 6, 9 -> {
                    binding.iv1Main.visibility = View.VISIBLE
                    binding.iv1Main.setImageResource(it)
                    freeBoxIndex++
                }

                2, 7 -> {
                    binding.iv2Main.visibility = View.VISIBLE
                    binding.iv2Main.setImageResource(it)
                    freeBoxIndex += 3
                }

                5 -> {
                    binding.iv3Main.visibility = View.VISIBLE
                    binding.iv3Main.setImageResource(it)
                    freeBoxIndex += 5
                }

                else -> Toast.makeText(context, "All cells are filled", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main()
    }
}