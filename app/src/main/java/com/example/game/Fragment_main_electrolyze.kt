package com.example.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.game.databinding.FragmentMainCoolBinding
import com.example.game.databinding.FragmentMainElectrolyzeBinding
import com.example.game.elementsCreation.CoolResult
import com.example.game.elementsCreation.ElectrolyzeResult
import com.example.game.elementsCreation.Elements

class Fragment_main_electrolyze : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainElectrolyzeBinding
    private var freeBoxIndex_elec: Int = 0
    /** Массив выбранных элементов */
    private val elements = Elements()

    /** Массив imageId для корректного удаления выбранных элементов */
    private val imageIdList = arrayListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainElectrolyzeBinding.inflate(inflater)
        elements.empty()
        imageIdList.clear()
        dataModel.message.observe(viewLifecycleOwner, Observer {
            when (freeBoxIndex_elec) {
                0 -> {
                    freeBoxIndex_elec++
                }
                1 -> {
                    binding.iv1Elec.visibility = View.VISIBLE
                    binding.iv1Elec.setImageResource(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_elec++
                }
                else -> Toast.makeText(context, R.string.containersFilled, Toast.LENGTH_LONG)
                    .show()
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        freeBoxIndex_elec = 0
        Log.i("test", "testElec")
        binding.iv1Elec.setImageDrawable(null)
        binding.iv1Elec.setOnClickListener {
            if(binding.iv1Elec.drawable != null){
                binding.iv1Elec.visibility = View.INVISIBLE
                freeBoxIndex_elec--
                Log.i("elements", imageIdList[0].toString())
                elements.remove(imageIdList[0])
                imageIdList.removeAt(0)
            }
        }
        binding.btnGetElec.setOnClickListener {
            val resElement = ElectrolyzeResult.get(elements)
//            for (el in elements) {
//                Toast.makeText(context, el.toString(), Toast.LENGTH_LONG).show()
//            }
            if (resElement != null) {
                dataModel.message.value = resElement
            } else {
                Toast.makeText(context, R.string.noResult, Toast.LENGTH_LONG).show()
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main_electrolyze()
    }
}