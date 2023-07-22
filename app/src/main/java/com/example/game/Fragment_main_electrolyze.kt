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
import com.example.game.databinding.FragmentMainElectrolyzeBinding

class Fragment_main_electrolyze : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainElectrolyzeBinding
    var freeBoxIndex = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainElectrolyzeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("test", "testElec")
        binding.iv1Elec.setImageDrawable(null)
        binding.iv1Elec.setOnClickListener {
            if(binding.iv1Elec.drawable != null){
                binding.iv1Elec.visibility = View.INVISIBLE
                freeBoxIndex--
            }
        }

        dataModel.message.observe(viewLifecycleOwner, Observer {
            when (freeBoxIndex) {
                1 -> {
                    binding.iv1Elec.visibility = View.VISIBLE
                    binding.iv1Elec.setImageResource(it.ImageId)
                    freeBoxIndex++
                }
                else -> Toast.makeText(context, "All cells are filled", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main_electrolyze()
    }
}