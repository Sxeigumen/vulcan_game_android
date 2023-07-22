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
import com.example.game.databinding.FragmentMainHeatBinding
import com.example.game.databinding.FragmentMainShineBinding
import com.example.game.elementsCreation.Elements
import com.example.game.elementsCreation.MixResults
import com.example.game.elementsCreation.ShineResult

class Fragment_main_shine : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainShineBinding
    private var freeBoxIndex_shine: Int = 0
    /** Массив выбранных элементов */
    private val elements = Elements()

    /** Массив imageId для корректного удаления выбранных элементов */
    private val imageIdList = arrayListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainShineBinding.inflate(inflater)
        elements.empty()
        imageIdList.clear()
        dataModel.message.observe(viewLifecycleOwner, Observer {
            when (freeBoxIndex_shine) {
                0 -> {
                    freeBoxIndex_shine++
                }
                1 -> {
                    binding.iv1Shine.visibility = View.VISIBLE
                    binding.iv1Shine.setImageResource(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_shine++
                }
                else -> Toast.makeText(context, R.string.containersFilled, Toast.LENGTH_LONG)
                    .show()
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        freeBoxIndex_shine = 0
        Log.i("test", "testShine")
        binding.iv1Shine.setImageDrawable(null)
        binding.iv1Shine.setOnClickListener {
            if(binding.iv1Shine.drawable != null){
                binding.iv1Shine.visibility = View.INVISIBLE
                freeBoxIndex_shine--
                Log.i("elements", imageIdList[0].toString())
                elements.remove(imageIdList[0])
                imageIdList.removeAt(0)
            }
        }
        binding.btnGetShine.setOnClickListener {
            val resElement = ShineResult.get(elements)
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
        fun newInstance() = Fragment_main_shine()
    }
}