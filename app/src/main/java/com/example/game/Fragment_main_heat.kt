package com.example.game

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.game.databinding.FragmentMainHeatBinding
import com.example.game.dialogs.Fragment_CustomPopUpHeat
import com.example.game.elementsCreation.Elements
import com.example.game.elementsCreation.HeatResult

class Fragment_main_heat : Fragment() {
    /** dataModel для взаимодействия с другими элементами UI */
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainHeatBinding
    private var freeBoxIndex_heat: Int = 0

    /** диалог для процесса получения элемента */
    lateinit var dialog: Dialog

    /** Массив выбранных элементов */
    private val elements = Elements()

    /** Массив imageId для корректного удаления выбранных элементов */
    private val imageIdList = arrayListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainHeatBinding.inflate(inflater)
        dialog = Dialog(requireContext())
        elements.empty()
        imageIdList.clear()

        elements.empty()
        imageIdList.clear()
        dataModel.elementFromList.observe(viewLifecycleOwner, Observer {
            when (freeBoxIndex_heat) {
                0 -> {
                    freeBoxIndex_heat++
                }

                1 -> {
                    binding.iv1Heat.visibility = View.VISIBLE
                    binding.iv1Heat.setImageResource(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_heat++
                }

                else -> {
                    if (!GETNEWELEMENT)
                        MAIN.customToast(R.string.containersFilled)
                    GETNEWELEMENT = false
                }
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        freeBoxIndex_heat = 0
        Log.i("test", "testHeat")
        binding.iv1Heat.setImageDrawable(null)
        binding.iv1Heat.setOnClickListener {
            if (binding.iv1Heat.drawable != null) {
                binding.iv1Heat.visibility = View.INVISIBLE
                freeBoxIndex_heat--
                Log.i("elements", imageIdList[0].toString())
                elements.remove(imageIdList[0])
                imageIdList.removeAt(0)
            }
        }
        binding.btnGetHeat.setOnClickListener {
            val resElements = HeatResult.get(elements)
            GETNEWELEMENT = true
            if (resElements != null) {
                showPopUp()
                dataModel.potentialElementsToAdd.value = resElements
                binding.iv1Heat.callOnClick()
            } else {
                MAIN.customToast(R.string.noResult)
            }
        }
    }

    fun showPopUp() {
        Fragment_CustomPopUpHeat().show(requireActivity().supportFragmentManager, "CustomPopUpHeat")
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main_heat()
    }
}
